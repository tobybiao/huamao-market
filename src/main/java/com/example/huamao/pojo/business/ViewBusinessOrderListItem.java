package com.example.huamao.pojo.business;

import com.example.huamao.pojo.ViewOrderGoodItem;

import java.util.List;

/** 商家订单列表项
 * @author toby tobytb@163.com
 * @date 2018/6/4 10:29
 */
public class ViewBusinessOrderListItem {
    /**
     * 订单编号 mongodb ObjectId
     */
    private String id;

    /**
     * 订单总额
     */
    private long totalAmount;

    /**
     * 支付方式
     */
    private String payment;

    /**
     * 订单包含的商品项
     */
    private List<ViewOrderGoodItem> items;

    /**
     * 订单生成日期
     */
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<ViewOrderGoodItem> getItems() {
        return items;
    }

    public void setItems(List<ViewOrderGoodItem> items) {
        this.items = items;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ViewBusinessOrderListItem{" +
                "id='" + id + '\'' +
                ", totalAmount=" + totalAmount +
                ", payment='" + payment + '\'' +
                ", items=" + items +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
