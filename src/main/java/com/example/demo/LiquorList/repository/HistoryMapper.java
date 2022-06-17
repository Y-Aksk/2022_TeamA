package com.example.demo.LiquorList.repository;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface HistoryMapper {
    //データ取得時並び方は事前に指定おく必要あり 
    @Select("SELECT * FROM product LEFT OUTER JOIN history ON product.product_id = history.product_id ORDER BY history.add_time")
    public List<HistoryModel>findHistory();

   //リスト 追加新しい順
   @Select("select history.product_id, customer_id, product_image, product_name, product_price from product LEFT OUTER JOIN history ON product.product_id = history.product_id where history.customer_id = #{id} order by history.table_id")
   public List<HistoryModel> findHistoryId(int id);
}
