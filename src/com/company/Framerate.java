package com.company;

public class Framerate {
    private int rate;
    private int count;
    private long lastTime;
    private long d;

    public void init(){
        rate = 0;
        count = 0;
        lastTime = System.currentTimeMillis();
    }
    public void calculate(){
        long currentTime = System.currentTimeMillis();
        d += currentTime - lastTime;
        lastTime = currentTime;
        count++;
        if(d > 1000){
            d -= 1000;
            rate = count;
            count = 0;
        }
    }
    public int getRate(){
        return rate;
    }
}
