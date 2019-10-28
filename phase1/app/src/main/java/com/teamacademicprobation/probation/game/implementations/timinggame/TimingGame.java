package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.teamacademicprobation.probation.game.Playable;

/**
 * A game where there is a bar with a target box and a moving line, and the player attempts to time
 * their taps so the line ends up inside the target box.
 */
public class TimingGame implements Playable {
  
  private HitBox hitBox;
  private SlidingLine slidingLine;
  private boolean completed;
  private static int gameColour = Color.CYAN;
  private int score;

  public TimingGame(int screenWidth, int screenHeight) {

    hitBox = new HitBox(screenWidth, screenHeight);
    slidingLine = new SlidingLine(screenWidth, screenHeight, hitBox);
  }

  public static int getGameColour() {
    return gameColour;
  }

  public void update() {

    this.slidingLine.update();

  }

  public void draw(Canvas canvas) {
    if(this.completed){
      drawScore(canvas);
      return;
    }
    canvas.drawColor(Color.BLACK); // resets the screen.
    this.hitBox.draw(canvas);
    this.slidingLine.draw(canvas);
  }

  private void drawScore(Canvas canvas) {
    Paint paint = new Paint();
    paint.setColor(gameColour);
    paint.setTextSize(50);
    canvas.drawText(String.valueOf(this.score), 100, 100, paint);
  }

  public void setCompleted() {
    this.completed = true;
  }

  public boolean isCompleted(){
    return this.completed;
  }

  public void updateScore() {
    if (this.completed) {

      this.score = Math.max(0, 100 - Math.abs((this.slidingLine.getActualPosition() - this.hitBox.getTargetCenter())));
    }
  }
}
