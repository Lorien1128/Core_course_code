package com.servlet;

import com.inter.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("user");
        String pass = request.getParameter("pass");
        String role = request.getParameter("role");
        String action = request.getParameter("action");
        String json = "";
        if (action.equals("login")) {
            switch (role) {
                case "diner": {
                    json = new User().login(name, pass);
                    break;
                }
                case "restaurant": {
                    break;
                }
                default: {
                    break;
                }
            }
        }
        else {
            String phone = request.getParameter("phone");
            switch (role) {
                case "diner": {
                    json = new User().register(name, pass, phone);
                    break;
                }
                case "restaurant": {
                    break;
                }
                default: {
                    break;
                }
            }
        }
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
