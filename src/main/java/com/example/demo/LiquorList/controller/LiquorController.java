package com.example.demo.LiquorList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.LiquorList.model.LiquorPageModel;
import com.example.demo.LiquorList.repository.LiquorMapper;

@Controller
@RequestMapping("LiquorList")
public class LiquorController {

    @Autowired
    LiquorMapper liquorMapper;

    @GetMapping("list")
    public String list(Model model) {
        LiquorPageModel page = new LiquorPageModel();
    

        //リストを初期化
        page.list = liquorMapper.findall();

        //モデルにページインスタンスを設定
        model.addAttribute("page",page);

        return "LiquorList/list";  //テンプレートファイルを指定
    }

    @PostMapping("search")
    public String search(@RequestParam("name") String name, Model model)
    {

        //ページインスタンスを作って、タイトルを決定
        LiquorPageModel page = new LiquorPageModel();

        //リストを初期化
        page.list = liquorMapper.findName("%"+name+"%");
        page.name = name;


        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを設定
        return "LiquorList/list";
    }
    @GetMapping("detail/{id}")
    public String detail(@PathVariable("id") int id, Model model){
        //ページインスタンスを作って、タイトルを設定
        LiquorPageModel page = new LiquorPageModel();
        page.title = "映画詳細画面(Java)";
        //IDをキーにデータを検索
        LiquorPageModel address = new LiquorPageModel();
        address.list = liquorMapper.findone(id);

        //モデルにページインスタンスを設定
        model.addAttribute("address", address);

        
        return "LiquorList/detail";

    }

}


