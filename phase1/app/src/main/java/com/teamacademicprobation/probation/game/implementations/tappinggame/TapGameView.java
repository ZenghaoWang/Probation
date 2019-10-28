package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class TapGameView extends SurfaceView implements Runnable {

  volatile boolean playing;
  private Thread gameThread = null;
  private SurfaceHolder surfaceHolder;
  private TapObjectManager tapObjectManager;
  private ScoreBoard scoreBoard;
  private Canvas canvas;

  public TapGameView(Context context) {
    super(context);
    surfaceHolder = getHolder();
    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    tapObjectManager = new TapObjectManager(screenWidth, screenHeight);
    scoreBoard = new ScoreBoard(screenWidth, screenHeight);
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
      scoreBoard.draw(canvas);
      surfaceHolder.unlockCanvasAndPost(canvas);
    }
  }

  private void control() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void pause() {
    playing = false;
    try {
      gameThread.join();
    } catch (InterruptedException ignored) {
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
      int tounchX = (int) event.getX();
      int tounchY = (int) event.getY();
      if (tapObjectManager.getBlue() != null) {
        if (tapObjectManager.getBlue().getX() - 60 <= tounchX
            && tounchX <= tapObjectManager.getBlue().getX() + 60
            && tapObjectManager.getBlue().getY() - 60 <= tounchY
            && tounchY <= tapObjectManager.getBlue().getY()) {
          scoreBoard.earnPoint();
          scoreBoard.draw(canvas);
        }
      } else {
        if (tapObjectManager.getRed().getX() - 60 <= tounchX
            && tounchX <= tapObjectManager.getRed().getX() + 60
            && tapObjectManager.getRed().getY() - 60 <= tounchY
            && tounchY <= tapObjectManager.getRed().getY()) {
          if (scoreBoard.getScore() != 0) {
            scoreBoard.losePoint();
            scoreBoard.draw(canvas);
          }
        }
      }
      return true;
    }
    return false;
  }
}
