package com.ianduran.circuitos;

public class Light {
    private int number;

    public Light(int number){
        this.number = number;
    }

    public String getName(){
        return "Luz " + number;
    }

    public int getNumber(){
        return number;
    }
}
