package com.example.huamao.pojo.business;

import com.example.huamao.pojo.ParamTemplateNode;
import com.example.huamao.pojo.Pricing;

import java.util.List;

/** 视图层添加的商品信息
 * @author toby tobytb@163.com
 * @date 2018/6/1 20:11
 */
public class ViewBusinessGoods {
    /**
     * 分类所属商家id MongoDB ObjectId
     */
    private String businessId;

    /**
     * 分类所属商家名称
     */
    private String businessName;
    /**
     * 商品分类信息（包括id 和分类名称）
     */
    private List<String> category;

    /**
     * 商品id
     */
    private String id;
    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 库存量
     */
    private Integer stock;
    /**
     * 商品详细规格信息
     */
    private List<ParamTemplateNode> details;

    /**
     * 商品价格
     */
    private Pricing pricing;

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

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<ParamTemplateNode> getDetails() {
        return details;
    }

    public void setDetails(List<ParamTemplateNode> details) {
        this.details = details;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    @Override
    public String toString() {
        return "ViewBusinessGoods{" +
                "businessId='" + businessId + '\'' +
                ", businessName='" + businessName + '\'' +
                ", category=" + category +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", details=" + details +
                ", pricing=" + pricing +
                '}';
    }
}
