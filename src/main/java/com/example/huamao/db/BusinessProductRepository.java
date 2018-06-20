package com.example.huamao.db;

import com.example.huamao.po.BusinessProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author toby tobytb@163.com
 * @date 2018/6/1 20:07
 */
public interface BusinessProductRepository extends MongoRepository<BusinessProduct, String> {
    /**
     * 通过唯一的 商品sku查找商品信息
     * @param sku 商品sku
     * @return BusinessProduct
     */
    BusinessProduct findBySku(String sku);
}
