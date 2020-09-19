package com.music_demo.model;

public class App_demo extends Thread {

    public void init()
    {
        
        new App_demo().start();
    }

    public void destroy() {
        this.is = false;
    }

    private volatile boolean is = true;

    @Override
    public void run() {
        while (is) {
            try {
                Thread.sleep(5000);
                System.out.println("我被执行了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
