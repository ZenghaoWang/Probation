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


  private Box box;
  /** Represents if the game is completed. */
  private boolean completed;
  /** The color of the game. In phase 2, can be updated to change based on user preferences. */
  private static int gameColor = Color.CYAN;
  /** The score of the game. */
  private int score;

  /** Initializes the line and the hitbox. Sets the initial score to 0 and not completed. */
  public TimingGame(int screenWidth, int screenHeight) {

    this.box = new Box(screenWidth, screenHeight);
    this.score = 0;
    this.completed = false;
  }

  /**
   * Returns the Color of the game.
   *
   * @return color of the game
   */
  public static int getGameColor() {
    return gameColor;
  }

  /** Updates the game. */
  public void update() {
    if (!this.completed) {
      this.box.update();
      }
  }

  /**
   * Draws the game.
   *
   * @param canvas The canvas to be drawn on.
   */
  public void draw(Canvas canvas) {
    if (this.completed) {
      drawScore(canvas);
      return;
    }
    canvas.drawColor(Color.BLACK); // resets the screen.
    this.box.draw(canvas);
  }

  /**
   * Draws the score of the current game.
   *
   * @param canvas The canvas to be drawn on.
   */
  private void drawScore(Canvas canvas) {
    Paint paint = new Paint();
    paint.setColor(gameColor);
    paint.setTextSize(50);
    canvas.drawText(String.valueOf(this.score), 100, 100, paint);
  }

  /** Sets this game as completed. */
  public void setCompleted() {
    this.completed = true;
  }

  /**
   * Returns if this game is completed or not.
   *
   * @return
   */
  public boolean isCompleted() {
    return this.completed;
  }

  /** Updates the score of the game, if and only if the game is completed. */
  public void updateScore() {
    if (this.completed) {
      int distance = this.box.getLineDistanceFromTarget();
      this.score = 100 - Math.abs(distance);
    }
  }
}
