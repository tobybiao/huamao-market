package com.example.huamao.pojo;

import java.util.ArrayList;

/** 添加商场商品新分类信息
 * @author toby tobytb@163.com
 * @date 2018/5/17 9:28
 */
public class MarketGoodsNewCategory {
    /**
     * 添加商品种类时候选择的父分类
     */
    private ArrayList<String> parent_ids;

    /**
     * 添加的商品种类名称
     */
    private String name;

    /**
     * 添加商品种类的描述
     */
    private String description;

    /**
     * 分类规格模板信息
     */
    private ArrayList<ParamTemplate> param_templates;

    public MarketGoodsNewCategory() {
    }

    public ArrayList<String> getParent_ids() {
        return parent_ids;
    }

    public void setParent_ids(ArrayList<String> parent_ids) {
        this.parent_ids = parent_ids;
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

    public ArrayList<ParamTemplate> getParam_templates() {
        return param_templates;
    }

    public void setParam_templates(ArrayList<ParamTemplate> param_templates) {
        this.param_templates = param_templates;
    }

    @Override
    public String toString() {
        return "MarketGoodsNewCategory{" +
                "parent_ids=" + parent_ids +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", param_templates=" + param_templates +
                '}';
    }
}
