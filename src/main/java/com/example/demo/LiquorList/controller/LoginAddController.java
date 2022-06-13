package com.example.demo.LiquorList.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.LiquorList.repository.*;
import com.example.demo.LiquorList.model.*;

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

    @GetMapping("loginDisplay")
    public String LoginDisplay(Model model){
        LoginAddPageModel page=new LoginAddPageModel();

        page.title="ログイン画面";

        model.addAttribute("page",page);

        return "LiquorList/login";
    }

    @GetMapping("addCustomerDisplay")
    public String addCustomerDisplay(Model model){
        LoginAddPageModel page=new LoginAddPageModel();

        page.title="新規登録画面";

        model.addAttribute("page",page);

        return "LiquorList/addCustomer";
    }

    @PostMapping("addCustomer")
    public String addCustomer(@ModelAttribute LoginAddPageModel page,Model model){

        if(page.password.equals(page.password2)){
            loginAddMapper.insert(page.customer_name,page.mailaddress,page.password);

            return "LiquorList/top";
        }else{
            model.addAttribute("massage","エラー");

            loginAddMapper.insert(page.customer_name,page.mailaddress,page.password);

            return "LiquorList/addCustomer";
            
        }

    }

    @PostMapping("addCustomer2")
    public String addCustomer2(Model model){

        String a="";

        return "LiquorList/addCustomer";

    }

    @GetMapping("topForTest")
    public String TopForTest(Model model){
        LoginAddPageModel page=new LoginAddPageModel();

        page.title="ログイン画面";

        model.addAttribute("page",page);

        return "LiquorList/top";
    }
}
