package com.example.demo.LiquorList.model;

import java.util.List;

import com.example.demo.LiquorList.repository.LiquorModel;


/**
 * LiquorModel
 */
public class LiquorPageModel {
    /**
    *　タイトル
    */
    public String title;
    public String name;
    
    /**
     * 顧客一覧
     */
    public List<LiquorModel> list;

    public int product_id;
    public int category_id;
    public String product_image;
    public String product_name;
    public int product_price;
    public double abv;
    public int volume;
    public String product_detail;
    

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
    public double getAbv(){
        return abv;
    }
    public void setAbv(double abv){
        this.abv = abv;
    }

    public int getVolume(){
        return volume;
    }
    public void setVolume(int volume){
        this.volume = volume;
    }
    public String getProduct_detail(){
        return product_detail;
    }
    public void setProduct_detail(String product_detail){
        this.product_detail =product_detail;
    }


}