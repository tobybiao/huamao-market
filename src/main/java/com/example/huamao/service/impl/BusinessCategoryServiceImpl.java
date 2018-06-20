package com.example.huamao.service.impl;

import com.example.huamao.db.BusinessCategoryRepository;
import com.example.huamao.po.BusinessCategory;
import com.example.huamao.pojo.VBusinessCategory;
import com.example.huamao.service.BusinessCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author toby tobytb@163.com
 * @date 2018/5/29 20:41
 */
@Service
public class BusinessCategoryServiceImpl implements BusinessCategoryService {
    private static final Logger logger = LoggerFactory.getLogger(BusinessCategoryServiceImpl.class);
    private BusinessCategoryRepository businessCategoryRepository;

    @Autowired
    public BusinessCategoryServiceImpl(BusinessCategoryRepository businessCategoryRepository) {
        this.businessCategoryRepository = businessCategoryRepository;
    }

    /**
     * 添加入住商家分类
     *
     * @param vBusinessCategory 视图层传来的新分类信息
     * @return BusinessCategory 保存到数据库的新分类信息
     */
    @Override
    public BusinessCategory addNewCategory(VBusinessCategory vBusinessCategory) {
        BusinessCategory businessCategory = new BusinessCategory();
        businessCategory.setName(vBusinessCategory.getName());
        businessCategory.setDescription(vBusinessCategory.getDescription());
        businessCategory.setParent(false);  // 新添加的分类信息默认没有子分类

        // 设置祖先分类
        List<Map<String, String>> parents = this.getAncestorsFromStringList(vBusinessCategory.getParents());
        if(null != parents && parents.size() > 0) { // 存在父分类
            businessCategory.setAncestors(parents);
            // 设置直接父分类
            Map<String, String> dirParent = parents.get(parents.size() -1);
            businessCategory.setParentId(dirParent.get("id"));
            businessCategory.setParentName(dirParent.get("name"));
        } else {
            // 不存在父分类
            logger.info("新增分类不存在父分类");
        }
        this.businessCategoryRepository.save(businessCategory);
        logger.info("添加到数据库的入住商家分类数据：" + businessCategory);
        return businessCategory;
    }

    /**
     * 获取所有入住商家分类信息
     *
     * @return 入住商家分类信息<pre>
    [{
        value: '5a5e07d772cb620e64ef485f|服装店',
        label: '服装店',
        children: [
            {
                value: '5a5e07d772cb620e64ef482e|男装专卖',
                label: '男装专卖'
            },
            {
                value: '5a5e07d772cb620e64ef482f|女装专卖',
                label: '女装专卖'
            }
        ]
    }]</pre>
    备注：“|”为英文状态下"|"
     */
    @Override
    public List<Map<String, Object>> getAllCategory() {
        return this.getBusinessCategoryItemParentId(null);
    }

    private List<Map<String, Object>> getBusinessCategoryItemParentId(String parentId) {
        List<BusinessCategory> businessCategoryList = this.businessCategoryRepository.findAllByParentId(parentId);
        List<Map<String, Object>> resultList = new ArrayList<>();
        for(BusinessCategory businessCategory: businessCategoryList) {
            Map<String, Object> resultNode = new HashMap<>();
            resultNode.put("value", businessCategory.getId() + "|" + businessCategory.getName()); // 英文 |
            resultNode.put("label", businessCategory.getName());
            if(businessCategory.getParentId() != null) {
                // 不是顶层分类
                if(businessCategory.isParent()) {
                    // 含有子分类
                    resultNode.put("children", this.getBusinessCategoryItemParentId(businessCategory.getId()));
                } else {
                    // 不含子分类
                    resultNode.put("children", null);
                }
            } else {
                // 顶层分类
                resultNode.put("children", this.getBusinessCategoryItemParentId(businessCategory.getId()));
            }
            resultList.add(resultNode);
        }
        return resultList;
    }

    /**
     * 视图层转po层
     * @param catParents 视图层祖先分类 （5a5e07d772cb620e64ef482e|男装）
     * @return List<Map<String, String>> List {name: "服装专卖",_id: ObjectId("5a5e07d772cb620e64ef485f")}
     */
    private List<Map<String, String>> getAncestorsFromStringList(List<String> catParents){
        List<Map<String, String>> ancestors = new ArrayList<>();
        if(null != catParents && catParents.size() > 0) {
            for(String parent: catParents) {
                Map<String, String> map = new HashMap<String, String>();
                String[] parentIdAndName = parent.split("\\|"); // 数据格式：5a5e07d772cb620e64ef482e|男装
                map.put("name", parentIdAndName[1]);
                map.put("id", parentIdAndName[0]);
                this.setBusinessCategoryIsParent(parentIdAndName[0]); // 设置祖先分类含有子分类
                ancestors.add(map);
            }
        }
        return ancestors;
    }
    /**
     * 根据分类id 设置分类含有子分类
     * @param cid 分类id
     */
    private void setBusinessCategoryIsParent(String cid) {
        BusinessCategory businessCategory = this.businessCategoryRepository.findOne(cid);
        if(!businessCategory.isParent()) {
            businessCategory.setParent(true);
            this.businessCategoryRepository.save(businessCategory);
        }
    }
}
