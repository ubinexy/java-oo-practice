package com.twu.view;

import com.twu.Controller;
import com.twu.users.User;
import java.util.Scanner;

public class LoginView {

    private final Controller controller;
    private final User[] users;
    public boolean viewShouldQuit;

    public LoginView(User[] users, Controller controller) {
        this.users = users;
        this.controller = controller;
        this.viewShouldQuit = false;
    }

    public void view() {
        while(!viewShouldQuit) {
            System.out.println("\n欢迎来到热搜排行榜，你是：(1-2)");

            for(int i = 0; i < users.length; ++i) {
                if(users[i].getType() == 0)
                    System.out.printf("%d. %s (管理员)\n", i+1, users[i].getUsername());
                else if(users[i].getType() == 1)
                    System.out.printf("%d. %s (普通用户)\n", i+1, users[i].getUsername());
            }
            System.out.println("输入quit 可退出");
            Scanner in = new Scanner(System.in);
            String answer = in.next().trim();

            controller.autheticate(answer);
        }
    }

}
