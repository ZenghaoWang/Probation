package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.graphics.Canvas;

import java.util.Random;

class TapObjectManager {

  private BlueObject blueObjects;
  private RedObject redObjects;
  private Random r = new Random();
  private int x;
  private int y;

  TapObjectManager(int x, int y) {
    this.x = x;
    this.y = y;
  }

  BlueObject getBlue() {
    return this.blueObjects;
  }

  RedObject getRed() {
    return this.redObjects;
  }

  void draw(Canvas canvas) {
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
