package com.twu;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Controller {

    private User[] users;
    private RankingSystem core;

    private String[] menus;
    private Map<String, Method> callbacks = new HashMap<>();


    public void registerUser(User[] users) {
        this.users = users;
        core = new RankingSystem();
    }


    public void run() {
        int session = signIn();
        if(session < 0) {
            System.out.println("再见！");
            return;
        }

        try {
            userConfigure(session);
        } catch (NoSuchMethodException e) {
            System.out.println("找不到函数");
            return;
        }

        try {
            welcome(session);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("运行时异常");
        }

    }

    private int signIn()  {
        String answer;

        do {
            System.out.println("欢迎来到热搜排行榜，你是：");

            for(int i = 0; i < users.length; ++i) {
                if(users[i].getType() == 0)
                    System.out.printf("%d. %s (管理员)\n", i+1, users[i].getUsername());
                else if(users[i].getType() == 1)
                    System.out.printf("%d. %s (普通用户)\n", i+1, users[i].getUsername());
            }
            System.out.println("输入quit 可退出");
            Scanner in = new Scanner(System.in);
            answer = in.next().trim();

            try {
                int n = Integer.parseInt(answer);n = n-1;

                if(n >= 0 && n < users.length) {
                    if(autheticate(n)) return n;
                    System.out.println("密码错误");
                }
            } catch(NumberFormatException e) {
                if(!answer.equals("quit"))
                    System.out.println("密码错误");
            }
        } while(!answer.equals("quit"));
        return -1;

    }

    private boolean autheticate(int id) {
        System.out.println("请输入密码：");

        Scanner in = new Scanner(System.in);
        String passwd = in.next().trim();

        return users[id].getPassword().equals(passwd);
    }


    private void userConfigure(int session) throws NoSuchMethodException {
        User user = users[session];
        if(user.getType() == 0) {
            callbacks.put("1", User.class.getDeclaredMethod("seeRankings", RankingSystem.class));
            callbacks.put("2", User.class.getDeclaredMethod("addEvent", RankingSystem.class));
            callbacks.put("3", User.class.getDeclaredMethod("addSuperEvent", RankingSystem.class));
            menus = new String[]{"查看热搜排行榜", "添加热搜", "添加超级热搜"};
        }
        else if(user.getType() == 1) {
            callbacks.put("1", User.class.getDeclaredMethod("seeRankings", RankingSystem.class));
            callbacks.put("2", User.class.getDeclaredMethod("addEvent", RankingSystem.class));
            callbacks.put("3", User.class.getDeclaredMethod("voteEvent", RankingSystem.class));
            callbacks.put("4", User.class.getDeclaredMethod("buyEventRanking", RankingSystem.class));
            menus = new String[]{"查看热搜排行榜", "添加热搜", "给热搜事件投票", "购买热搜"};
        }
    }

    private void welcome(int session) throws InvocationTargetException, IllegalAccessException {
        String answer;
        User usr = users[session];
        do {
            System.out.printf("\n你好，%s, 你可以：\n", usr.getUsername());
            for(int i = 0; i < menus.length; ++i) {
                System.out.printf("%d. %s\n", i+1, menus[i]);
            }
            System.out.println("输入back 可返回");

            Scanner in = new Scanner(System.in);
            answer = in.next().trim();

            if(callbacks.containsKey(answer)) {
                Method m = callbacks.get(answer);
                if(m != null) m.invoke(usr, core);
            }
        } while (!answer.equals("back"));

        this.run();
    }
}
