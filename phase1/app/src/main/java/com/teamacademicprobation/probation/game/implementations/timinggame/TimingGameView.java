package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.teamacademicprobation.probation.ui.ScoreScreenActivity;

// Code template from
// https://www.simplifiedcoding.net/android-game-development-tutorial-1/#Android-Game-Development-with-Unity

public class TimingGameView extends SurfaceView implements Runnable {

  private Thread gameThread = null;
  private volatile boolean running;
  private TimingGame timingGame;
  private SurfaceHolder surfaceHolder;
  private static final String TAG = "TimingGame";

  /**
   * Initializes the view and starts the timing game in the context environment.
   *
   * @param context the environment
   */
  public TimingGameView(Context context) {
    super(context);
    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    this.timingGame = new TimingGame(screenWidth, screenHeight);
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

  /** Updates the game. */
  private void update() {
    this.timingGame.update();
  }

  /** Draws the game onto the canvas. */
  private void draw() {
    if (surfaceHolder.getSurface().isValid()) {
      Canvas canvas = surfaceHolder.lockCanvas();
      this.timingGame.draw(canvas);
      surfaceHolder.unlockCanvasAndPost(canvas);
    }
  }

  /** Allows the game to run smoothly. */
  private void control() {
    try {
      gameThread.sleep(17);
    } catch (InterruptedException e) {
      Log.e(TAG, e.toString());
    }
  }

  /** Pauses the thread. */
  public void pause() {
    running = false;
    try {
      // stopping the thread
      gameThread.join();
    } catch (InterruptedException e) {
      Log.e(TAG, e.toString());
    }
  }

  /** Resumes the thread. */
  public void resume() {
    // when the game is resumed
    // starting the thread again
    running = true;
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public boolean performClick() {
    super.performClick();
    this.timingGame.updateScore();
    if (this.timingGame.isCompleted()) {
      Intent intent = new Intent(getContext(), ScoreScreenActivity.class);
      intent.putExtra("score", "You scored:" + this.timingGame.getScore());
      getContext().startActivity(intent);
    }
    return true;
  }

  @Override
  public boolean onTouchEvent(MotionEvent motionEvent) {
    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
      performClick();
      return true;
    }
    return false;
  }
}
