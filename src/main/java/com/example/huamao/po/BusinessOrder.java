package com.example.huamao.po;

import com.example.huamao.pojo.ViewOrderGoodItem;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/** 入住商家订单
 * @author toby tobytb@163.com
 * @date 2018/6/4 7:48
 */
@Document(collection = "t_business_order")
public class BusinessOrder {
    @Id
    private String id;

    /**
     * 收款商家id mongoDB ObjectId
     */
    @Field("business_id")
    private String businessId;

    /**
     * 收款商家名称
     */
    @Field("business_name")
    private String businessName;

    /**
     * 收款方式：现金、微信、支付宝
     */
    private String payment;

    private List<ViewOrderGoodItem> items;

    /**
     * 订单总额
     */
    @Field("total_amount")
    private long totalAmount;

    /**
     * 订单创建日期
     */
    @Field("create_time")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "BusinessOrder{" +
                "id='" + id + '\'' +
                ", businessId='" + businessId + '\'' +
                ", businessName='" + businessName + '\'' +
                ", payment='" + payment + '\'' +
                ", items=" + items +
                ", totalAmount=" + totalAmount +
                ", createTime=" + createTime +
                '}';
    }
}
