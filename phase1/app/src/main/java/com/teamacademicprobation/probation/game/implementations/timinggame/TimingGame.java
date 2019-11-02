package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.graphics.Canvas;
import android.graphics.Color;

import com.teamacademicprobation.probation.game.ScoreBoard;
import com.teamacademicprobation.probation.player.PlayerAccess;
import com.teamacademicprobation.probation.player.PlayerManager;

/**
 * A game where there is a bar with a target box and a moving line, and the player attempts to time
 * their taps so the line ends up inside the target box.
 */
public class TimingGame {

  private Box box;
  /** Represents if the game is completed. */
  private boolean completed;
  /** The color of the game. In phase 2, can be updated to change based on user preferences. */
  private static int gameColor = Color.CYAN;
  /** The scoreboard of this game. */
  private ScoreBoard scoreBoard;
  /** The amount of times this game is played. */
  private int numPlayed;

  /** The gameID of this game. */
  private static final String GAME_ID = "TimingGame";

  private PlayerAccess playerAccess;

  private String currPlayerID;

  private final int TOTAL_LEVELS = 5;

  /** Initializes the line and the hitbox. Sets the initial score to 0 and not completed. */
  TimingGame(int screenWidth, int screenHeight, String currPlayerID) {

    this.box = new Box(screenWidth, screenHeight);
    this.scoreBoard = new ScoreBoard(screenWidth, screenHeight);
    this.numPlayed = 0;
    this.completed = false;
    this.playerAccess = new PlayerManager();
    this.currPlayerID = currPlayerID;
  }

  /**
   * Returns the Color of the game.
   *
   * @return color of the game
   */
  static int getGameColor() {
    return gameColor;
  }

  /** Updates the game. */
  public void update() {
    this.box.update();
    if (this.numPlayed >= TOTAL_LEVELS) {
      this.setCompleted();
    }
  }

  /**
   * Draws the game.
   *
   * @param canvas The canvas to be drawn on.
   */
  public void draw(Canvas canvas) {
    canvas.drawColor(Color.BLACK); // resets the screen.
    this.box.draw(canvas);
    this.scoreBoard.draw(canvas);
  }

  /** Sets this game as completed. */
  public void setCompleted() {
    this.completed = true;
    playerAccess.updateStats(currPlayerID, GAME_ID, "score", this.scoreBoard.getScore());
  }

  /**
   * Returns if this game is completed or not.
   *
   * @return Completed game or not.
   */
  public boolean isCompleted() {
    return this.completed;
  }

  /**
   * Updates the score of this timing game. The score algorithm is visualized here:
   * https://www.desmos.com/calculator/7rxfhnhuog
   */
  public void updateScore() {
    int lineDistance = Math.abs(this.box.getLineDistanceFromTarget());
    int targetBoxWidth = this.box.getTargetBoxWidth();

    if (lineDistance <= targetBoxWidth / 2) {
      this.scoreBoard.setScore(
          this.scoreBoard.getScore() + 100 - (40 * (lineDistance) / targetBoxWidth));
    } else {
      this.scoreBoard.setScore(
          Math.max(0, this.scoreBoard.getScore() + 160 - (160 * (lineDistance) / targetBoxWidth)));
    }

    this.box.newTarget();
    this.numPlayed++;
  }

  /**
   * Returns the score of this game.
   *
   * @return int, the score of this game.
   */
  public int getScore() {

    return this.scoreBoard.getScore();
  }
}
