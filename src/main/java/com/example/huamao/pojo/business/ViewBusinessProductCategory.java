package com.example.huamao.pojo.business;

import java.util.List;
import java.util.Map;

/** 视图层入住商家商品分类
 * @author toby tobytb@163.com
 * @date 2018/5/31 17:57
 */
public class ViewBusinessProductCategory {
    /**
     * 分类所属商家id MongoDB ObjectId
     */
    private String businessId;

    /**
     * 分类所属商家名称
     */
    private String businessName;
    /**
     * 添加商品种类时候选择的父分类
     */
    private List<String> parent;

    /**
     * 商品分类名称
     */
    private String name;

    /**
     * 商品种类描述
     */
    private String description;

    /**
     * 按分类分规格参数模板
     * List {
                 group: '',
                 params: ['','','']
            }
     */
    private List<Map<String, Object>> paramTemplate;

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

    public List<String> getParent() {
        return parent;
    }

    public void setParent(List<String> parent) {
        this.parent = parent;
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

    public List<Map<String, Object>> getParamTemplate() {
        return paramTemplate;
    }

    public void setParamTemplate(List<Map<String, Object>> paramTemplate) {
        this.paramTemplate = paramTemplate;
    }

    @Override
    public String toString() {
        return "ViewBusinessProductCategory{" +
                "businessId='" + businessId + '\'' +
                ", businessName='" + businessName + '\'' +
                ", parent=" + parent +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", paramTemplate=" + paramTemplate +
                '}';
    }
}
