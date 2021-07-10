package com.company;

public class Main {

    public static void main(String[] args) {
        try {
            QueuesManager manager = QueuesManager.getInstance(4000);
            manager.start();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }




}
