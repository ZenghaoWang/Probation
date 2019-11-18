package com.teamacademicprobation.probation.game.implementations.timinggame.meter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;


import com.teamacademicprobation.probation.game.implementations.timinggame.TimingGameStyle;

/**
 * A Meter object that has a Cursor and a TargetZone (Potentially more in the future!) for
 * TimingGame.
 */
public class Meter {

  /** The width of the meter. */
  private int width;
  /** The height of the meter. */
  private int height;
  /** The length between the left-most edge of the screen and the left-most edge of the meter. */
  private int widthMargins;
  /** The length between the top-most edge of the screen and the top-most edge of the meter. */
  private int heightMargins;

  /** The zone the player_ship has to try to hit. */
  private TargetZone targetZone;

  private BonusZone bonusZone;
  /** The sliding line. */
  private Cursor cursor;

  /** The paint object that describes the style of this box. */
  private Paint paint;

  /**
   * Initializes the width, height, margins, paint, target box and sliding line. The width, height,
   * margins in ratios of the screenWidth and screenHeight.
   *
   * @param screenWidth The width of the screen in pixels.
   * @param screenHeight The height of the screen in pixels.
   * @param gameStyle Object that defines the overall style.
   */
  public Meter(int screenWidth, int screenHeight, TimingGameStyle gameStyle) {
    generateProportions(screenWidth, screenHeight);
    generateContents(gameStyle);
    generatePaint(gameStyle);
  }

  private void generateContents(TimingGameStyle gameStyle) {
    this.targetZone = new TargetZone(this, gameStyle);
    this.bonusZone = new BonusZone(this, gameStyle);
    this.cursor = new Cursor(gameStyle);
  }

  private void generateProportions(int screenWidth, int screenHeight) {
    this.width = Math.toIntExact(Math.round(screenWidth * 0.8));
    this.height = Math.toIntExact(Math.round(screenHeight * 0.06));
    this.heightMargins = Math.toIntExact(Math.round((2*screenHeight)/ 3 - (this.height/2)));
    this.widthMargins = Math.toIntExact(Math.round((screenWidth - this.width) / 2));
  }

  /** Initializes the paint style for this box. */
  private void generatePaint(TimingGameStyle gameStyle) {
    this.paint = new Paint();
    this.paint.setStrokeWidth(10);
    this.paint.setStyle(Paint.Style.STROKE);
    this.paint.setColor(gameStyle.getMeterColor());
  }

  /**
   * Draws this box, target box, and line onto the canvas.
   *
   * @param canvas The canvas on which to draw on.
   */
  public void draw(Canvas canvas) {
    if (bonusZone.isVisible()) {
      bonusZone.draw(canvas);
    }
    targetZone.draw(canvas);
    drawMeter(canvas);
    cursor.draw(canvas);
  }

  /** Updates this box that are updatable. */
  public void update() {
    this.cursor.update();
  }

  /**
   * Draws the box only onto the canvas.
   *
   * @param canvas The canvas on which to draw on.
   */
  private void drawMeter(Canvas canvas) {
    Rect box = getMeterRect();

    canvas.drawRect(box, this.paint);
  }

  /**
   * Returns a Rect object that represents the dimensions of this box.
   *
   * @return Rect
   */
  private Rect getMeterRect() {
    return new Rect(widthMargins, heightMargins, widthMargins + width, heightMargins + height);
  }

  /**
   * Returns the distance from the sliding line to the center of the target, in pixels.
   *
   * @return int
   */
  private int getCursorDistanceFromTarget() {
    return cursor.cursorPosition - targetZone.getTargetCenter();
  }

  /**
   * Returns the width of the target in pixels.
   *
   * @return int
   */
  private int getTargetBoxWidth() {
    return this.targetZone.getWidth();
  }

