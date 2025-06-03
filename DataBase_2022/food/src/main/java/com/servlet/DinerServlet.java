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

@WebServlet(name = "dinerServlet", value = "/diner-servlet")
public class DinerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String what = request.getParameter("what");
        String mode = request.getParameter("mode");
        String json;
        final ArrayList<HashMap<String, Object>> set = new ArrayList<>();
        if (what.equals("restaurant")) {
            HashMap<String, Object> r1 = new HashMap<>();
            r1.put("rid", 2130);
            r1.put("name", "华莱士");
            r1.put("rate", 2.0);
            r1.put("kind", "快餐");
            r1.put("dist", 25.2);
            set.add(r1);
            HashMap<String, Object> r2 = new HashMap<>();
            r2.put("rid", 2130);
            r2.put("name", "千岛湖");
            r2.put("rate", 4.2);
            r2.put("kind", "中餐");
            r2.put("dist", 41.0);
            set.add(r2);
            HashMap<String, Object> r3 = new HashMap<>();
            r3.put("rid", 13);
            r3.put("name", "否欧文就");
            r3.put("rate", 0.4);
            r3.put("kind", "中餐");
            r3.put("dist", 123.1);
            set.add(r3);
            json = new Gson().toJson(set);
        }
        else {
            HashMap<String, Object> r1 = new HashMap<>();
            r1.put("rid", 2130);
            r1.put("restaurant", "皮蛋区西大街");
            r1.put("name", "哦IWC欧文非常难");
            r1.put("style", "川菜");
            r1.put("meat", "荤");
            r1.put("sales", 121313);
            r1.put("price", 328.1);
            set.add(r1);
            HashMap<String, Object> r2 = new HashMap<>();
            r2.put("rid", 2130);
            r2.put("restaurant", "IE此ID那几位");
            r2.put("name", "都是能存储");
            r2.put("style", "粤菜");
            r2.put("meat", "素");
            r2.put("sales", 2930);
            r2.put("price", 11.9);
            set.add(r2);
            json = new Gson().toJson(set);
        }
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }
}
