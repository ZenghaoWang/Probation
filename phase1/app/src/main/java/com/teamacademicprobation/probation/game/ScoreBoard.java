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

  public void setScore(int score){ this.score = score;}

  public void earnPoint() {
    this.score++;
  }

  public void losePoint() {
    this.score--;
  }

  public void draw(Canvas canvas) {
    int charWidth = Math.toIntExact(Math.round(paint.measureText("0")));
    int numChars = (int) (Math.log10(score+1) + 1);

    canvas.drawText("Score:" + this.score ,this.x -(charWidth*numChars), this.y, paint);
  }
}
