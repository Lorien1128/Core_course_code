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

@WebServlet(name = "dishServlet", value = "/dish-servlet")
public class DishServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");
        String json;
        switch (action) {
            case "addGlobalDish": {
                json = addGlobalDish(request);
                break;
            }
            case "restaurantAddDish": {
                json = restaurantAddDish(request);
                break;
            }
            case "getAllDishes": {
                json = getAllDishes(request);
                break;
            }
            case "updatePrice": {
                json = updatePrice(request);
                break;
            }
            case "initUpdatePage": {
                json = initUpdatePage(request);
                break;
            }
            case "addGlobalMaterial": {
                json = addGlobalMaterial(request);
                break;
            }
            case "removeMaterial": {
                json = removeMaterial(request);
                break;
            }
            case "saveMaterial": {
                json = saveMaterial(request);
                break;
            }
            case "updateMaterialWeight": {
                json = updateMaterialWeight(request);
                break;
            }
            case "getAllMaterials": {
                json = getAllMaterials(request);
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

    public String addGlobalDish(HttpServletRequest request) {
        System.out.println(request.getParameter("meat"));
        return "";
    }

    public String restaurantAddDish(HttpServletRequest request) {
        System.out.println(request.getParameter("did"));
        return "";
    }

    public String updatePrice(HttpServletRequest request) {
        return "";
    }

    public String getAllDishes(HttpServletRequest request) {
        String rid = request.getParameter("rid");
        HashMap<Integer, String> list = new HashMap<>();
        list.put(231, "饿哦农二");
        list.put(43932, "访问你发错");
        list.put(37298, "陪我哪都");
        return new Gson().toJson(list);
    }

    public String initUpdatePage(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "金牛万达");
        map.put("price", 23.1);
        ArrayList<HashMap<String, Object>> material = new ArrayList<>();
        HashMap<String, Object> m1 = new HashMap<>();
        m1.put("name", "宿建德江");
        m1.put("mid", 3928);
        m1.put("weight", 2930);
        material.add(m1);
        HashMap<String, Object> m2 = new HashMap<>();
        m2.put("name", "动物农场");
        m2.put("mid", 2093);
        m2.put("weight", 540);
        material.add(m2);
        map.put("material", material);
        return new Gson().toJson(map);
    }

    public String addGlobalMaterial(HttpServletRequest request) {
        return "";
    }

    public String removeMaterial(HttpServletRequest request) {
        String mid = request.getParameter("mid");
        String did = request.getParameter("did");
        System.out.println(did + ", " + mid);
        return "";
    }

    public String saveMaterial(HttpServletRequest request) {
        String mid = request.getParameter("mid");
        String did = request.getParameter("did");
        String weight = request.getParameter("weight");
        System.out.println("insert: " + did + ", " + mid + ", " + weight);
        return "";
    }

    public String updateMaterialWeight(HttpServletRequest request) {
        System.out.println("update");
        return "";
    }

    public String getAllMaterials(HttpServletRequest request) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(3928, "宿建德江");
        map.put(2093, "动物农场");
        return new Gson().toJson(map);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }
}
