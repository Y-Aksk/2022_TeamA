package com.example.demo.LiquorList.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.example.demo.LiquorList.controller.LoginAddController;



@Component
public class LoginAddFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init!!");
        //☆☆ここにログインしていなくても開けるページ☆☆
        urls.add("^/LiquorList/loginDisplay");
        urls.add("^/LiquorList/login");
        urls.add("^/LiquorList/addCustomerDisplay");
        urls.add("^/LiquorList/addCustomer");
        urls.add("^/LiquorList/listwhisky");
        urls.add("^/LiquorList/listbrandy");
        urls.add("^/LiquorList/listgin");
        urls.add("^/LiquorList/listrum");
        urls.add("^/LiquorList/top");
        urls.add("^/LiquorList/list");
        urls.add("^/LiquorList/detail");
        urls.add("^/LiquorList/search");

        //開発のために一時的にオープンするページ
        // urls.add("^/LiquorList/myPageDisplay");

        //テスト用のトップページです
        urls.add("^/LiquorList/topForTest");
    }

    private List<String> urls = new  ArrayList<String>();

    private boolean checkURL(String checkurl){
        boolean flag =false;
        for (String url : urls) {
            if(checkurl.matches(url)){
                flag=true;
                break;
            }
        }
        return flag;
    }
    

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
                HttpServletRequest request = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;
                String url = request.getServletPath();
                //ログイン画面に戻す処理
                if (url == null) {
                    response.sendRedirect("LiquorList/loginDisplay");
                } else if (checkURL(url)) {
                    chain.doFilter(request, response);
                //フィルターをかけない拡張子の指定
                } else if (url.matches(".+\\.(css|png|jpg)")) {
                    chain.doFilter(request, response);
                } else {
                    HttpSession session = request.getSession(false);
                    if (null == session) {
                        response.sendRedirect("/LiquorList/loginDisplay");
                    } else {
                        chain.doFilter(request, response);
                    }
                }
    }

    @Override
    public void destroy() {
        System.out.println("destroy!!");
    }
}