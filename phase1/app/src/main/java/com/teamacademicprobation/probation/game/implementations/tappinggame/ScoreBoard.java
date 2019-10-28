package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class ScoreBoard extends TapObject {
  private int score;
  private Paint paint;

  ScoreBoard(int x, int y) {
    super(x, y);
    this.score = 0;
    paint = new Paint();
    paint.setColor(Color.WHITE);
    paint.setTextSize(30);
  }

  int getScore() {
    return this.score;
  }

  void earnPoint() {
    this.score++;
  }

  void losePoint() {
    this.score--;
  }

  public void draw(Canvas canvas) {
    canvas.drawText("Score:" + this.score, 950, 50, paint);
  }
}
