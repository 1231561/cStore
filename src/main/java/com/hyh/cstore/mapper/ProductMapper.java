package com.hyh.cstore.mapper;

import com.hyh.cstore.entity.Product;

import java.util.List;
/**商品mapper接口*/
public interface ProductMapper {
    /**查询热销商品
     * @return
     */
    List<Product> findHotList();

    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    Product findById(Integer id);
}
