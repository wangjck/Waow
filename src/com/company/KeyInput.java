package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{
    private boolean[] keys;
    private int[] poll;
    public KeyInput(){
        keys = new boolean[256];
        poll = new int[256];
    }
    public boolean keyDown(int KeyCode){
        return poll[KeyCode] > 0;
    }
    public boolean keyDownOnce(int KeyCode){
        return poll[KeyCode] == 1;
    }
    public synchronized void poll(){
        for(int i = 0; i < keys.length; i++){
            if (keys[i]) {
                poll[i]++;
            }else{
                poll[i] = 0;
            }
        }
    }
    @Override
    public synchronized void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode >= 0 && keyCode <= keys.length) {
            keys[keyCode] = true;
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode >= 0 && keyCode <= keys.length) {
            keys[keyCode] = false;
        }
    }

    @Override
    public synchronized void keyTyped(KeyEvent e) {

    }
}
