package com.example.huamao.po;

import com.example.huamao.pojo.ParamTemplateNode;
import com.example.huamao.pojo.Pricing;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

/** 商场商品信息
 * @author toby tobytb@163.com
 * @date 2018/5/21 16:55
 */
@Document(collection = "t_market_product")
public class MarketProduct {
    @Id
    private String id;
    private String sku;
    private String name;
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

    /**
     * 商品直接父分类
     * <pre>
        {
            name: "食品",
            id: ObjectId("5a5e07d772cb620e64ef485f")
        }</pre>
     */
    private Map<String, String> category;

    /**
     * 祖先分类，不含直接分类 category_ancestors
     */
    @Field("category_ancestors")
    private List<Map<String, String>> categoryAncestors;

    /**
     * 标签
     */
    private List<String> tags;

    /**
     * 缩略图
     */
    private List<String> thumbs;

    /**
     * 图片
     */
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

    public Map<String, String> getCategory() {
        return category;
    }

    public void setCategory(Map<String, String> category) {
        this.category = category;
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
        return "MarketProduct{" +
                "id='" + id + '\'' +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", details=" + details +
                ", pricing=" + pricing +
                ", category=" + category +
                ", categoryAncestors=" + categoryAncestors +
                ", tags=" + tags +
                ", thumbs=" + thumbs +
                ", imgs=" + imgs +
                '}';
    }
}
