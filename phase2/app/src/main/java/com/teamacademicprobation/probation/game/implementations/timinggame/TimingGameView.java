package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.teamacademicprobation.probation.ui.ScoreScreenActivity;

/**
 * A view for the timing game.
 */
@SuppressLint("ViewConstructor")
public class TimingGameView extends SurfaceView implements Runnable, TimingGameViewInterface {


    private Thread gameThread = null;
    private volatile boolean running;
    private TimingGamePresenter timingGamePresenter;
    private SurfaceHolder surfaceHolder;
    private static final String TAG = "TimingGame";

    public TimingGameView(Context context, String playerID) {
        super(context);
        this.timingGamePresenter = new TimingGamePresenter(this, playerID);
        surfaceHolder = getHolder();
    }

    @Override
    public void run() {
        while (running) {
            update();
            draw();
            control();
        }
    }


    /**
     * Updates the game.
     */
    private void update() {
        this.timingGamePresenter.update();
    }

    /**
     * Draws the game onto the canvas.
     */
    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            this.timingGamePresenter.draw(canvas);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    /**
     * Allows the game to run smoothly.
     */
    private void control() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * Pauses the thread.
     */
    public void pause() {
        running = false;
        try {
            // stopping the thread
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * Resumes the thread.
     */
    public void resume() {
        // when the game is resumed
        // start the thread again
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            this.timingGamePresenter.onTouch(motionEvent.getX());
            return true;
        }
        return false;
    }


    @Override
    public void goToScoreScreen(String score) {
        Intent intent = new Intent(getContext(), ScoreScreenActivity.class);
        intent.putExtra(ScoreScreenActivity.SCORE_KEY, "You scored:" + score);
        getContext().startActivity(intent);
    }

}

