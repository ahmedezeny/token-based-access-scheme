package com.company;

public class utils {

    public static double getRandomBetween(double min, double max){
        double x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }
}
