package com.twu;

import com.twu.users.User;
import com.twu.view.LoginView;
import com.twu.view.WelcomeView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Controller {

    private User[] users;
    private RankingSystem core;

    private Map<String, Method> callbacks = new HashMap<>();

    private LoginView login;
    private WelcomeView welcome;

    private int session = -1;

    public void registerUser(User[] users) {
        this.users = users;
        this.core = new RankingSystem();
        this.login = new LoginView(users,this);
    }

    public void run() {
        while(true) {
            login.view();
            if (session < 0) {
                System.out.println("再见！");
                return;
            }

            try {
                setCallbacks(session);
            } catch (NoSuchMethodException e) {
                System.out.println("找不到函数");
                return;
            }

            welcome = new WelcomeView(users[session], this);
            welcome.view();
            login.viewShouldQuit = false;
        }
    }


    public void autheticate(String cmd) {
        if(cmd.equals("quit")) {
            login.viewShouldQuit = true;
            return;
        }

        try {
            int id = Integer.parseInt(cmd); id--;
            if(id >= users.length || id < 0) return;
            System.out.println("请输入密码：");
            Scanner in = new Scanner(System.in);
            String passwd = in.next().trim();

            if(users[id].getPassword().equals(passwd)) {
                login.viewShouldQuit = true;
                this.session = id;
            } else {
                System.out.println("密码错误");
            }
        } catch(Exception e) {
        }

    }


    private void setCallbacks(int userId) throws NoSuchMethodException {
        if(userId == 0) {
            callbacks.put("1", User.class.getDeclaredMethod("seeRankings", RankingSystem.class));
            callbacks.put("2", User.class.getDeclaredMethod("addTopic", RankingSystem.class));
            callbacks.put("3", User.class.getDeclaredMethod("addSuperTopic", RankingSystem.class));
            callbacks.put("4", User.class.getDeclaredMethod("removeTopic", RankingSystem.class));
        }
        else if(userId == 1) {
            callbacks.put("1", User.class.getDeclaredMethod("seeRankings", RankingSystem.class));
            callbacks.put("2", User.class.getDeclaredMethod("addTopic", RankingSystem.class));
            callbacks.put("3", User.class.getDeclaredMethod("voteTopic", RankingSystem.class));
            callbacks.put("4", User.class.getDeclaredMethod("buyTopicRanking", RankingSystem.class));
        }
    }

    public void invokeCallback(String cmd) {
        if(cmd.equals("back")) {
            welcome.viewShouldQuit = true;
            callbacks.clear();
            session = -1;
            return;
        }

        try {
            if(callbacks.containsKey(cmd)) {
                Method m = callbacks.get(cmd);
                User user = users[session];
                if(m != null) m.invoke(user, core);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("运行时错误");
        }
    }
}
