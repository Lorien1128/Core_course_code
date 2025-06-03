package com.inter;

import com.google.gson.Gson;
import com.inter.returnAns.BooleanAns;
import com.inter.returnAns.UserMessageAns;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class User {
    private Connection conn = null;
    private final Statement st = null;

    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public String register(String name,String password,String phone) { //用户注册
        try {
            conn = JdbcUtils.getConnection();
            pst = conn.prepareStatement("select * from user where name = ?");
            pst.setString(1, name);
            rs = pst.executeQuery();
            if (rs.next()) {
                return new Gson().toJson(new BooleanAns(false));
            }
            pst = conn.prepareStatement("insert into user (`name`, `password`, `phone`, `account`, `isVip`, `isLogin`) " +
                    "VALUES (?,?,?,?,?,?)");
            pst.setString(1,name);
            pst.setString(2,password);
            pst.setString(3,phone);
            pst.setLong(4,0);
            pst.setBoolean(5,false);
            pst.setBoolean(6,true);
            pst.executeUpdate();
            return new Gson().toJson(new BooleanAns(true));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtils.release(conn, null,rs,pst);
        }
    }

    public boolean isHaveId(int id) {
        try {
            conn = JdbcUtils.getConnection();
            pst = conn.prepareStatement("select * from user where `uid` = ?");
            pst.setInt(1,id);
            pst.executeQuery();
            rs = pst.getResultSet();
            //判断id是否存在
            return rs.next();
        } catch (SQLException e) {
            return false;
        } finally {
            JdbcUtils.release(conn, null,rs,pst);
        }
    }

    public String disRegister(int id) { //用户注销
        try {
            if (!isHaveId(id)) {

                return new Gson().toJson(new BooleanAns(false));
            }
            conn = JdbcUtils.getConnection();
            pst = conn.prepareStatement("delete from user where `uid` = ?");
            pst.setInt(1,id);
            pst.executeUpdate();
            return new Gson().toJson(new BooleanAns(true));
        } catch (SQLException e) {
            return new Gson().toJson(new BooleanAns(false));
        } finally {
            JdbcUtils.release(conn,st,rs,pst);
        }
    }

    public String messageChange(int id,String name,String password,String phone,Double x,Double y) { //用户信息修改
        try {
            if (!isHaveId(id)) {
                return new Gson().toJson(new BooleanAns(false));
            }
            conn = JdbcUtils.getConnection();
            pst = conn.prepareStatement("update user set `name` = ?, `password` = ?, `phone` =  ?," +
                    "`posX` = ?,`posY` = ? where `uid` = ?");
            pst.setString(1,name);
            pst.setString(2,password);
            pst.setString(3,phone);
            pst.setDouble(4,x);
            pst.setDouble(5,y);
            pst.setInt(6,id);
            pst.executeUpdate();
            return new Gson().toJson(new BooleanAns(true));
        } catch (SQLException e) {
            return new Gson().toJson(new BooleanAns(false));
        } finally {
            JdbcUtils.release(conn, null,rs,pst);
        }
    }

    public String queryMessage(int id) { //用户信息查询
        try {
            conn = JdbcUtils.getConnection();
            pst = conn.prepareStatement("select * from user where `uid` = ?");
            pst.setInt(1,id);
            pst.executeQuery();
            rs = pst.getResultSet();
            rs.next();
            UserMessageAns userMessageAns = new UserMessageAns(rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getDouble(5), rs.getDouble(6),
                    rs.getLong(7),rs.getBoolean(8));
            return new Gson().toJson(userMessageAns);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtils.release(conn, null,rs,pst);
        }
    }

    public String recharge(int id,Long money) {
        try {
            if (!isHaveId(id)) {
                return new Gson().toJson(new BooleanAns(false));
            }
            conn = JdbcUtils.getConnection();
            pst = conn.prepareStatement("select `account` from user where `uid` = ?");
            pst.setInt(1,id);
            pst.executeQuery();
            rs = pst.getResultSet();
            rs.next();
            Long nowMoney = rs.getLong(1);
            long writeMoney = nowMoney + money;
            pst = conn.prepareStatement("update user set `account` = ? where `uid` = ?");
            pst.setLong(1,writeMoney);
            pst.setInt(2,id);
            pst.executeUpdate();
            return new Gson().toJson(new BooleanAns(true));
        } catch (SQLException e) {
            return new Gson().toJson(new BooleanAns(false));
        } finally {
            JdbcUtils.release(conn, null,rs,pst);
        }
    }

    public String changeVip(int id) {
        try {
            if (!isHaveId(id)) {
                return new Gson().toJson(new BooleanAns(false));
            }
            conn = JdbcUtils.getConnection();
            pst = conn.prepareStatement("select `isVip` from user where `uid` = ?");
            pst.setInt(1,id);
            pst.executeQuery();
            rs = pst.getResultSet();
            rs.next();
            boolean nowVip = rs.getBoolean(1);
            boolean writeVip = !nowVip;
            pst = conn.prepareStatement("update user set `isVip` = ? where `uid` = ?");
            pst.setBoolean(1,writeVip);
            pst.setInt(2,id);
            pst.executeUpdate();
            return new Gson().toJson(new BooleanAns(true));
        } catch (SQLException e) {
            return new Gson().toJson(new BooleanAns(false));
        } finally {
            JdbcUtils.release(conn, null,rs,pst);
        }
    }

    public String login(String name,String password) {
        try {
            conn = JdbcUtils.getConnection();
            pst = conn.prepareStatement("select `password`, `uid` from user where `name` = ?");
            pst.setString(1, name);
            pst.executeQuery();
            HashMap<String, Integer> res = new HashMap<>();
            rs = pst.getResultSet();
            if (rs.next()) {
                String userPassword = rs.getString(1);
                int uid = rs.getInt(2);
                if (password.equals(userPassword)) {
                    pst = conn.prepareStatement("update user set `isLogin` = ? where `name` = ?");
                    pst.setBoolean(1,true);
                    pst.setString(2, name);
                    pst.executeUpdate();
                    res.put("id", uid);
                }
                else {
                    res.put("id", -2);
                }
            }
            else {
                res.put("id", -1);
            }
            return new Gson().toJson(res);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            JdbcUtils.release(conn, null,rs,pst);
        }
    }
}
