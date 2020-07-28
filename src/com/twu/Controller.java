package com.twu;

import java.util.Scanner;

public class Controller {

    private User[] users;
//    private Core core;
    private String[] menu;

    public void registerUser(User[] users) {
        this.users = users;
    }

//    public void registerCore(Core core) {
//        this.core = core;
//    }

    public void run() {
        int session = signIn();

        if(session < 0) {
            System.out.println("再见！");
            return;
        }

        userConfigure(session);

        welcome(session);
    }

    private int signIn() {
        System.out.println("欢迎来到热搜排行榜，你是：");

        for(int i = 0; i < users.length; ++i) {
            if(this.users[i].getType() == 0)
                System.out.printf("%d. %s (管理员)\n", i+1, users[i].getUsername());
            else if(this.users[i].getType() == 1)
                System.out.printf("%d. %s (普通用户)\n", i+1, users[i].getUsername());
        }

        Scanner in = new Scanner(System.in);
        String answer = in.next().trim();

        try {
            int n = Integer.parseInt(answer);n = n-1;

            if(n >= 0 && n < users.length) {
                if(autheticate(n))return n;
                System.out.println("密码错误");
            } else {
                System.out.println("无法识别的输入");
            }
        } catch(NumberFormatException e) {
            System.out.println("无法识别的输入");
        }
        return -1;
    }

    private boolean autheticate(int id) {
        System.out.println("请输入密码：");

        Scanner in = new Scanner(System.in);
        String passwd = in.next().trim();

        return users[id].getPassword().equals(passwd);
    }


    private void userConfigure(int session) {
        User user = users[session];
        if(user.getType() == 0) {
//            callback("1", core.seeRankings);
//            callback("2", core.addEvent);
//            callback("3", core.addSuperEvent);
            menu = new String[]{"查看热搜排行榜", "添加热搜", "添加超级热搜"};
        }
        else if(user.getType() == 1) {
//            callback("1", core.seeRankings);
//            callback("2", core.addEvent);
//            callback("3", core.voteEvent);
//            callback("4", core.buyEvent);
            menu = new String[]{"查看热搜排行榜", "给热搜事件投票", "添加热搜", "购买热搜"};
        }
    }

    private void welcome(int session) {
        String answer;

        do {
            System.out.printf("\n你好，%s, 你可以：\n\n", users[session].getUsername());
            for(int i = 0; i < menu.length; ++i) {
                System.out.printf("%d. %s\n", i+1, menu[i]);
            }
            System.out.println("输入quit 可退出");

            Scanner in = new Scanner(System.in);
            answer = in.next().trim();

//            if(callback(answer) != null) {
//                call(callback(answer, user));
//            }


        } while (!answer.equals("quit"));
        System.out.println("再见！");
    }


}
