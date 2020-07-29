package com.twu;

import com.twu.users.Admin;
import com.twu.users.OrdinaryUser;
import com.twu.users.User;

public class Main {


    public static void main(String[] args) {

        Controller controller = new Controller();

        User[] users = {
                new Admin("admin", "1234"),
                new OrdinaryUser("kaka", "1234")
        };

        controller.registerUser(users);

        controller.run();
    }
}
