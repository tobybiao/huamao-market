package com.example.huamao.pojo;

import java.util.List;
import java.util.Map;

/** 商场商品列表 商品项信息
 * @author toby tobytb@163.com
 * @date 2018/5/23 11:07
 */
public class VMarketGoodsListNode {
    /**
     * 商品id
     */
    private String id;
    /**
     * 商品sku
     */
    private String sku;
    private String name;
    private String description;
    private Integer stock;
    private Pricing pricing;

    /** 商品分类id 数组
     * <pre>['5a5e07d772cb620e64ef485f', '5a5e07d772cb620e64ef487k', '5a5e07d772cb620e64ef488e', '5a5e07d772cb620e64ef483b']</pre>
     */
    private List<String> category;

    /**
     * 分类标签 ['食品', '新鲜水果', '苹果', '红富士']
     */
    private List<String> categoryLabel;
    /**
     * 直接分类
     * <pre>{
     id: '5a5e07d772cb620e64ef483b',
     name: '红富士'
     }</pre>
     */
    private Map<String, String> primaryCategory;

    /**
     * 商品父分类，不含直接分类
     */
    private List<Map<String, String>> categoryAncestors;
    private List<String> tags;
    private List<String> thumbs;
    private List<String> imgs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(List<String> categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public Map<String, String> getPrimaryCategory() {
        return primaryCategory;
    }

    public void setPrimaryCategory(Map<String, String> primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public List<Map<String, String>> getCategoryAncestors() {
        return categoryAncestors;
    }

    public void setCategoryAncestors(List<Map<String, String>> categoryAncestors) {
        this.categoryAncestors = categoryAncestors;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getThumbs() {
        return thumbs;
    }

    public void setThumbs(List<String> thumbs) {
        this.thumbs = thumbs;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    @Override
    public String toString() {
        return "VMarketGoodsListNode{" +
                "id='" + id + '\'' +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", pricing=" + pricing +
                ", category=" + category +
                ", categoryLabel=" + categoryLabel +
                ", primaryCategory=" + primaryCategory +
                ", categoryAncestors=" + categoryAncestors +
                ", tags=" + tags +
                ", thumbs=" + thumbs +
                ", imgs=" + imgs +
                '}';
    }
}
