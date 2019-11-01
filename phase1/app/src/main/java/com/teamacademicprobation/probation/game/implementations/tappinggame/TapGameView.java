package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.teamacademicprobation.probation.ui.ScoreScreenActivity;

public class TapGameView extends SurfaceView implements Runnable {
  volatile boolean playing;
  private Thread gameThread = null;
  private SurfaceHolder surfaceHolder;
  private TapGame tapGame;
  private Canvas canvas;

  /**
   * Initializes the view and starts the tapping game in the context environment.
   *
   * @param context the environment.
   * @param currPlayerID the ID of the player playing the game.
   */
  public TapGameView(Context context, String currPlayerID) {
    super(context);
    surfaceHolder = getHolder();
    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    tapGame = new TapGame(screenWidth, screenHeight, currPlayerID);
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
    tapGame.update();
    if (tapGame.getGameComplete()) {
      goToScoreScreen();
    }
  }


  /**
   * Switches to the ScoreScreenActivity.
   */
  private void goToScoreScreen() {
    Intent intent = new Intent(getContext(), ScoreScreenActivity.class);
    intent.putExtra("score", "You scored:" + this.tapGame.getScore());
    getContext().startActivity(intent);
  }

  /**
   * Draws the game on canvas.
   */
  private void draw() {
    if (surfaceHolder.getSurface().isValid()) {
      canvas = surfaceHolder.lockCanvas();
      canvas.drawColor(Color.BLACK);
      tapGame.draw(canvas);
      surfaceHolder.unlockCanvasAndPost(canvas);
    }
  }

  private void control() {
    try {
      Thread.sleep(850);
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

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      double touch_x = event.getX();
      double touch_y = event.getY();
      tapGame.check_touch(touch_x, touch_y);
      return true;
    }
    return false;
  }
}
