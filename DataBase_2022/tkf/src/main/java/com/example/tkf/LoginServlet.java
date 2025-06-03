package com.example.tkf;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    //TODO: for test
    private final HashMap<String, String> user = new HashMap<>();

    public LoginServlet() {
        user.put("user", "zhang-san");
        user.put("pass", "abc123");
        user.put("phone", "1234567");
        user.put("role", "1");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // System.out.println("got it");
        String name = request.getParameter("user");
        String pass = request.getParameter("pass");
        String phone = request.getParameter("phone");
        String role = request.getParameter("role");
        String action = request.getParameter("action");
        HashMap<String, String> resp = new HashMap<>();
        if (action.equals("login")) {
            if (name.equals(user.get("user")) && pass.equals(user.get("pass"))
                    && role.equals(user.get("role"))) {
                resp.put("succeed", "true");
            }
            else {
                resp.put("succeed", "false");
            }
        }
        String json = new Gson().toJson(resp);
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
