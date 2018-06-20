package com.example.huamao.pojo;

import java.util.ArrayList;

/**
 * 商品规格模板 数据库
 *
 * @author toby tobytb@163.com
 * @date 2018/5/17 10:30
 */
public class ParamTemplate {
    /**
     * 规格组名
     */
    private String group;

    /**
     * 规格名称列表
     */
    private ArrayList<String> params;

    public ParamTemplate() {
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public ArrayList<String> getParams() {
        return params;
    }

    public void setParams(ArrayList<String> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "ParamTemplate{" +
                "group='" + group + '\'' +
                ", params=" + params +
                '}';
    }
}
