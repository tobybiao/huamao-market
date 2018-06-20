package com.example.huamao.po;

import com.example.huamao.pojo.ParamTemplate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Map;

/** 商场商品分类信息
 * @author toby tobytb@163.com
 * @date 2018/5/17 11:03
 */
@Document(collection = "t_market_product_category")
public class MarketProductCategory {
    @Id
    private String id;
    private String name;
    private String description;

    /**
     * 直接父分类id
     */
    @Field("parent_id")
    private String parentId;

    /**
     * 直接父分类名称
     */
    @Field("parent_name")
    private String parentName;

    /**
     * 是否为父分类（是否有子分类）
     */
    @Field("is_parent")
    private boolean isParent;
    /**
     * 所有父分类(id, name)
     */
    private ArrayList<Map<String, String>> ancestors;
    /**
     * 分类规格模板信息
     */
    @Field("param_templates")
    private ArrayList<ParamTemplate> paramTemplates;

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

    public ArrayList<Map<String, String>> getAncestors() {
        return ancestors;
    }

    public void setAncestors(ArrayList<Map<String, String>> ancestors) {
        this.ancestors = ancestors;
    }

    public ArrayList<ParamTemplate> getParamTemplates() {
        return paramTemplates;
    }

    public void setParamTemplates(ArrayList<ParamTemplate> paramTemplates) {
        this.paramTemplates = paramTemplates;
    }

    @Override
    public String toString() {
        return "MarketProductCategory{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parentId='" + parentId + '\'' +
                ", parentName='" + parentName + '\'' +
                ", isParent=" + isParent +
                ", ancestors=" + ancestors +
                ", paramTemplates=" + paramTemplates +
                '}';
    }
}
