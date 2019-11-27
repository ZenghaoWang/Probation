package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.teamacademicprobation.probation.ui.ScoreScreenActivity;

@SuppressLint("ViewConstructor")
public class TapGameView extends SurfaceView implements Runnable {
    volatile boolean playing;
    private Thread gameThread = null;
    private SurfaceHolder surfaceHolder;
    private TapGamePresenter tapGamePresenter;

    /**
     * Initializes the view and starts the tapping game in the context environment.
     *
     * @param context      the environment.
     * @param currPlayerID the ID of the player playing the game.
     */
    public TapGameView(Context context, String currPlayerID) {
        super(context);
        surfaceHolder = getHolder();
        this.tapGamePresenter = new TapGamePresenter(this, currPlayerID);
    }

    /**
     * Runs game while playing variable is true.
     */
    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    /**
     * Updates the game.
     */
    private void update() {
        this.tapGamePresenter.update();
    }

    /**
     * Switches to the ScoreScreenActivity.
     */
    public void goToScoreScreen(String score) {
        Intent intent = new Intent(getContext(), ScoreScreenActivity.class);
        intent.putExtra(ScoreScreenActivity.SCORE_KEY, "You scored:" + score);
        getContext().startActivity(intent);
    }

    /**
     * Draws the game on canvas.
     */
    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            this.tapGamePresenter.draw(canvas);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            Log.e("TapGame", "Unexpected interruption.");
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("TapGame", "Unexpected interruption.");
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.tapGamePresenter.updateScore(event.getX(), event.getY());
            return true;
        }
        return false;
    }
}
