package com.example.demo.LiquorList.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.LiquorList.model.CartPageModel;
import com.example.demo.LiquorList.repository.CartMapper;
import com.example.demo.LiquorList.repository.CartModel;
import com.example.demo.LiquorList.repository.HistoryMapper;

@Controller
@RequestMapping("LiquorList")
public class CartController {


    /**
     * 表示
     */
    @Autowired
    CartMapper cartMapper;
    HistoryMapper historyMapper;

    @Autowired
    HttpSession session;

    @GetMapping("cart")
    public String cart(Model model){

        //ページクラスをNewしてタイトルをセット
        CartPageModel page = new CartPageModel();
        CartPageModel sum = new CartPageModel();

        //セッションの中にid（customer_idという名前の）←実際はログイン時にやってあるため呼び出しのみでいい
        int customer_id=(int)session.getAttribute("customer_id");

        page.title = "買い物かご";

        //モデルにページインスタンスを作成
        model.addAttribute("page",page);
        model.addAttribute("sum",sum);

        
        //更新後のデータを取得
        page.list = cartMapper.findCartId(customer_id);
        sum.list = cartMapper.findSum(customer_id);

        return "LiquorList/cart"; //テンプレートファイルを指定

    }

    

    
    //削除
    //id=product_id
    @Transactional
    @GetMapping("deletecart/{productId}")
    public String deletecart(@PathVariable("productId") int productId, Model model){
    
    //ページインスタンスを作って、タイトルを設定
    CartPageModel page = new CartPageModel();

    //セッション呼び出し
    int customer_id = (int) session.getAttribute("customer_id");


    page.title = "買い物かご";

    //IDをキーにデータ削除
    cartMapper.deletecart(productId);

    //リストを初期化
    page.list = cartMapper.findCartId(customer_id);

    //
    model.addAttribute("page", page);

    return "LiquorList/cart"; //テンプレートファイルを指定
    }

    //合計
    

    //数量変更
    @PostMapping("update/{productId}")
    public String update(@ModelAttribute CartPageModel page ,@PathVariable("productId") int productId, Model model){
        //セッション呼び出し
        int customer_id = (int) session.getAttribute("customer_id");

        //タイトルを設定
        page.title = "顧客一覧画面(Java)";
        //画面で入力したデータをパラメータに設定
        cartMapper.update(productId,page.count);
        //更新後のデータを取得
        page.list = cartMapper.findCartId(customer_id);
        //モデルページにインスタンスを設定
        model.addAttribute("page", page);

        return "LiquorList/cart"; //テンプレートファイルを指定

    }

    //完了画面
    @GetMapping("complete/{id}")
    public String complete(@PathVariable("id") int id, Model model){

        //ページクラスをNewしてタイトルをセット
        CartPageModel page = new CartPageModel();

        //セッションの中にid（customer_idという名前の）←実際はログイン時にやってあるため呼び出しのみでいい
        session.setAttribute("customer_id",id);

        page.title = "買い物かご";

        //モデルにページインスタンスを作成
        model.addAttribute("page",page);

        //更新後のデータを取得
        //page.list = cartMapper.findCartId(id);

        return "LiquorList/complete"; //テンプレートファイルを指定

    }

    //購入ボタン処理
    @GetMapping("inserthistory/{id}")
        public String inserthistory(@ModelAttribute CartPageModel page,@PathVariable("id") int id, Model model) {
            

        int customer_id = (int)session.getAttribute("customer_id");

        page.list = cartMapper.findCartId(customer_id);
        //更新データをパラメータに設定
        //cartMapper.inserthistory(id,customer_id);

        for(int i=0; i<page.list.size(); i++){
            // int a = page.list.get(i).product_id;
            cartMapper.inserthistory(page.list.get(i).product_id, page.list.get(i).customer_id);

        }
        

        //モデルページインスタンスを設定
        model.addAttribute("page", page);
        //テンプレートファイルをを指定
        return "LiquorList/complete";


    }

    //カート全削除
    @Transactional
    @GetMapping("deletecomp/{id}")
    public String deletecomp(@PathVariable("id") int id, Model model){
    
    //ページインスタンスを作って、タイトルを設定
    CartPageModel page = new CartPageModel();

    //セッション呼び出し
    int customer_id = (int) session.getAttribute("customer_id");


    page.title = "買い物かご";

    //IDをキーにデータ削除
    cartMapper.deletecomp(customer_id);

    //
    model.addAttribute("page", page);

    return "LiquorList/top"; //テンプレートファイルを指定
    }


}

