package com.twu.view;

import com.twu.Controller;
import com.twu.users.User;

import java.util.Scanner;

public class WelcomeView {
    private User user;
    private Controller controller;
    public boolean viewShouldQuit;

    public WelcomeView(User user, Controller c) {
        this.user = user;
        this.controller = c;
        this.viewShouldQuit = false;
    }

    public void view() {
        int n = user.menus.length;
        while(!viewShouldQuit) {
            System.out.printf("\n你好，%s, 你可以：(1-%d)\n", user.getUsername(), n);
            for(int i = 0; i < n; ++i) {
                System.out.printf("%d. %s\n", i+1, user.menus[i]);
            }
            System.out.println("输入back 可返回");

            Scanner in = new Scanner(System.in);
            String answer = in.next().trim();

            controller.invokeCallback(answer);
        }
    }
}
