package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.graphics.Canvas;

import com.teamacademicprobation.probation.game.ScoreBoard;
import com.teamacademicprobation.probation.player.PlayerAccess;
import com.teamacademicprobation.probation.player.PlayerManager;

import java.io.File;
import java.util.Random;

class TapGame {

  private BlueObject blueObjects;
  private RedObject redObjects;
  private ScoreBoard scoreBoard;
  private Random r = new Random();
  private BlueCounter blueCounter;
  private boolean gameComplete;
  private int x;
  private int y;
  private static final String GAME_ID = "TapGame";
  private PlayerAccess playerAccess;
  private String currPlayerID;

  TapGame(int x, int y, String currPlayerID) {
    this.x = x;
    this.y = y;
    this.gameComplete = false;
    this.scoreBoard = new ScoreBoard(x, y);
    this.blueCounter = new BlueCounter(x, y);
    this.playerAccess = new PlayerManager();
    this.currPlayerID = currPlayerID;
  }

  boolean getgameComplete(){
    return this.gameComplete;
  }

  BlueCounter getBlueCounter() {
    return blueCounter;
  }

  ScoreBoard getScoreBoard() {
    return this.scoreBoard;
  }

  BlueObject getBlue() {
    return this.blueObjects;
  }

  RedObject getRed() {
    return this.redObjects;
  }

  void draw(Canvas canvas) {
    this.scoreBoard.draw(canvas);
    this.blueCounter.draw(canvas);
    if (this.blueObjects != null) {
      this.blueObjects.draw(canvas);
    } else if (this.getRed() != null) {
      this.redObjects.draw(canvas);
    }
  }

  void update() {
    double c = Math.random();
    if (c > 0.5) {
      blueObjects = null;
      redObjects = new RedObject(r.nextInt(this.x - 350) + 100, r.nextInt(this.y - 350) + 100);
    } else if (c < 0.5) {
      redObjects = null;
      blueObjects = new BlueObject(r.nextInt(this.x - 350) + 100, r.nextInt(this.y - 350) + 100);
      blueCounter.addCount();
    }
    if (blueCounter.getBlueCount() == BlueCounter.blueLimit){
      this.setCompleted();
    }
  }

  public void setCompleted() {
    this.gameComplete = true;
    playerAccess.updateStats(currPlayerID, GAME_ID, "score", this.scoreBoard.getScore());
  }

  void check_touch(double touch_x, double touch_y, Canvas canvas) {
    if (this.getBlue() != null) {
      if (Math.pow(touch_x - this.getBlue().getX(), 2)
              + Math.pow(touch_y - this.getBlue().getY(), 2)
          <= Math.pow(TapObject.radius, 2)) {
        this.getScoreBoard().earnPoint();
        this.getScoreBoard().draw(canvas);
      }
    } else {
      if (Math.pow(touch_x - this.getRed().getX(), 2)
              + (Math.pow(touch_y - this.getRed().getY(), 2))
          <= Math.pow(TapObject.radius, 2)) {
          this.getScoreBoard().losePoint();
          this.getScoreBoard().draw(canvas);
        }
      }
    }
}

