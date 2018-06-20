package com.example.huamao.pojo;

import java.util.List;
import java.util.Map;

/**
 * 响应给视图的参数模板格式
 * @author toby tobytb@163.com
 * @date 2018/5/20 0:30
 */
// [
//      {
//          group: '主体',
//          params: [
//                    {
//                      key: '保质期',
//                      value: ''
//                    },
//                    {
//                      key: '净含量',
//                      value: ''
//                    },
//                    {
//                      key: '贮存条件',
//                      value: ''
//                    },
//                    {
//                      key: '包装清单',
//                      value: ''
//                    }
//                    ]
//                    },
//      {
//           group: '特色功能',
//           params: [
//                    {
//                      key: '净含量',
//                      value: ''
//                    },
//                    {
//                      key: '保质期',
//                      value: ''
//                    }
//                   ]
//      }
// ]

public class ParamTemplateNode {
    /**
     * 组名
     */
    private String group;

    /**
     * 模板参数列表 {key, value}
     */
    private List<Map<String, String>> params;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<Map<String, String>> getParams() {
        return params;
    }

    public void setParams(List<Map<String, String>> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "ParamTemplateNode{" +
                "group='" + group + '\'' +
                ", params=" + params +
                '}';
    }
}
