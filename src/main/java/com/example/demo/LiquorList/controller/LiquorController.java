package com.example.demo.LiquorList.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.LiquorList.model.FavoritePageModel;
import com.example.demo.LiquorList.model.LiquorPageModel;
import com.example.demo.LiquorList.repository.FavoriteMapper;
import com.example.demo.LiquorList.repository.LiquorMapper;
import com.example.demo.LiquorList.repository.LiquorModel;

@Controller
@RequestMapping("LiquorList")
public class LiquorController {
    @Autowired
    HttpSession session;

    @Autowired
    LiquorMapper liquorMapper;
    FavoriteMapper favoriteMapper;

    @GetMapping("top")
    public String top(Model model) {
        LiquorPageModel page = new LiquorPageModel();


        //モデルにページインスタンスを設定
        model.addAttribute("page",page);

        return "LiquorList/top";  //テンプレートファイルを指定
    }

    @GetMapping("list")
    public String list(Model model) {
        LiquorPageModel page = new LiquorPageModel();
    

        //リストを初期化
        page.list = liquorMapper.findall();

        //モデルにページインスタンスを設定
        model.addAttribute("page",page);

        return "LiquorList/list";  //テンプレートファイルを指定
    }
    @GetMapping("listbrandy")
    public String listbrandy(Model model) {
        LiquorPageModel page = new LiquorPageModel();
    

        //リストを初期化
        page.list = liquorMapper.findbrandy();

        //モデルにページインスタンスを設定
        model.addAttribute("page",page);

        return "LiquorList/listbrandy";  //テンプレートファイルを指定
    }
    @GetMapping("listgin")
    public String listgin(Model model) {
        LiquorPageModel page = new LiquorPageModel();
    

        //リストを初期化
        page.list = liquorMapper.findgin();

        //モデルにページインスタンスを設定
        model.addAttribute("page",page);

        return "LiquorList/list";  //テンプレートファイルを指定
    }
    @GetMapping("listrum")
    public String listrum(Model model) {
        LiquorPageModel page = new LiquorPageModel();
    

        //リストを初期化
        page.list = liquorMapper.findrum();

        //モデルにページインスタンスを設定
        model.addAttribute("page",page);

        return "LiquorList/list";  //テンプレートファイルを指定
    }
    @GetMapping("listwhisky")
    public String listwhisky(Model model) {
        LiquorPageModel page = new LiquorPageModel();
    

        //リストを初期化
        page.list = liquorMapper.findwhisky();

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
        //IDをキーにデータを検索
        LiquorModel product = new LiquorModel();
        product = liquorMapper.findone(id);
        //取得データをページに設定
        page.product_id = product.product_id;
        page.product_name = product.product_name;
        page.product_image = product.product_image;
        page.product_price = product.product_price;
        page.abv = product.abv;
        page.volume = product.volume;
        page.product_detail =product.product_detail;
     
        //モデルにページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルを指定
        return "LiquorList/detail";

    }

        //カートボタン
    @GetMapping("insertcart/{id}")
        public String insertcart(@ModelAttribute LiquorPageModel page, @PathVariable("id") int id, Model model) {
        //更新データをパラメータに設定
        int price = liquorMapper.findprice(id);
        liquorMapper.insertcart(id, price, page.count);
        //更新後データを取得
        page.list = liquorMapper.findall();
        //モデルページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルをを指定
        return "LiquorList/list";

    }
        //お気に入りボタン
    @GetMapping("insertfavorite")
        public String insertfavorite(@ModelAttribute LiquorPageModel page, Model model) {

        int customer_id=(int)session.getAttribute("customer_id");
        //更新データをパラメータに設定
        liquorMapper.insertfavorite(page.product_id, customer_id);
        //更新後データを取得
        // page.list2 = favoriteMapper.findFavo(id);
        page.list = liquorMapper.findall();
        //モデルページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルをを指定
        return "LiquorList/favorite";

    }

    
    //     //履歴ボタン
    // @PostMapping("insertfavorite")
    // public String list(@ModelAttribute LiquorPageModel page, Model model){
    //     //タイトルを設定
    //     page.title = "顧客一覧表示(Java)";
    //     //画面で入力した更新データをパラメータに設定
    //     liquorMapper.insertfavorite(page.product_id);
    //     //更新後データを取得
    //     page.list = liquorMapper.findfavorite();
    //     //モデルページインスタンスを設定
    //     model.addAttribute("page", page);
    //     //テンプレートファイルをを指定
    //     return "LiquorList/favorit";
    
    // }
    


}




