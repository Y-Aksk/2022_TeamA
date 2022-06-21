package com.example.demo.LiquorList.model;

import java.util.List;
import com.example.demo.LiquorList.repository.*;

public class LoginAddPageModel {
    public String title;
    public List<LoginAddModel> list;
    public int customer_id;
    public String customer_name;
    public String new_customer_name;
    public String mailaddress;
    public String new_mailaddress;
    public String password;
    public String password2;
    public String new_password;
    public String new_password2;
    public String add_user;
    public String change_user;

    public int getCustomer_id(){
        return customer_id;
    }
    public void setCustomer_id(int customer_id){
        this.customer_id=customer_id;
    }

    public String getCustomer_name(){
        return customer_name;
    }
    public void setCustomer_name(String customer_name){
        this.customer_name=customer_name;
    }

    public String getNew_customer_name(){
        return new_customer_name;
    }
    public void setNew_customer_name(String new_customer_name){
        this.new_customer_name=new_customer_name;
    }

    public String getMailaddress(){
        return mailaddress;
    }
    public void setMailaddress(String mailaddress){
        this.mailaddress=mailaddress;
    }

    public String getNew_mailaddress(){
        return new_mailaddress;
    }
    public void setNew_mailaddress(String new_mailaddress){
        this.new_mailaddress=new_mailaddress;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }

    public String getPassword2(){
        return password2;
    }
    public void setPassword2(String password2){
        this.password2=password2;
    }

    public String getNew_password2(){
        return new_password2;
    }
    public void setNew_password2(String new_password2){
        this.new_password2=new_password2;
    }

    public String getNew_password(){
        return new_password;
    }
    public void setNew_password(String new_password){
        this.new_password=new_password;
    }

    public String getAdd_user(){
        return add_user;
    }
    public void setAdd_user(String add_user){
        this.add_user=add_user;
    }

    public String getChange_user(){
        return change_user;
    }
    public void setChange_user(String change_user){
        this.change_user=change_user;
    }
}
