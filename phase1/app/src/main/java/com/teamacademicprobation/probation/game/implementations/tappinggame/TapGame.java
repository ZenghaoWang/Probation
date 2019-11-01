package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.graphics.Canvas;
import com.teamacademicprobation.probation.game.ScoreBoard;
import com.teamacademicprobation.probation.player.PlayerAccess;
import com.teamacademicprobation.probation.player.PlayerManager;
import java.util.Random;

/**
 * A Tapping game where the player tries to tap the target object and avoid tapping non-target.
 */

class TapGame {
  /** The gameID of this game. */
  private static final String GAME_ID = "TapGame";
  /** Represents if the game is completed. */
  private boolean GameComplete;
  private TargetObject targetObjects;
  private NonTargetObject nonTargetObjects;
  private ScoreBoard scoreBoard;
  private Random r = new Random();
  private TargetCounter targetCounter;
  private int x;
  private int y;
  private PlayerAccess playerAccess;
  private String currPlayerID;

  /**
   * Constructor for the Tap game.
   */
  TapGame(int x, int y, String currPlayerID) {
    this.x = x;
    this.y = y;
    this.GameComplete = false;
    this.scoreBoard = new ScoreBoard(x, y);
    this.targetCounter = new TargetCounter(x, y);
    this.playerAccess = new PlayerManager();
    this.currPlayerID = currPlayerID;
  }

  /**
   * Return the status of the game (Completed or not Completed).
   */
  boolean getGameComplete() {
    return this.GameComplete;
  }

  /**
   * Return the score of the game.
   *
   * @return score of the game.
   */
  int getScore() {
    return this.scoreBoard.getScore();
  }

  /**
   * Return targetObject.
   *
   * @return TargetObject
   */
  private TargetObject getTarget() {
    return this.targetObjects;
  }

  /**
   * Return targetObject.
   *
   * @return nonTargetObject
   */
  private NonTargetObject getNonTarget() {
    return this.nonTargetObjects;
  }

  /**
   * Draw the TapGame objects on the canvas.
   */
  void draw(Canvas canvas) {
    this.scoreBoard.draw(canvas);
    this.targetCounter.draw(canvas);
    if (this.targetObjects != null) {
      this.targetObjects.draw(canvas);
    } else if (this.nonTargetObjects != null) {
      this.nonTargetObjects.draw(canvas);
    }
  }


  /**
   * Updates the TapGame.
   */
  void update() {
    double c = Math.random(); //Randomly choose if a Target or Non-target should be created.
    if (c > 0.5) {
      this.targetObjects = null;
      this.nonTargetObjects =
          new NonTargetObject(
              r.nextInt(this.x - TapObject.radius) + TapObject.radius,
              (int) (r.nextInt(this.y - TapObject.radius) + this.y * 0.03));
    } else if (c < 0.5) {
      this.nonTargetObjects = null;
      targetObjects =
          new TargetObject(
              r.nextInt(this.x - TapObject.radius) + TapObject.radius,
              (int) (r.nextInt(this.y - TapObject.radius) + this.y * 0.03));
      targetCounter.addCount();
    }
    //If target objects reach the limit. The game is completed
    if (targetCounter.getTargetCount() == TargetCounter.targetLimit) {
      this.setCompleted();
    }
  }

  /**
   * Sets the game to completed and sends the statistics.
   */
  private void setCompleted() {
    this.GameComplete = true;
    playerAccess.updateStats(currPlayerID, GAME_ID, "score", this.scoreBoard.getScore());
  }


  /**
   * Checks if the user tapped on the non-target or target object.
   *
   * @param touch_x the x-coordinate where the user touched.
   * @param touch_y the x-coordinate where the user touched
   */
  void check_touch(double touch_x, double touch_y) {
    if (this.getTarget() != null) {
      if (Math.pow(touch_x - this.getTarget().getX(), 2)
              + Math.pow(touch_y - this.getTarget().getY(), 2)
          <= Math.pow(TapObject.radius, 2)) {
        this.scoreBoard.earnPoint();
      }
    } else {
      if (Math.pow(touch_x - this.getNonTarget().getX(), 2)
              + (Math.pow(touch_y - this.getNonTarget().getY(), 2))
          <= Math.pow(TapObject.radius, 2)) {
        this.scoreBoard.losePoint();
      }
    }
  }
}
