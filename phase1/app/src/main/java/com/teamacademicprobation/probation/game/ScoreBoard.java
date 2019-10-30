package com.teamacademicprobation.probation.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ScoreBoard{
  private int score;
  private Paint paint;
  private int x;
  private int y;

  public ScoreBoard(int screenWidth, int screenHeight) {
    this.score = 0;
    this.x = (int)(screenWidth * 0.81);
    this.y = (int)(screenHeight * 0.03);
    paint = new Paint();
    paint.setColor(Color.WHITE);
    paint.setTextSize(50);
  }

  public int getScore() {
    return this.score;
  }

  public void earnPoint() {
    this.score++;
  }

  public void losePoint() {
    this.score--;
  }

  public void draw(Canvas canvas) {
    canvas.drawText("Score:" + this.score,this.x, this.y, paint);
  }
}
