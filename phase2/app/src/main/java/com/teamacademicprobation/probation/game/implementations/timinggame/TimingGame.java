package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.graphics.Canvas;
import android.graphics.Color;

import com.teamacademicprobation.probation.game.ScoreBoard;
import com.teamacademicprobation.probation.player.PlayerAccess;
import com.teamacademicprobation.probation.player.PlayerManager;

/**
 * A game where there is a bar with a target meter and a moving line, and the player attempts to time
 * their taps so the line ends up inside the target meter.
 */
public class TimingGame {

  private Meter meter;
  /** Represents if the game is completed. */
  private boolean completed;
  /** The scoreboard of this game. */
  private ScoreBoard scoreBoard;
  /** The amount of times this game is played. */
  private int numPlayed;

  /** The gameID of this game. */
  private static final String GAME_ID = "TimingGame";

  private PlayerAccess playerAccess;

  private String currPlayerID;

  private final int TOTAL_LEVELS = 5;

//TODO: MAKE CURSOR A DIFFERENT COLOR, ADD IN TIMINGGAMESTYLES

  /** Initializes the line and the hitbox. Sets the initial score to 0 and not completed. */
  TimingGame(int screenWidth, int screenHeight, String currPlayerID) {

    // Change for preferences
    TimingGameStyle gameStyle = new TimingGameStyle(TimingGameStyles.STYLE3);

    this.meter = new Meter(screenWidth, screenHeight, gameStyle);
    this.scoreBoard = new ScoreBoard(screenWidth, screenHeight);
    this.numPlayed = 0;
    this.completed = false;
    this.playerAccess = new PlayerManager();
    this.currPlayerID = currPlayerID;
  }

  /** Updates the game. */
  public void update() {
    this.meter.update();
//    if (this.numPlayed >= TOTAL_LEVELS) {
//      this.setCompleted();
//    }
    if(this.numPlayed%3 == 0){
      this.meter.setBonusVisible(true);
    }
  }

  /**
   * Draws the game.
   *
   * @param canvas The canvas to be drawn on.
   */
  public void draw(Canvas canvas) {
    canvas.drawColor(Color.BLACK); // resets the screen.
    this.meter.draw(canvas);
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
   * Updates the score of this timing game.
   */
  public void updateScore() {

    if (this.meter.cursorNearFocus()) {
      this.scoreBoard.earnPoint();
      this.scoreBoard.earnPoint();
    } else if (this.meter.cursorNearTarget()) {
      this.scoreBoard.earnPoint();
    }
    this.meter.newTarget();
    this.meter.setBonusVisible(false);
    this.numPlayed++;
    }

  /**
   * Returns the score of this game.
   *
   * @return int, the score of this game.
   */
  public int getScore(){

    return this.scoreBoard.getScore();
  }
}
