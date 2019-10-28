package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.teamacademicprobation.probation.game.Playable;

public class TimingGameView extends SurfaceView implements Runnable {

    private Thread gameThread = null;
    private volatile boolean running;
    private Canvas canvas;
    private Playable timingGame;
    private SurfaceHolder surfaceHolder;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;


    public TimingGameView(Context context) {
        super(context);
        this.timingGame = new TimingGame(screenWidth, screenHeight);
        surfaceHolder = getHolder();
    }

    @Override
    public void run() {
        while(running){
            update();
            draw();
            control();
        }
    }
    private void update() {
        this.timingGame.update();

    }

    private void draw() {
    if (surfaceHolder.getSurface().isValid()) {
        canvas = surfaceHolder.lockCanvas();
        this.timingGame.draw(canvas);
        surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            Log.e("TimingGame", "Unexpected interruption.");
        }
    }

    public void pause() {
        //when the game is paused
        //setting the variable to false
        running = false;
        try {
            //stopping the thread
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("TimingGame", "Unexpected interruption.");
        }
    }

    public void resume() {
        //when the game is resumed
        //starting the thread again
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            this.timingGame.setCompleted();
            this.timingGame.updateScore();
            canvas = surfaceHolder.lockCanvas();
            this.timingGame.draw(canvas);
            surfaceHolder.unlockCanvasAndPost(canvas);
            this.pause();
            return true;
        }
        return false;
    }

}
