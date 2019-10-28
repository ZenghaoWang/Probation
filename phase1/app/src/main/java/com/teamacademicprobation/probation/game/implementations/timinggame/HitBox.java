package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

class HitBox {

  /** The width of the box in pixels. */
  private int boxWidth;
  /** The height of the box in pixels. */
  private int boxHeight;
  /** The width of the target box in pixels. */
  private int targetWidth;
  /** A ratio that represents (the distance from the most left of the box )/(width of the box.) */
  private double targetStart;

  /**
   * The distance of the left most edge of the box to the left most edge of the screen in pixels.
   */
  private int boxWidthMargins;
  /** The distance of the top most edge of the box to the top most edge of the screen in pixels. */
  private int boxHeightMargins;

  /** The Paint object that dictates the style of the target and the box, respectively. */
  private Paint targetPaint;

  private Paint boxPaint;

  /**
   * Initializes the box parameters and the paint styles.
   *
   * @param screenWidth The screen width in pixels.
   * @param screenHeight The screen width in pixels.
   */
  public HitBox(int screenWidth, int screenHeight) {
    this.boxWidth = Math.toIntExact(Math.round(screenWidth * 0.8));
    this.boxHeight = Math.toIntExact(Math.round(screenHeight * 0.06));

    this.targetWidth = Math.toIntExact(Math.round(this.boxWidth * 0.1));

    // Randomly selects the start of the target zone.
    Random rand = new Random();
    this.targetStart = rand.nextInt(90) * 0.01;

    this.boxHeightMargins = Math.toIntExact(Math.round((screenHeight - this.boxHeight) / 2));
    this.boxWidthMargins = Math.toIntExact(Math.round((screenWidth - this.boxWidth) / 2));
    generateBoxPaint();
    generateTargetPaint();
  }

  /** Initializes the paint style of the target box. The target box is filled. */
  private void generateTargetPaint() {
    this.targetPaint = new Paint();
    this.targetPaint.setStrokeWidth(3);
    this.targetPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    this.targetPaint.setColor(TimingGame.getGameColor());
  }

  /** Initializes the paint style of the box. The box is not filled. */
  private void generateBoxPaint() {
    this.boxPaint = new Paint();
    this.boxPaint.setStrokeWidth(3);
    this.boxPaint.setStyle(Paint.Style.STROKE);
    this.boxPaint.setColor(TimingGame.getGameColor());
  }

  // ===== GETTER METHODS =====
  public int getHeight() {
    return this.boxHeight;
  }

  public int getWidth() {
    return this.boxWidth;
  }

  public int getWidthMargins() {
    return this.boxWidthMargins;
  }

  public float getHeightMargins() {
    return this.boxHeightMargins;
  }

  /**
   * Gets the center of the target box.
   *
   * @return center of the target box.
   */
  public int getTargetCenter() {

    Rect targetBox = targetBoxToRect();
    return targetBox.centerX();
  }

  // ===== END OF GETTER METHODS =====

  /**
   * Draws the box and the targetbox.
   *
   * @param canvas The canvas on which to draw on.
   */
  public void draw(Canvas canvas) {
    drawBox(canvas);
    drawTargetBox(canvas);
  }

  /**
   * Draws the target Box.
   *
   * @param canvas THe canvas on which to draw on.
   */
  private void drawTargetBox(Canvas canvas) {

    Rect targetBox = targetBoxToRect();

    canvas.drawRect(targetBox, this.targetPaint);
  }

  /**
   * Gets a Rect representation of the target box.
   *
   * @return
   */
  private Rect targetBoxToRect() {
    int left = Math.toIntExact(Math.round(boxWidthMargins + (targetStart * boxWidth)));
    int top = boxHeightMargins;
    int right = left + targetWidth;
    int bottom = top + boxHeight;

    return new Rect(left, top, right, bottom);
  }

  /**
   * Draws the box.
   *
   * @param canvas The canvas on which to draw on.
   */
  private void drawBox(Canvas canvas) {
    Rect box =
        new Rect(
            boxWidthMargins,
            boxHeightMargins,
            boxWidthMargins + boxWidth,
            boxHeightMargins + boxHeight);

    canvas.drawRect(box, this.boxPaint);
  }
}
