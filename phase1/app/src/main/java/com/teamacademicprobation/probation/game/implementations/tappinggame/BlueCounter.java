package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BlueCounter {
  static int blueLimit = 30;
  private int blueCount;
  private Paint paint;
  private int x;
  private int y;

  public BlueCounter(int screenWidth, int screenHeight) {
    this.blueCount = 0;
    this.x = (int) (screenWidth * 0.01);
    this.y = (int) (screenHeight * 0.03);
    paint = new Paint();
    paint.setColor(Color.WHITE);
    paint.setTextSize(50);
  }

  public int getBlueCount() {
    return this.blueCount;
  }

  public void addCount() {
    this.blueCount++;
  }

  public void draw(Canvas canvas) {
    canvas.drawText("Blue Left:" + (blueLimit - this.blueCount), this.x, this.y, paint);
  }
}
