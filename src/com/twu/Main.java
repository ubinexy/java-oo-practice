package com.twu;

public class Main {


    public static void main(String[] args) {

        Controller controller = new Controller();

        User[] users = new User[]{
                new Admin("admin", "1234"),
                new OrdinaryUser("kaka", "1234")
        };

        controller.registerUser(users);

        controller.run();
    }
}
