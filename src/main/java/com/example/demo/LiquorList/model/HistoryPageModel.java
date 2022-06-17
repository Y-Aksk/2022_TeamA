package com.example.demo.LiquorList.model;

import java.util.List;

import com.example.demo.LiquorList.repository.HistoryModel;

public class HistoryPageModel {
     /**
    *　タイトル
    */
    public String title;
    public String name;
    
    /**
     * 顧客一覧
     */
    public List<HistoryModel> list;

    public int product_id;
    public int category_id;
    public String product_image;
    public String product_name;
    public int product_price;
    public String add_time;
    public int customer_id;
    

    public int getProduct_id(){
        return product_id;
    }
    public void setProduct_id(int product_id){
        this.product_id =product_id;
    }
    public int getCategory_id(){
        return category_id;
    }
    public void setCategory_id(int category_id){
        this.category_id =category_id;
    }
    public String getProduct_name(){
        return product_name;
    }
    public void setProduct_name(String product_name){
        this.product_name =product_name;
    }
    public String getProduct_image(){
        return product_image;
    }
    public void setProduct_image(String product_image){
        this.product_image =product_image;
    }
    public int getProduct_price(){
        return product_price;
    }
    public void setProduct_price(int product_price){
        this.product_price =product_price;
    }

    public String getAdd_time(){
        return add_time;
    }
    public void setAdd_time(String add_time){
        this.add_time = add_time;
    }

    public int getCustomer_id(){
        return customer_id;
    }
    public void setCustomer_id(int customer_id){
        this.customer_id = customer_id;
    }

}
