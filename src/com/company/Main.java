package com.company;

public class Main {

    public static void main(String[] args) {
        UserStore userStore = new UserStore();
        Panel panel = new Panel(userStore);

        panel.start();
    }
}
