package com.example.huamao.pojo;

import java.util.List;

/** 对应于视图层添加商品的信息
 * @author toby tobytb@163.com
 * @date 2018/5/21 8:45
 */
public class VMarketGoods {
    /**
     * 商品分类信息（包括id 和分类名称）
     */
    private List<String> categoryIds;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    private Integer stock;

    /**
     * 商品详细规格信息
     */
    private List<ParamTemplateNode> details;

    /**
     * 商品价格
     */
    private Pricing pricing;

    public List<String> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<String> categoryIds) {
        this.categoryIds = categoryIds;
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
        return "VMarketGoods{" +
                "categoryIds=" + categoryIds +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", details=" + details +
                ", pricing=" + pricing +
                '}';
    }
}