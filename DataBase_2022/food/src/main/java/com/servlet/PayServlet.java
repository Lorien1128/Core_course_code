package com.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "payServlet", value = "/pay-servlet")
public class PayServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");
        String json;
        switch (action) {
            case "getOrderMessage": {
                json = getOrderMessage(request);
                break;
            }
            case "getOrderMessageBeforePay": {
                json = getOrderMessageBeforePay(request);
                break;
            }
            case "commitOrder": {
                json = commitOrder(request);
                break;
            }
            default: {
                json = "";
                break;
            }
        }
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    public String getOrderMessage(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("diner-name", "的服务哦厚非");
        map.put("diner-phone", 1930812);
        map.put("dx", 12.4);
        map.put("dy", 5.321);
        map.put("restaurant-name", "可否看到我");
        map.put("restaurant-phone", 122902);
        map.put("rx", 45.41);
        map.put("ry", 32);
        map.put("deliverer-name", "额济纳");
        map.put("deliverer-phone", 340932343);
        map.put("time", "2022-12-04 16:55:56");
        map.put("state", "配送中");
        map.put("deliverFee", 32.4);
        map.put("price", 324);
        map.put("coupon", true);
        map.put("sill", 200);
        map.put("discount", 30);
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> m1 = new HashMap<>();
        m1.put("name", "豆腐脑儿");
        m1.put("amount", 10);
        m1.put("price", 120);
        list.add(m1);
        HashMap<String, Object> m2 = new HashMap<>();
        m2.put("name", "溶解菲欧");
        m2.put("amount", 3);
        m2.put("price", 45.3);
        list.add(m2);
        map.put("dishes", list);
        return new Gson().toJson(map);
    }

    public String getOrderMessageBeforePay(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("diner-name", "的服务哦厚非");
        map.put("diner-phone", 1930812);
        map.put("dx", 12.4);
        map.put("dy", 5.321);
        map.put("restaurant-name", "低于过一次");
        map.put("restaurant-phone", 122902);
        map.put("rx", 45.41);
        map.put("ry", 32);
        map.put("tid", 45981);
        map.put("deliverer-name", "额济纳");
        map.put("deliverer-phone", 340932343);
        map.put("deliverFee", 32.4);
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> m1 = new HashMap<>();
        m1.put("cid", 32984);
        m1.put("sill", 200);
        m1.put("discount", 20);
        list.add(m1);
        HashMap<String, Object> m2 = new HashMap<>();
        m2.put("cid", 6445);
        m2.put("sill", 120);
        m2.put("discount", 10);
        list.add(m2);
        map.put("coupons", list);
        return new Gson().toJson(map);
    }

    public String commitOrder(HttpServletRequest request) {
        Type type = new TypeToken<ArrayList<HashMap<String, String>>>(){}.getType();
        ArrayList<HashMap<String, String>> list =
                new Gson().fromJson(request.getParameter("dishes"), type);
        for (HashMap<String, String> map : list) {
            System.out.println(map.get("did") + ": " + map.get("amount"));
        }
        System.out.println(request.getParameter("cid"));
        return "";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }
}
