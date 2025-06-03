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

@WebServlet(name = "restaurantServlet", value = "/restaurant-servlet")
public class RestaurantServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");
        String json;
        switch (action) {
            case "getNameAndRate": {
                json = getNameAndRate(request);
                break;
            }
            case "initDishes": {
                json = initDishes(request);
                break;
            }
            case "getLocation": {
                json = getLocation(request);
                break;
            }
            case "delDish": {
                json = delDish(request);
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

    public String getNameAndRate(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "让我返回偶尔");
        map.put("rate", 4.3);
        return new Gson().toJson(map);
    }

    public String initDishes(HttpServletRequest request) {
        final ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        HashMap<String, Object> m1 = new HashMap<>();
        m1.put("did", 320982);
        m1.put("name", "为off肉");
        m1.put("style", "鲁菜");
        m1.put("meat", "荤");
        m1.put("sales", 3290);
        m1.put("price", 232.2);
        final ArrayList<HashMap<String, Object>> ll1 = new ArrayList<>();
        HashMap<String, Object> mm1 = new HashMap<>();
        mm1.put("mid", 3092);
        mm1.put("name", "耦合服务");
        mm1.put("weight", 320);
        ll1.add(mm1);
        HashMap<String, Object> mm2 = new HashMap<>();
        mm2.put("mid", 5922);
        mm2.put("name", "UR恶化发");
        mm2.put("weight", 1010);
        ll1.add(mm2);
        m1.put("material", ll1);
        list.add(m1);
        HashMap<String, Object> m2 = new HashMap<>();
        m2.put("did", 54904);
        m2.put("name", "抚王逢年");
        m2.put("style", "湘菜");
        m2.put("meat", "素");
        m2.put("sales", 43);
        m2.put("price", 33);
        ArrayList<HashMap<String, Object>> ll2 = new ArrayList<>();
        ll2.add(mm2);
        m2.put("material", ll2);
        list.add(m2);
        return new Gson().toJson(list);
    }

    public String getLocation(HttpServletRequest request) {
        System.out.println(request.getParameter("mid"));
        HashMap<String, String> map = new HashMap<>();
        map.put("origin", "东湖UI");
        return new Gson().toJson(map);
    }

    public String delDish(HttpServletRequest request) {
        System.out.println(request.getParameter("rid") + ", " +
                request.getParameter("did"));
        return "";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }
}
