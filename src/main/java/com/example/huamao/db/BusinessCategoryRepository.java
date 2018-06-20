package com.example.huamao.db;

import com.example.huamao.po.BusinessCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/** 入住商家分类
 * @author toby tobytb@163.com
 * @date 2018/5/29 20:34
 */
public interface BusinessCategoryRepository extends MongoRepository<BusinessCategory, String> {
    /**
     * 通过父分类id查找 查找某个分类下的子分类
     * @param parentId 入住商家父分类id
     * @return 满足条件的入住商家列表
     */
    List<BusinessCategory> findAllByParentId(String parentId);
}
