package com.example.huamao.po;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

/** 商家商品分类信息
 * @author toby tobytb@163.com
 * @date 2018/5/31 18:29
 */
@Document(collection = "t_business_product_category")
public class BusinessProductCategory {
    /**
     * MongoDB ObjectId
     */
    @Id
    private String id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 分类所属商家id MongoDB ObjectId
     */
    @Field("business_id")
    private String businessId;

    /**
     * 分类所属商家名称
     */
    @Field("business_name")
    private String businessName;

    /**
     * 直接父分类id  MongoDB ObjectId
     */
    @Field("parent_id")
    private String parentId;

    /**
     * 直接父分类名称
     */
    @Field("parent_name")
    private  String parentName;

    /**
     * 是否有子分类 默认没有 false
     */
    @Field("is_parent")
    private boolean isParent;

    /**
     * 祖先分类，包含直接分类
     [
         {
             name: "男装",
             id: ObjectId("5a5e07d772cb620e64ef488e"),
         }
     ]
     */
    private List<Map<String, String>> ancestors;

    /**
     * 按分类划分的规格模板
     * [
     {
     group: '版型款式',
     params:['袖长', '领型', '版型']
     },
     {
     group: '关键信息',
     params: ['上市年份季节', '厚薄', '材质成分', '销售渠道类型', '品牌', '基础风格']
     },
     {
     group: '其他',
     params: ['适用场景']
     }
     ]
     */
    @Field("param_templates")
    private List<Map<String, Object>> paramTemplates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public List<Map<String, String>> getAncestors() {
        return ancestors;
    }

    public void setAncestors(List<Map<String, String>> ancestors) {
        this.ancestors = ancestors;
    }

    public List<Map<String, Object>> getParamTemplates() {
        return paramTemplates;
    }

    public void setParamTemplates(List<Map<String, Object>> paramTemplates) {
        this.paramTemplates = paramTemplates;
    }

    @Override
    public String toString() {
        return "BusinessProductCategory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", businessId='" + businessId + '\'' +
                ", businessName='" + businessName + '\'' +
                ", parentId='" + parentId + '\'' +
                ", parentName='" + parentName + '\'' +
                ", isParent=" + isParent +
                ", ancestors=" + ancestors +
                ", paramTemplates=" + paramTemplates +
                '}';
    }
}
