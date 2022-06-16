package com.example.demo.LiquorList.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.catalina.manager.DummyProxySession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.LiquorList.repository.*;
import com.example.demo.LiquorList.model.*;

import javax.servlet.http.HttpSession;

import java.text.Normalizer;

// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.context.annotation.Bean;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.builders.WebSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping("LiquorList")
// @Configuration
public class LoginAddController{

    @Autowired
    LoginAddMapper loginAddMapper;

    @Autowired
    HttpSession session;

    boolean info_judge;
    boolean mail_judge;
    public boolean judge_login_ing=false;

    @GetMapping("loginDisplay")
    public String LoginDisplay(Model model){
        LoginAddPageModel page=new LoginAddPageModel();

        page.title="ログイン画面";

        model.addAttribute("page",page);

        return "LiquorList/login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute LoginAddPageModel page,Model model){

        int judge_mail=loginAddMapper.search_mailaddress(page.mailaddress);
        int judge_login=loginAddMapper.login(page.mailaddress,page.password);

        

        if(judge_mail>=1){
            if(judge_mail<2){
                if(judge_login==1){
                    var data=loginAddMapper.search_the_data(page.mailaddress,page.password);

                    page.customer_id = data.customer_id;
                    page.customer_name = data.customer_name;
                    page.mailaddress=data.mailaddress;
                    page.password=data.password;

                    judge_login_ing=true;

                    session.setAttribute("customer_id", page.getCustomer_id());
                    session.setAttribute("customer_name", page.getCustomer_name());
                    session.setAttribute("mailaddress", page.getMailaddress());
                    session.setAttribute("password", page.getPassword());

                    model.addAttribute("customer_name",page.customer_name+"さんがログインしています。");

                    return "LiquorList/top";
                }else{
                    model.addAttribute("massage","メールアドレスかパスワードが間違えています。");

                    return "LiquorList/login";
                }
            }else{
                model.addAttribute("massage","エラーです。同じメールアドレスを使用しているユーザが2件以上見つかりました。管理者にお問い合わせください。");

                return "LiquorList/login";
            }
        }else{
            model.addAttribute("massage","メールアドレスかパスワードが間違えています。");

            return "LiquorList/login";
        }
    }

    // @PostMapping("logout")
    // public String logoutDisplay(Model model){
    //     session.invalidate();

    //     return "LiquorList/top";
    // }

    @GetMapping("addCustomerDisplay")
    public String addCustomerDisplay(Model model){

        LoginAddPageModel page=new LoginAddPageModel();

        page.title="新規登録画面";

        model.addAttribute("page",page);

        return "LiquorList/addCustomer";
    }

    @PostMapping("addCustomer")
    public String addCustomer(@ModelAttribute LoginAddPageModel page,Model model){
        //入力されたmailaddressに「全角英数字」が含まれていた場合、それらを「半角英数字」に変換する。
        String input_mail= Normalizer.normalize(page.mailaddress, Normalizer.Form.NFKD);

        int judge_mail=loginAddMapper.search_mailaddress(input_mail);

        String input_info=page.customer_name+page.mailaddress+page.password;

        String[] input_info_judge = input_info.split("");

        String[] input_mail_judge = input_mail.split("");

        

        info_judge=false;
        mail_judge=false;

        for(int i=0;i<input_info_judge.length;i++){
            if(input_info_judge[i].equals(" ")||input_info_judge[i].equals("　")){
                info_judge=true;
            }
        }

        for(int i=0;i<input_mail_judge.length;i++){
            if(input_mail_judge[i].equals("@")){
                mail_judge=true;
            }
        }

        if(page.customer_name.equals("")||page.mailaddress.equals("")||page.password.equals("")||page.password2.equals("")){
            model.addAttribute("massage","未入力の欄があります。");

            return "LiquorList/addCustomer";
        }else if(mail_judge==false){
            model.addAttribute("massage","メールアドレスが正しくありません。");

            return "LiquorList/addCustomer";
        }else if(info_judge==true){
            model.addAttribute("massage","空白が入力されている欄があります。");

            return "LiquorList/addCustomer";
        }else{
            if(!(judge_mail>=1)){
                if(page.password.equals(page.password2)){
                    //DBにデータを格納する
                    loginAddMapper.insert(page.customer_name,input_mail,page.password);

                    var data=loginAddMapper.search_the_data(page.mailaddress,page.password);

                    page.customer_id = data.customer_id;
                    page.customer_name = data.customer_name;
                    page.mailaddress=data.mailaddress;
                    page.password=data.password;

                    judge_login_ing=true;

                    session.setAttribute("customer_id", page.getCustomer_id());
                    session.setAttribute("customer_name", page.getCustomer_name());
                    session.setAttribute("mailaddress", page.getMailaddress());
                    session.setAttribute("password", page.getPassword());

                    model.addAttribute("customer_name",page.customer_name+"さんがログインしています。");

                    return "LiquorList/top";
                }else{
                    model.addAttribute("massage","パスワードが一致しません。");

                    return "LiquorList/addCustomer";
                }
            }else{
                model.addAttribute("massage","このメールアドレスは既に使用されています。");

                return "LiquorList/addCustomer";
            }
        }
    }

    @GetMapping("myPageDisplay")
    public String myPageDisplay(Model model){

        LoginAddPageModel page=new LoginAddPageModel();

        int customer_id=(int)session.getAttribute("customer_id");
        String customer_name=(String)session.getAttribute("customer_name");
        String mailaddress=(String)session.getAttribute("mailaddress");
        


        page.title="マイページ";
        
        model.addAttribute("customer_id",customer_id);
        model.addAttribute("customer_name",customer_name);
        model.addAttribute("mailaddress",mailaddress);

        // model.addAttribute("customer_name",customer_name+"さんがログインしています。");

        // session.invalidate();

        return "LiquorList/myPage";
    }

    @GetMapping("topForTest")
    public String topForTest(Model model){

        String customer_name=(String)session.getAttribute("customer_name");
        
        
        LoginAddPageModel page=new LoginAddPageModel();

        page.title="ログイン画面";

        if(customer_name!=null){
            model.addAttribute("customer_name",customer_name+"さんがログインしています。");
        }else{
            model.addAttribute("customer_name","ログインしていません。");

            session.invalidate();
        }

        // session.invalidate();
        
        model.addAttribute("page",page);

        return "LiquorList/top";
    }
}