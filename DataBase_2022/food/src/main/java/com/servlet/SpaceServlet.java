package com.servlet;

import com.google.gson.Gson;
import com.inter.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "spaceServlet", value = "/space-servlet")
public class SpaceServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");
        String json;
        switch (action) {
            case "initPage": {
                json = initPage(request);
                break;
            }
            case "changeVIP": {
                json = changeVip(request);
                break;
            }
            case "addToAccount": {
                json = addToAccount(request);
                break;
            }
            case "save": {
                json = save(request);
                break;
            }
            case "withdraw": {
                json = withdraw(request);
                break;
            }
            case "changeWorkState": {
                json = changeWorkState(request);
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

    public String initPage(HttpServletRequest request) {
        if (request.getParameterMap().containsKey("uid")) {
            int uid = Integer.parseInt(request.getParameter("uid"));
            return new User().queryMessage(uid);
        }
        else if (request.getParameterMap().containsKey("rid")) {
            HashMap<String, String> map = new HashMap<>();
            map.put("kind", "快餐");
            map.put("rate", "3.6");
            return new Gson().toJson(map);
        }
        else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("salary", 12000);
            map.put("state", "off");
            return new Gson().toJson(map);
        }
    }

    public String changeVip(HttpServletRequest request) {
        int uid = Integer.parseInt(request.getParameter("uid"));
        return new User().changeVip(uid);
    }

    public String addToAccount(HttpServletRequest request) {
        int uid = Integer.parseInt(request.getParameter("uid"));
        long money = Integer.parseInt(request.getParameter("money"));
        return new User().recharge(uid, money);
    }

    public String save(HttpServletRequest request) {
        System.out.println(request.getParameter("kind"));
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String phone = request.getParameter("phone");
        double x = Double.parseDouble(request.getParameter("pos-x"));
        double y = Double.parseDouble(request.getParameter("pos-y"));
        String role = request.getParameter("role");
        switch (role) {
            case "diner": {
                return new User().messageChange(id, name, pass, phone, x, y);
            }
            case "restaurant":
            default: {
                return "";
            }
        }
    }

    public String withdraw(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String role = request.getParameter("role");
        switch (role) {
            case "diner": {
                return new User().disRegister(id);
            }
            default: {
                return "";
            }
        }
    }

    public String changeWorkState(HttpServletRequest request) {
        int tid = Integer.parseInt(request.getParameter("tid"));
        return "";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
