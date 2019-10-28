package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.graphics.Canvas;
import android.graphics.Paint;

class SlidingLine {

  /** The X position of this line in relation to the hitBox. */
  private int linePosition;
  /** The velocity of the line. */
  private int lineVelocity;
  /** The box this line slides across. */
  private HitBox hitBox;
  /** The paint object that describes the style of the line. */
  private Paint paint;

  /**
   * Initializes the sliding line.
   *
   * @param screenWidth The screen width in pixels
   * @param screenHeight The screen height in pixels
   * @param hitBox The hitbox this line is sliding across.
   */
  public SlidingLine(int screenWidth, int screenHeight, HitBox hitBox) {
    this.hitBox = hitBox;
    this.linePosition = 0;
    this.lineVelocity = this.hitBox.getWidth() / 40; // This can be adjusted for difficulty.
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
   * Updates the position of the line. If the line reaches the end of the box, reverse it's
   * direction.
   */
  public void update() {
    this.linePosition += this.lineVelocity;
    if (this.linePosition <= 0 || this.linePosition >= hitBox.getWidth()) {
      // This code makes it so that the line matches up with the box.
      this.linePosition = Math.max(0, Math.min(this.linePosition, hitBox.getWidth()));
      this.lineVelocity *= -1;
    }
  }

  /**
   * Draws the line.
   *
   * @param canvas The canvas on which to draw on.
   */
  public void draw(Canvas canvas) {
    int currX = linePosition + hitBox.getWidthMargins();

    double lineMargins = 0.1; // The ratio of how long the "extensions" are.
    long yMargins = Math.round(hitBox.getHeight() * lineMargins);
    int topY = Math.toIntExact(Math.round(hitBox.getHeightMargins() - yMargins));
    int bottomY =
        Math.toIntExact(Math.round(hitBox.getHeightMargins() + hitBox.getHeight() + yMargins));

    canvas.drawLine(currX, topY, currX, bottomY, paint);
  }

  /**
   * Returns the actual position of this line relative to the screen, not the box.
   *
   * @return int : position of the line.
   */
  public int getActualPosition() {
    return this.linePosition + hitBox.getWidthMargins();
  }
}