  /** Randomly make the target box move locations. */
  public void newTarget() {
//    this.targetZone.generateBoxStart();
//    if (this.bonusZone.isVisible()) {
//      int boxDistance;
//      int threshold =
//          Math.toIntExact(Math.round((this.bonusZone.getWidth() + this.targetZone.getWidth()) / 2))
//              + 50;
//      do {
//        this.bonusZone.generateBoxStart();
//        boxDistance =
//            Math.abs(this.bonusZone.getTargetCenter() - this.targetZone.getTargetCenter());
//        // TODO: GET RID
//        System.out.println("Distance, Threshold " + boxDistance + " ," + threshold);
//      } while (boxDistance <= threshold);
//    }
//
    this.targetZone.generateBoxStart();
      do{
        this.bonusZone.generateBoxStart();
      }while(this.overlapping());
  }

  private boolean overlapping() {
    if(this.targetZone.getStart() <= this.bonusZone.getStart()){
      return bonusZone.getStart() - targetZone.getStart() < targetZone.getWidthRatio() + 0.1;
    }
    else{
      return targetZone.getStart() - bonusZone.getStart() < bonusZone.getWidthRatio() + 0.1;
    }
  }

  public boolean cursorNearFocus() {
    if (this.isBonusVisible()){
      int cursorBonusDistance = Math.abs(this.getCursorDistanceFromBonus());
      int bonusBoxWidth = this.getBonusBoxWidth();
      return cursorBonusDistance <= bonusBoxWidth / 2;
    }
    return false;
  }

  public boolean cursorNearTarget() {
    int cursorTargetDistance = Math.abs(this.getCursorDistanceFromTarget());
    int targetBoxWidth = this.getTargetBoxWidth();
    return cursorTargetDistance <= targetBoxWidth / 2;

  }
  // ====== GETTERS ======
  int getWidth() {
    return this.width;
  }

  int getHeight() {
    return this.height;
  }

  int getWidthMargins() {
    return this.widthMargins;
  }

  int getHeightMargins() {
    return this.heightMargins;
  }

  private int getBonusBoxWidth() {
    return this.bonusZone.getWidth();
  }

  private int getCursorDistanceFromBonus() {
    return cursor.cursorPosition - bonusZone.getTargetCenter();
  }

  private boolean isBonusVisible() {
    return bonusZone.isVisible();
  }

  public void setBonusVisible(boolean b) {
    this.bonusZone.setVisible(b);
  }

  // ====== END OF GETTERS =====

  /** The sliding line of this object. */
  private class Cursor {
    /** The X position of this line in relation to the hitBox. */
    private int cursorPosition;
    /** The velocity of the line. */
    private int cursorVelocity;
    /** The paint object that describes the style of the line. */
    private Paint paint;

    /**
     * Initializes the position of this line and the velocity of the line. The line starts by moving
     * right, and starts at the left-most edge of the box.
     */
    private Cursor(TimingGameStyle gameStyle) {
      this.cursorPosition = 0;
      this.cursorVelocity =
          Math.toIntExact(Math.round(width * 0.03)); // This can be adjusted for difficulty.
      generatePaint(gameStyle);
    }

    /**
     * Initializes the paint style of this line.
     *
     * @param gameStyle Object that defines the overall style.
     */
    private void generatePaint(TimingGameStyle gameStyle) {
      this.paint = new Paint();
      this.paint.setStyle(Paint.Style.STROKE);
      this.paint.setStrokeWidth(10);
      this.paint.setColor(gameStyle.getCursorColor());
    }

    /**
     * Moves the line according to its velocity. If the line's position is less than 0 or more than
     * the width of the box, turn around.
     */
    private void update() {
      this.cursorPosition += this.cursorVelocity;
      if (this.cursorPosition <= 0 || this.cursorPosition >= width) {
        // This code makes it so that the line matches up with the box.
        this.cursorPosition = Math.max(0, Math.min(this.cursorPosition, width));
        this.cursorVelocity *= -1;
      }
    }

    /**
     * Draws the line in it's current position onto the canvas.
     *
     * @param canvas The canvas on which to draw on.
     */
    private void draw(Canvas canvas) {
      int currX = cursorPosition + widthMargins;

      double margins = 0.2; // The ratio of how long the "extensions" are.
      long yMargins = Math.round(height * margins);
      int topY = Math.toIntExact(Math.round(heightMargins - yMargins));
      int bottomY = Math.toIntExact(Math.round(heightMargins + height + yMargins));

      canvas.drawLine(currX, topY, currX, bottomY, paint);
    }
  }
}
