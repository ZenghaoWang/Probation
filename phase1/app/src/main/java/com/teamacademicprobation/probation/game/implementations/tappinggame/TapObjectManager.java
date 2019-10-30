package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.graphics.Canvas;

import com.teamacademicprobation.probation.game.ScoreBoard;

import java.util.Random;

class TapObjectManager {

  private BlueObject blueObjects;
  private RedObject redObjects;
  private ScoreBoard scoreBoard;
  private Random r = new Random();
  private int x;
  private int y;

  TapObjectManager(int x, int y) {
    this.x = x;
    this.y = y;
    this.scoreBoard = new ScoreBoard(x, y);
  }

  ScoreBoard getScoreBoard(){
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
    }
  }
}
