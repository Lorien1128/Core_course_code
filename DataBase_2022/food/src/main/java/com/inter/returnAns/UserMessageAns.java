package com.inter.returnAns;

public class UserMessageAns {
    private String name;
    private String password;
    private String phone;
    private Double x;
    private Double y;
    private Long account;
    private boolean vip;

    public UserMessageAns(String name, String password, String phone, Double x, Double y, Long account, boolean vip) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.x = x;
        this.y = y;
        this.account = account;
        this.vip = vip;
    }
}
