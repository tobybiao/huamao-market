package com.example.huamao.db;

import com.example.huamao.po.BusinessProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/** 商家商品分类
 * @author toby tobytb@163.com
 * @date 2018/5/31 20:02
 */
public interface BusinessProductCategoryRepository extends MongoRepository<BusinessProductCategory, String> {
    /**
     * 通过商家id和该商家商品分类的分类id查找 查找某个商家某分类下的子分类
     * @param businessId 分类所属商家id MongoDB ObjectId
     * @param parentId 分类id MongoDB ObjectId
     * @return 满足条件的商品分类列表
     */
    List<BusinessProductCategory> findAllByBusinessIdAndParentId(String businessId, String parentId);
}
