package com.example.huamao.pojo;

/** 视图层订单商品项
 * @author toby tobytb@163.com
 * @date 2018/6/4 7:07
 */
public class ViewOrderGoodItem {
    /**
     * 商品id mongoDB ObjectId
     */
    private String id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品唯一sku编号
     */
    private String sku;

    /**
     * 订单项购买数量
     */
    private int quantity;

    /**
     * 单价 单位是 分
     */
    private long univalence;

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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getUnivalence() {
        return univalence;
    }

    public void setUnivalence(long univalence) {
        this.univalence = univalence;
    }

    @Override
    public String toString() {
        return "ViewOrderGoodItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", quantity=" + quantity +
                ", univalence=" + univalence +
                '}';
    }
}
