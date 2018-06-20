package com.example.huamao.service;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.po.BusinessProduct;
import com.example.huamao.pojo.business.ViewBusinessGoods;

import java.util.Map;

/** 商家商品服务接口
 * @author toby tobytb@163.com
 * @date 2018/6/1 20:09
 */
public interface BusinessProductService {
    /**
     * 根据客户端传来的商品信息构成数据库相应结构后保存
     * @param viewBusinessGoods 客户端传来的商品信息
     * @return 保存到数据库中商家商品数据
     */
    BusinessProduct saveProduct(ViewBusinessGoods viewBusinessGoods);

    /**
     * 根据客户端传来的商品信息更新商品信息
     * @param viewBusinessGoods 客户端传来的商品信息 包含商品id
     * @return 保存到数据库中商家商品数据
     */
    BusinessProduct updateProduct(ViewBusinessGoods viewBusinessGoods);

    /**
     * 通过分页查找所有商品
     * @param businessId 商家id mongoDB ObjectId
     * @param pageIndex 页码
     * @param pageSize 每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    Map<String, Object> findAllByPage(String businessId, int pageIndex, int pageSize);

    /**
     * 通过商品名称模糊查询，分页展示
     * @param name 商品名称
     * @param businessId 商家id mongoDB ObjectId
     * @param pageIndex 页码
     * @param pageSize 每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    Map<String, Object> findAllByNameAndPage(String name, String businessId, int pageIndex, int pageSize);

    /**
     * 通过商品id mongoDB ObjectId 删除商品
     * @param id 商品id mongoDB ObjectId
     * @return 删除商品信息
     */
    BusinessProduct deleteById(String id);

    /**
     * 通过唯一的 商品sku查找商品信息
     * @param sku 商品sku
     * @return BusinessProduct
     */
    BusinessProduct findBySku(String sku);
}
