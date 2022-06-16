// package com.example.demo.LiquorList.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.example.demo.LiquorList.model.FavoritePageModel;
// import com.example.demo.LiquorList.repository.FavoriteMapper;


// /**
//  * CustomerController
//  */
// @Controller
// @RequestMapping("LiquorList")
// public class FavoriteController {


//     /**
//      * Customerテーブルのアクセス用クラスを追加
//      */
//     @Autowired
//     FavoriteMapper favoriteMapper;

//     @GetMapping("favorite")
//     public String list(Model model) {
//         //ページクラスをNewしてタイトルをセット
//         FavoritePageModel page = new FavoritePageModel();
//         page.title = "お気に入り";

//         //リストを初期化
//         model.addAttribute("page", page);
//         page.list = favoriteMapper.findFavo1();

//         return "LiquorList/favorite"; //テンプレートファイルを指定
//     }

//     @Transactional
//     @GetMapping("delete/{id}")
//     public String delete(@PathVariable("id") int id, Model model){
    
//     //ページインスタンスを作って、タイトルを設定
//     FavoritePageModel page = new FavoritePageModel();
//     page.title = "お気に入り";

//     //IDをキーにデータ削除
//     favoriteMapper.delete(id);

//     //リストを初期化
//     page.list = favoriteMapper.findFavo1();

//     //
//     model.addAttribute("page", page);

//     return "LiquorList/favorite"; //テンプレートファイルを指定
//     }

    


// }
package com.example.demo.LiquorList.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.LiquorList.model.FavoritePageModel;
import com.example.demo.LiquorList.repository.FavoriteMapper;


/**
 * CustomerController
 */
@Controller
@RequestMapping("LiquorList")
public class FavoriteController {


    /**
     * Customerテーブルのアクセス用クラスを追加
     */
    @Autowired
    FavoriteMapper favoriteMapper;

    @Autowired
    HttpSession session; 

    //テスト用セッション
    @GetMapping("favorite")
    public String favorite( Model model){

        //ページクラスをNewしてタイトルをセット
        FavoritePageModel page = new FavoritePageModel();

        //セッションの中にid（customer_idという名前の）
        //session.setAttribute("customer_id",id);
        int customer_id=(int)session.getAttribute("customer_id");


        page.title = "お気に入り";

        //モデルにページインスタンスを作成
        model.addAttribute("page",page);
        //更新後のデータを取得
        page.list = favoriteMapper.findFavo(customer_id);

        return "LiquorList/favorite"; //テンプレートファイルを指定

    }

   


    //id=product_id
    @Transactional
    @GetMapping("deletefavorite/{productId}")
    public String deletefavorite(@PathVariable("productId") int productId, Model model){
    
    //ページインスタンスを作って、タイトルを設定
    FavoritePageModel page = new FavoritePageModel();

    //セッション呼び出し
    int customer_id = (int) session.getAttribute("customer_id"); 

    page.title = "お気に入り";

    //IDをキーにデータ削除
    favoriteMapper.deletefavorite(productId);

    //リストを初期化
    page.list = favoriteMapper.findFavo(customer_id);
    
    //
    model.addAttribute("page", page);

    return "LiquorList/favorite"; //テンプレートファイルを指定
    }

}