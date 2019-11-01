package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * A Box object that has a SlidingLine and a TargetBox (Potentially more in the future!) for
 * TimingGame.
 */
class Box {

  /** The width of the box. */
  private int boxWidth;
  /** The height of the box. */
  private int boxHeight;
  /** The length between the left-most edge of the screen and the left-most edge of the box. */
  private int boxWidthMargins;
  /** The length between the top-most edge of the screen and the top-most edge of the box. */
  private int boxHeightMargins;

  /** The zone the player has to try to hit. */
  private TargetBox targetBox;
  /** The sliding line. */
  private SlidingLine slidingLine;

  /** The paint object that describes the style of this box. */
  private Paint boxPaint;

  /**
   * Initializes the boxWidth, boxHeight, margins, paint, target box and sliding line. The boxWidth,
   * boxHeight, margins in ratios of the screenWidth and screenHeight.
   *
   * @param screenWidth The width of the screen in pixels.
   * @param screenHeight The height of the screen in pixels.
   */
  Box(int screenWidth, int screenHeight) {
    this.boxWidth = Math.toIntExact(Math.round(screenWidth * 0.8));
    this.boxHeight = Math.toIntExact(Math.round(screenHeight * 0.06));
    this.boxHeightMargins = Math.toIntExact(Math.round((screenHeight - this.boxHeight) / 2));
    this.boxWidthMargins = Math.toIntExact(Math.round((screenWidth - this.boxWidth) / 2));
    generatePaint();
    this.targetBox = new TargetBox();
    this.slidingLine = new SlidingLine();
  }

  /** Initializes the paint style for this box. */
  private void generatePaint() {
    this.boxPaint = new Paint();
    this.boxPaint.setStrokeWidth(3);
    this.boxPaint.setStyle(Paint.Style.STROKE);
    this.boxPaint.setColor(TimingGame.getGameColor());
  }

  /**
   * Draws this box, target box, and line onto the canvas.
   *
   * @param canvas The canvas on which to draw on.
   */
  void draw(Canvas canvas) {
    drawBox(canvas);
    targetBox.draw(canvas);
    slidingLine.draw(canvas);
  }

  /** Updates this box that are updatable. */
  void update() {
    this.slidingLine.update();
  }

  /**
   * Draws the box only onto the canvas.
   *
   * @param canvas The canvas on which to draw on.
   */
  private void drawBox(Canvas canvas) {
    Rect box = getBoxRect();

    canvas.drawRect(box, this.boxPaint);
  }

  /**
   * Returns a Rect object that represents the dimensions of this box.
   *
   * @return Rect
   */
  private Rect getBoxRect() {
    return new Rect(
        boxWidthMargins,
        boxHeightMargins,
        boxWidthMargins + boxWidth,
        boxHeightMargins + boxHeight);
  }

  /**
   * Returns the distance from the sliding line to the center of the target, in pixels.
   *
   * @return int
   */
  int getLineDistanceFromTarget() {
    return slidingLine.linePosition - targetBox.getTargetCenter();
  }

  /**
   * Returns the width of the target in pixels.
   *
   * @return int
   */
  int getTargetBoxWidth() {
    return this.targetBox.targetBoxWidth;
  }

  /** Randomly make the target box move locations. */
  void newTarget() {
    this.targetBox.generateBoxStart();
  }

  /** The target box that describes the zone the player has to hit. */
  private class TargetBox {
    /**
     * The start of the leftmost edge of the targetBox, described in a ratio: (distance between left
     * edge of box and left edge of the targetBox) / width of box.
     */
    private double targetBoxStart;
    /** The width of the target box. */
    private int targetBoxWidth;
    /** The paint object that describes the target box. */
    private Paint targetBoxPaint;

    /** Initializes the target box's width, where the target box start and the paint. */
    TargetBox() {
      this.targetBoxWidth = Math.toIntExact(Math.round(boxWidth * 0.1));
      generateBoxStart();
      generateTargetPaint();
    }

    /** Generate a new, random start to the target box. */
    private void generateBoxStart() {
      Random rand = new Random();
      this.targetBoxStart = rand.nextInt(90) * 0.01;
    }

    /** Initializes the paint style of this target box. */
    private void generateTargetPaint() {
      this.targetBoxPaint = new Paint();
      this.targetBoxPaint.setStrokeWidth(3);
      this.targetBoxPaint.setStyle(Paint.Style.FILL_AND_STROKE);
      this.targetBoxPaint.setColor(TimingGame.getGameColor());
    }

    /**
     * Returns a Rect object that represents the dimensions of the target box.
     *
     * @return Rect
     */
    private Rect getTargetBoxRect() {
      int left = Math.toIntExact(Math.round(boxWidthMargins + (targetBoxStart * boxWidth)));
      int top = boxHeightMargins;
      int right = left + targetBoxWidth;
      int bottom = top + boxHeight;

      return new Rect(left, top, right, bottom);
    }

    /**
     * Returns the center of the targetBox in relation to the whole box, not the screen.
     *
     * @return int
     */
    private int getTargetCenter() {
      Rect targetBox = getTargetBoxRect();
      return targetBox.centerX() - boxWidthMargins;
    }

    /**
     * Draws the target box onto the canvas.
     *
     * @param canvas The canvas on which to draw on.
     */
    private void draw(Canvas canvas) {
      Rect targetBox = getTargetBoxRect();
      canvas.drawRect(targetBox, targetBoxPaint);
    }
  }

  /** The sliding line of this object. */
  private class SlidingLine {
    /** The X position of this line in relation to the hitBox. */
    private int linePosition;
    /** The velocity of the line. */
    private int lineVelocity;
    /** The paint object that describes the style of the line. */
    private Paint paint;

    /**
     * Initializes the position of this line and the velocity of the line. The line starts by moving
     * right, and starts at the left-most edge of the box.
     */
    private SlidingLine() {
      this.linePosition = 0;
      this.lineVelocity =
          Math.toIntExact(Math.round(boxWidth * 0.05)); // This can be adjusted for difficulty.
      generatePaint();
    }

    /** Initializes the paint style of this line. */
    private void generatePaint() {
      this.paint = new Paint();
      this.paint.setStyle(Paint.Style.STROKE);
      this.paint.setStrokeWidth(7);
      this.paint.setColor(TimingGame.getGameColor());
    }

    /**
     * Moves the line according to its velocity. If the line's position is less than 0 or more than
     * the width of the box, turn around.
     */
    private void update() {
      this.linePosition += this.lineVelocity;
      if (this.linePosition <= 0 || this.linePosition >= boxWidth) {
        // This code makes it so that the line matches up with the box.
        this.linePosition = Math.max(0, Math.min(this.linePosition, boxWidth));
        this.lineVelocity *= -1;
      }
    }

    /**
     * Draws the line in it's current position onto the canvas.
     *
     * @param canvas The canvas on which to draw on.
     */
    private void draw(Canvas canvas) {
      int currX = linePosition + boxWidthMargins;

      double lineMargins = 0.1; // The ratio of how long the "extensions" are.
      long yMargins = Math.round(boxHeight * lineMargins);
      int topY = Math.toIntExact(Math.round(boxHeightMargins - yMargins));
      int bottomY = Math.toIntExact(Math.round(boxHeightMargins + boxHeight + yMargins));

      canvas.drawLine(currX, topY, currX, bottomY, paint);
    }
  }
}
