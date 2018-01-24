package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

public class JavaApp extends JFrame implements Runnable{
    private Framerate rate;
    private Dimension d;
    private Thread gameThread;
    private volatile boolean running;
    private BufferStrategy bs;
    private KeyInput keys;
    int s = 5;

    public JavaApp(){
        d = new Dimension(1048,768);
        rate = new Framerate();
    }
    public void createGUI(){
        Canvas canvas = new Canvas();
        canvas.setBackground(Color.WHITE);
        canvas.setPreferredSize(d);
        canvas.setIgnoreRepaint(true);
        getContentPane().add(canvas);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rua");
        pack();
        canvas.createBufferStrategy(2);rate.init();
        gameThread = new Thread(this);
        bs = canvas.getBufferStrategy();
        keys = new KeyInput();
        canvas.addKeyListener(keys);
        setVisible(true);
        gameThread.start();
    }
    public void run(){
        running = true;
        rate.init();
        while(running){
            gameLoop();
        }

    }
    public void processInput(){
        keys.poll();
        //mouses.poll();
        if(keys.keyDownOnce(KeyEvent.VK_SPACE)){
            System.out.println("space");
        }
        if(keys.keyDown(KeyEvent.VK_C)){
            System.out.println("c");
        }
    }
    private void renderFrame(){
        do{
            do{
                Graphics g = null;
                try{
                    g = bs.getDrawGraphics();
                    g.clearRect(0,0,getWidth(),getHeight());
                    render(g);
                }finally{
                    if( g != null){
                        g.dispose();
                    }
                }
            }while(bs.contentsRestored());
            bs.show();
        }while(bs.contentsLost());
    }
    private void sleep(int length){
        try{
            Thread.sleep(length);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public void gameLoop(){
        processInput();
        renderFrame();
        sleep(16);
    }
    public void render(Graphics g){
        rate.calculate();
        g.setColor(Color.BLACK);
        g.drawString(rate.getRate()+"",20,20);
    }
    protected void onWindowClosing(){
        try{
            running = false;
            gameThread.join();
        }catch( InterruptedException e){
            e.printStackTrace();
        }
        System.exit(0);
    }
    public static void main(String[] args){
        JavaApp japp = new JavaApp();
        japp.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                japp.onWindowClosing();
            }
        });

        japp.createGUI();

    }
}
