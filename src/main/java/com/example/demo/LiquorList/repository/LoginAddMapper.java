package com.example.demo.LiquorList.repository;

import java.util.List;
import org.apache.ibatis.annotations.*;

public interface LoginAddMapper {
    
    @Insert("insert into customer(customer_name,mailaddress,password,add_user,change_user)values(#{customer_name},#{mailaddress},#{password},#{customer_name},#{customer_name})")
    public void insert(
        @Param("customer_name")String customer_name,
        @Param("mailaddress")String mailaddress,
        @Param("password")String password
    );
    
}
