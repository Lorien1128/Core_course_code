package com.servlet;

import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "orderServlet", value = "/order-servlet")
public class OrderServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");
        String json;
        switch (action) {
            case "initDinerOrder": {
                json = initDinerOrder(request);
                break;
            }
            case "initRestaurantOrder": {
                json = initRestaurantOrder(request);
                break;
            }
            case "initNewDelivererOrder": {
                json = initNewDelivererOrder(request);
                break;
            }
            case "initAllDelivererOrder": {
                json = initAllDelivererOrder(request);
                break;
            }
            case "finishOrder": {
                json = finishOrder(request);
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

    public String initDinerOrder(HttpServletRequest request) {
        final ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("name", "鹅肉及饿哦");
        map1.put("time", "2022-12-04 15:53:56");
        map1.put("totalPrice", 10.22);
        map1.put("state", "配送中");
        map1.put("oid", 7530);
        list.add(map1);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("name", "分风冷方面");
        map2.put("time", "2022-12-04 16:55:56");
        map2.put("totalPrice", 232.10);
        map2.put("state", "已完成");
        map2.put("oid", 2302);
        list.add(map2);
        return new Gson().toJson(list);
    }

    public String initRestaurantOrder(HttpServletRequest request) {
        final ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("name", "user121");
        map1.put("time", "2022-12-04 15:53:56");
        map1.put("totalPrice", 10.22);
        map1.put("state", "配送中");
        map1.put("oid", 7530);
        list.add(map1);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("name", "woj成绩我");
        map2.put("time", "2022-12-04 16:55:56");
        map2.put("totalPrice", 232.10);
        map2.put("state", "已完成");
        map2.put("oid", 2302);
        list.add(map2);
        return new Gson().toJson(list);
    }

    public String initNewDelivererOrder(HttpServletRequest request) {
        final ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("restaurant", "份额欧文撇");
        map1.put("diner", "user121");
        map1.put("time", "2022-12-04 15:53:56");
        map1.put("deliverFee", 10.22);
        map1.put("oid", 7530);
        map1.put("rx", 22.22);
        map1.put("ry", 54.1);
        map1.put("dx", 12.3);
        map1.put("dy", 33);
        list.add(map1);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("restaurant", "of杰尔夫");
        map2.put("diner", "woj成绩我");
        map2.put("time", "2022-12-04 16:55:56");
        map2.put("deliverFee", 32.10);
        map2.put("oid", 2302);
        map2.put("rx", 3.23);
        map2.put("ry", 45.5);
        map2.put("dx", 13.6);
        map2.put("dy", 534.5);
        list.add(map2);
        return new Gson().toJson(list);
    }

    public String initAllDelivererOrder(HttpServletRequest request) {
        final ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("restaurant", "份额欧文撇");
        map1.put("diner", "user121");
        map1.put("time", "2022-12-04 15:53:56");
        map1.put("state", "配送中");
        map1.put("deliverFee", 10.22);
        map1.put("oid", 7530);
        list.add(map1);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("restaurant", "of杰尔夫");
        map2.put("diner", "woj成绩我");
        map2.put("time", "2022-12-04 16:55:56");
        map2.put("state", "已完成");
        map2.put("deliverFee", 32.10);
        map2.put("oid", 2302);
        list.add(map2);
        return new Gson().toJson(list);
    }

    public String finishOrder(HttpServletRequest request) {
        String oid = request.getParameter("oid");
        System.out.println(oid);
        return "";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }
}
