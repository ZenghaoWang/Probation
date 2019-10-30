package com.teamacademicprobation.probation.game.implementations.tappinggame;

public class TapObject {

  static int radius = 60;
  private int x;
  private int y;

  public TapObject(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

}