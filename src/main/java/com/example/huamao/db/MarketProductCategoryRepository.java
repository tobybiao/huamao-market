package com.example.huamao.db;

import com.example.huamao.po.MarketProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author toby tobytb@163.com
 * @date 2018/5/17 13:12
 */
public interface MarketProductCategoryRepository extends MongoRepository<MarketProductCategory, String> {
    List<MarketProductCategory> findAllByParentId(String parentId);
}
