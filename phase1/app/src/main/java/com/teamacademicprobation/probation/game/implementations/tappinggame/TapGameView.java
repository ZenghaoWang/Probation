package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.teamacademicprobation.probation.game.ScoreBoard;

public class TapGameView extends SurfaceView implements Runnable {

  volatile boolean playing;
  private Thread gameThread = null;
  private SurfaceHolder surfaceHolder;
  private TapObjectManager tapObjectManager;
  private Canvas canvas;

  public TapGameView(Context context) {
    super(context);
    surfaceHolder = getHolder();
    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    tapObjectManager = new TapObjectManager(screenWidth, screenHeight);
  }

  @Override
  public void run() {
    while (playing) {
      update();
      draw();
      control();
    }
  }

  private void update() {
    tapObjectManager.update();
  }

  private void draw() {
    if (surfaceHolder.getSurface().isValid()) {
      canvas = surfaceHolder.lockCanvas();
      canvas.drawColor(Color.BLACK);
      tapObjectManager.draw(canvas);
      surfaceHolder.unlockCanvasAndPost(canvas);
    }
  }

  private void control() {
    try {
      Thread.sleep(900);
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
      int touch_x = (int) event.getX();
      int touch_y = (int) event.getY();
      if (tapObjectManager.getBlue() != null) {
        if (tapObjectManager.getBlue().getX() - 60 <= touch_x
            && touch_x <= tapObjectManager.getBlue().getX() + 60
            && tapObjectManager.getBlue().getY() - 60 <= touch_y
            && touch_y <= tapObjectManager.getBlue().getY()) {
          tapObjectManager.getScoreBoard().earnPoint();
          tapObjectManager.getScoreBoard().draw(canvas);
        }
      } else {
        if (tapObjectManager.getRed().getX() - 60 <= touch_x
            && touch_x <= tapObjectManager.getRed().getX() + 60
            && tapObjectManager.getRed().getY() - 60 <= touch_y
            && touch_y <= tapObjectManager.getRed().getY()) {
          if (tapObjectManager.getScoreBoard().getScore() != 0) {
            tapObjectManager.getScoreBoard().losePoint();
            tapObjectManager.getScoreBoard().draw(canvas);
          }
        }
      }
      return true;
    }
    return false;
  }
}
