package com.example.demo.LiquorList.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.catalina.manager.DummyProxySession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.lang.StringBuffer;

import com.example.demo.LiquorList.repository.*;
import com.example.demo.LiquorList.model.*;

import javax.servlet.http.HttpSession;

import java.text.Normalizer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    boolean new_mail_judge;

    @GetMapping("loginDisplay")
    public String LoginDisplay(Model model){
        LoginAddPageModel page=new LoginAddPageModel();

        page.title="ログイン画面";

        model.addAttribute("page",page);

        return "LiquorList/login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute LoginAddPageModel page,Model model){

        String password = page.password;

                    /** ハッシュ化したい文字列：strHash */
        String strHash = password;
//		System.out.println("ハッシュ化する文字列：" + strHash);

        try {
            // メッセージダイジェストのインスタンスを生成
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            byte[] result = md5.digest(strHash.getBytes());

            // 16進数に変換して桁を整える
            int[] i = new int[result.length];
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < result.length; j++) {
                i[j] = (int) result[j] & 0xff;
                if (i[j] <= 15) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i[j]));
            }
            str = sb.toString();
//			System.out.println("ハッシュ化後の文字列：" + str);

        } catch (NoSuchAlgorithmException x) {

        }

        int judge_mail=loginAddMapper.search_mailaddress(page.mailaddress);
        int judge_login=loginAddMapper.login(page.mailaddress,str);

        

        if(judge_mail>=1){
            if(judge_mail<2){
                if(judge_login==1){
                    var data=loginAddMapper.search_the_data(page.mailaddress,str);

                    page.customer_id = data.customer_id;
                    page.customer_name = data.customer_name;
                    page.mailaddress=data.mailaddress;

                    judge_login_ing=true;

                    session.setAttribute("customer_id", page.getCustomer_id());
                    session.setAttribute("customer_name", page.getCustomer_name());
                    session.setAttribute("mailaddress", page.getMailaddress());

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

    @PostMapping("logout")
    public String logout(Model model){
        session.invalidate();

        return "LiquorList/top";
    }

    @GetMapping("addCustomerDisplay")
    public String addCustomerDisplay(Model model){

        LoginAddPageModel page=new LoginAddPageModel();

        page.title="新規登録画面";

        model.addAttribute("page",page);

        return "LiquorList/addCustomer";
    }

    String str;

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

                    String password = page.password;

                    /** ハッシュ化したい文字列：strHash */
                    String strHash = password;
            //		System.out.println("ハッシュ化する文字列：" + strHash);

                    try {
                        // メッセージダイジェストのインスタンスを生成
                        MessageDigest md5 = MessageDigest.getInstance("MD5");

                        byte[] result = md5.digest(strHash.getBytes());

                        // 16進数に変換して桁を整える
                        int[] i = new int[result.length];
                        StringBuffer sb = new StringBuffer();
                        for (int j = 0; j < result.length; j++) {
                            i[j] = (int) result[j] & 0xff;
                            if (i[j] <= 15) {
                                sb.append("0");
                            }
                            sb.append(Integer.toHexString(i[j]));
                        }
                        str = sb.toString();
            //			System.out.println("ハッシュ化後の文字列：" + str);

                    } catch (NoSuchAlgorithmException x) {

                    }


                    //DBにデータを格納する
                    loginAddMapper.insert(page.customer_name,input_mail,str);

                    var data=loginAddMapper.search_the_data(page.mailaddress,str);

                    page.customer_id = data.customer_id;
                    page.customer_name = data.customer_name;
                    page.mailaddress=data.mailaddress;

                    judge_login_ing=true;

                    session.setAttribute("customer_id", page.getCustomer_id());
                    session.setAttribute("customer_name", page.getCustomer_name());
                    session.setAttribute("mailaddress", page.getMailaddress());

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

    @GetMapping("editDisplay")
    public String editDisplay(Model model){

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

        return "LiquorList/edit";
    }

    int num_mail;

    @PostMapping("edit")
    public String edit(@ModelAttribute LoginAddPageModel page,Model model){
        int customer_id=(int)session.getAttribute("customer_id");
        String customer_name=(String)session.getAttribute("customer_name");
        String mailaddress=(String)session.getAttribute("mailaddress");

        model.addAttribute("customer_id",customer_id);
        model.addAttribute("customer_name",customer_name);
        model.addAttribute("mailaddress",mailaddress);

        /** ハッシュ化したい文字列：strHash */
        String strHash = page.password;
        //		System.out.println("ハッシュ化する文字列：" + strHash);

        try {
        // メッセージダイジェストのインスタンスを生成
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        byte[] result = md5.digest(strHash.getBytes());

        // 16進数に変換して桁を整える
        int[] i = new int[result.length];
        StringBuffer sb = new StringBuffer();
        for (int j = 0; j < result.length; j++) {
            i[j] = (int) result[j] & 0xff;
            if (i[j] <= 15) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(i[j]));
        }
        str = sb.toString();
        //			System.out.println("ハッシュ化後の文字列：" + str);

        } catch (NoSuchAlgorithmException x) {

        }

        // 入力されたmailaddressに「全角英数字」が含まれていた場合、それらを「半角英数字」に変換する。
        String input_new_mail= Normalizer.normalize(page.new_mailaddress, Normalizer.Form.NFKD);

        int judge_new_mail=loginAddMapper.search_mailaddress(input_new_mail);
        int judge_login=loginAddMapper.login(mailaddress,str);

        String input_info=page.new_customer_name+page.new_mailaddress+page.new_password;

        String[] input_info_judge = input_info.split("");

        String[] input_new_mail_judge = input_new_mail.split("");

        

        info_judge=false;
        new_mail_judge=false;

        for(int i=0;i<input_info_judge.length;i++){
            if(input_info_judge[i].equals(" ")||input_info_judge[i].equals("　")){
                info_judge=true;
            }
        }

        for(int i=0;i<input_new_mail_judge.length;i++){
            if(input_new_mail_judge[i].equals("@")){
                new_mail_judge=true;
            }
        }

        
        
        if(page.new_mailaddress.equals(mailaddress)){
            num_mail=2;
        }else{
            num_mail=1;
        }

        // model.addAttribute("massage","表示テスト");
        // var data=loginAddMapper.edit(customer_id,page.new_customer_name,new_mailaddress,"");

        if(page.new_customer_name.equals("")||page.new_mailaddress.equals("")||page.password.equals("")||page.new_password2.equals("")){
            model.addAttribute("massage","未入力の欄があります。");

            return "LiquorList/edit";
        }else if(new_mail_judge==false){
            model.addAttribute("massage","メールアドレスが正しくありません。");

            return "LiquorList/edit";
        }else if(info_judge==true){
            model.addAttribute("massage","空白が入力されている欄があります。");

            return "LiquorList/edit";
        }else{
            if(judge_new_mail<num_mail){
                if(judge_login==1){
                    if(page.new_password.equals(page.new_password2)){
                        String password = page.new_password;

                        /** ハッシュ化したい文字列：new_strHash */
                        String new_strHash = password;
                //		System.out.println("ハッシュ化する文字列：" + new_strHash);

                        try {
                            // メッセージダイジェストのインスタンスを生成
                            MessageDigest md5 = MessageDigest.getInstance("MD5");

                            byte[] result = md5.digest(new_strHash.getBytes());

                            // 16進数に変換して桁を整える
                            int[] i = new int[result.length];
                            StringBuffer sb = new StringBuffer();
                            for (int j = 0; j < result.length; j++) {
                                i[j] = (int) result[j] & 0xff;
                                if (i[j] <= 15) {
                                    sb.append("0");
                                }
                                sb.append(Integer.toHexString(i[j]));
                            }
                            str = sb.toString();
                //			System.out.println("ハッシュ化後の文字列：" + str);

                        } catch (NoSuchAlgorithmException x) {

                        }


                        //DBにデータを格納する
                        loginAddMapper.edit(customer_id,page.new_customer_name,page.new_mailaddress,str);

                        var data=loginAddMapper.search_the_data(page.new_mailaddress,str);

                        page.customer_id = data.customer_id;
                        page.customer_name = data.customer_name;
                        page.mailaddress=data.mailaddress;

                        judge_login_ing=true;

                        session.setAttribute("customer_id", page.getCustomer_id());
                        session.setAttribute("customer_name", page.getCustomer_name());
                        session.setAttribute("mailaddress", page.getMailaddress());

                        model.addAttribute("customer_id", page.getCustomer_id());
                        model.addAttribute("customer_name",page.getCustomer_name());
                        model.addAttribute("mailaddress",page.getMailaddress());

                        return "LiquorList/myPage";
                    }else{
                        model.addAttribute("massage","新しいパスワードが一致しません。");

                        return "LiquorList/edit";
                    }


                }else{
                    model.addAttribute("massage","現在のパスワードが間違えています。");

                    return "LiquorList/edit";
                }

            }else{
                model.addAttribute("massage","このメールアドレスは既に使用されています。");

                return "LiquorList/edit";
            }
        }


    }
}