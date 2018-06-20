package com.example.huamao.service;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.po.MarketProduct;
import com.example.huamao.pojo.VMarketGoods;
import com.example.huamao.pojo.VMarketGoodsListNode;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/** 商场商品服务接口
 * @author toby tobytb@163.com
 * @date 2018/5/21 18:01
 */
public interface MarketProductService {
    /**
     * 根据客户端传来的商品信息构成数据库相应结构后保存
     * @param marketGoods 客户端传来的商品信息
     * @return 包含商品信息的响应结果
     */
    GenericResult saveProduct(VMarketGoods marketGoods);

    /**
     * 查找所有商品
     * @return List<MarketProduct>
     */
    List<VMarketGoodsListNode> findAll();

    /**
     * 通过分页查找所有商品
     * @param pageIndex 页码
     * @param pageSize 每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    Map<String, Object> findAllByPage(int pageIndex, int pageSize);

    /**
     * 通过商品名称模糊查询，分页展示
     * @param name 商品名称
     * @param pageIndex 页码
     * @param pageSize 每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    Map<String, Object> findAllByNameAndPage(String name, int pageIndex, int pageSize);
}
