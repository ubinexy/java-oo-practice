package com.twu;

import com.twu.users.AdminUser;
import com.twu.users.OrdinaryUser;
import com.twu.users.User;

public class Main {


    public static void main(String[] args) {

        Controller controller = new Controller();

        User[] users = {
                new AdminUser("admin", "1234"),
                new OrdinaryUser("kaka", "1234")
        };

        controller.registerUser(users);

        controller.run();
    }
}
