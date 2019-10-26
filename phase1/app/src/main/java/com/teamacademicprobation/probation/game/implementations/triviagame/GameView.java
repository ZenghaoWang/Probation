package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.content.Context;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    volatile boolean isPlaying;
    private Thread gameThread = null;

    public GameView(Context context) {
        super(context);
    }


    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            control();
        }
    }

    private void control() {
        try{
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void draw() {
    }

    private void update() {
    }

    public void pause(){
        isPlaying = false;
        try{
            gameThread.join();
        } catch (InterruptedException e){

        }
    }

    public void resume(){
        isPlaying = true;
        gameThread = new Thread();
        gameThread.start();
    }


    
    
}
