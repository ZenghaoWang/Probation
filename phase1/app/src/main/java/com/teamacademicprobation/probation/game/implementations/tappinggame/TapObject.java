package com.teamacademicprobation.probation.game.implementations.tappinggame;

class TapObject {

  static int radius = 60;
  private int x;
  private int y;

  TapObject(int x, int y) {
    this.x = x;
    this.y = y;
  }

  int getX() {
    return this.x;
  }

  int getY() {
    return this.y;
  }
}
