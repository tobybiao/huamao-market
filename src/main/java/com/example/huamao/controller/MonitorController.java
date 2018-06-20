package com.example.huamao.controller;

import com.alibaba.fastjson.JSON;
import com.example.huamao.common.pojo.ISessionKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/** 系统指标监控器 通过SSE（Server-Sent Events 服务器发送事件）
 * @author toby tobytb@163.com
 * @date 2018/5/26 16:32
 */
@Controller
@RequestMapping("/monitoring")
public class MonitorController {
    @Value("${LOGIN_NUMBER}")
    private String LOGIN_NUMBER;
    @RequestMapping(value = "/onlineNumber", produces="text/event-stream;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String getOnlineNumber(HttpServletRequest request) {
        Map<String,Object> perMinRecord = new HashMap<>();

        Calendar calendar = Calendar.getInstance();
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        perMinRecord.put("时间", minute+":"+second);
        Integer loginNumber = (Integer) request.getServletContext().getAttribute(this.LOGIN_NUMBER);
//        Random random = new Random();
//        perMinRecord.put("在线人数", random.nextInt(50));
        perMinRecord.put("在线人数", loginNumber);
        String result = "data:" + JSON.toJSONString(perMinRecord) + "\n\n";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/pressure", produces="text/event-stream;charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String getSystemPressure() {
        Random random = new Random();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = "data:" + ((Integer)random.nextInt(100)).toString() + "\n\n";
        return result;
    }
}
