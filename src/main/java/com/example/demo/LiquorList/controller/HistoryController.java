package com.example.demo.LiquorList.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.LiquorList.model.HistoryPageModel;
import com.example.demo.LiquorList.repository.HistoryMapper;


 @Controller
@RequestMapping("LiquorList")
public class HistoryController {



    /**
     * Customerテーブルのアクセス用クラスを追加
     */
    @Autowired
    HistoryMapper historyMapper;
    HttpSession session; 

    @GetMapping("history")
    public String history(HttpServletRequest request, HttpServletResponse response,  Model model){

        //ページクラスをNewしてタイトルをセット
        HistoryPageModel page = new HistoryPageModel();
      

        //セッションオブジェクトの生成
        HttpSession session = request.getSession();

        int customer_id=(int)session.getAttribute("customer_id");

        //モデルにページインスタンスを作成
        model.addAttribute("page",page);
        //更新後のデータを取得
        page.list = historyMapper.findHistoryId(customer_id);

        return "LiquorList/history"; //テンプレートファイルを指定

    }


}

