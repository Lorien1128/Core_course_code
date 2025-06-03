package com.inter;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        User user1 = new User();
        user1.register("qyb","qybsb","11111111");
        System.out.println(user1.queryMessage(1));
        System.out.println(user1.queryMessage(1));
        user1.register("qybskdasj","qybsbsdas","1111111sdasd1");
        System.out.println(user1.queryMessage(2));
    }
}