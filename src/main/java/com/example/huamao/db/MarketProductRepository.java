package com.example.huamao.db;

import com.example.huamao.po.MarketProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author toby tobytb@163.com
 * @date 2018/5/23 9:53
 */
public interface MarketProductRepository extends MongoRepository<MarketProduct, String> {
}
