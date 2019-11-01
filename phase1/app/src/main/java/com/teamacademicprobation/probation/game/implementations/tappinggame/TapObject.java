package com.teamacademicprobation.probation.game.implementations.tappinggame;
/**
 * A Tap object that the players will tap when playing the game.
 */
class TapObject {
  /** The radius of the circle TapObjects. */
  static int radius = 60;
  /** The x-coordinate TapObjects. */
  private int x;
  /** The y-coordinateTapObjects. */
  private int y;

  /**
   * Initializes the x and y coordinates of the TapObject.
   *
   * @param x The x coordinate of TapObject in pixels.
   * @param y The y coordinate of TapObject in pixels.
   */
  TapObject(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * @return the x-coordinate of the Tap Object
   */
  int getX() {
    return this.x;
  }

  /**
   * @return the y-coordinate of the Tap Object
   */
  int getY() {
    return this.y;
  }
}
