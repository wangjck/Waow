package com.company;

import java.awt.*;
import java.awt.event.*;

public class MouseInput implements MouseListener{
    private static final int BOTTON_COUNT = 3;
    private boolean[] buttons;
    private int[] poll;
    private Point mousePos;
    private Point currentPos;
    private int notches;
    private int polledNotches;
    public MouseInput(){
        buttons = new boolean[BOTTON_COUNT];
        poll = new int[BOTTON_COUNT];
        mousePos = new Point(0,0);
        currentPos = new Point(0,0);
    }
    public void poll(){
        mousePos = new Point(currentPos);
        polledNotches = notches;
        notches = 0;
        for(int i = 0; i < BOTTON_COUNT; i++){
            if(buttons[i]){
                poll[i]++;
            }else{
                poll[i] = 0;
            }
        }
    }

    public Point getPosition() {
        return mousePos;
    }
    public int getNotches(){
        return polledNotches;
    }
    public boolean mouseDown(int button){
        return poll[button - 1] > 0;
    }
    public boolean mouseDownOnce(int button){
        return poll[button - 1] == 1;
    }
    @Override
    public synchronized void mouseClicked(MouseEvent e) {
       //
    }

    @Override
    public synchronized void mouseEntered(MouseEvent e) {
        mouseMove(e);
    }

    @Override
    public synchronized void mouseExited(MouseEvent e) {
        mouseMove(e);
    }
    public synchronized void mouseMove(MouseEvent e){
        currentPos = e.getPoint();
    }
    public synchronized mouseWheelMoved(MouseEvent e){
        notches += e.getWheelRotation();
    }
    @Override
    public synchronized void mousePressed(MouseEvent e) {
        int button = e.getButton() - 1;
        if(button >=0 && button < buttons.length){
            buttons(button) = true;
        }
    }

    @Override
    public synchronized void mouseReleased(MouseEvent e) {
        int button = e.getButton() - 1;
        if(button >=0 && button < buttons.length){
            buttons(button) = false;
        }
    }
}
