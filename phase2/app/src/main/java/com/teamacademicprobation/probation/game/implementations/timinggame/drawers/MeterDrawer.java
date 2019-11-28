package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

/**
 * An implementation of AndroidDrawer that draws the meter.
 */
public class MeterDrawer implements AndroidDrawer {

    /**
     * The rectangle that describes the frame of the meter.
     */
    private Rect meterRect;
    /**
     * The style of the meter.
     */
    private Paint meterPaint;
    /**
     * The coordinates of the cursor. The size of cursorCoordinates must be 4, and are in the
     * following order:
     *
     * <p>[startX, starY, endX, endY].
     */
    private int[] cursorCoordinates;
    /**
     * The style of the cursor.
     */
    private Paint cursorPaint;

    /**
     * Initializes a new MeterDrawer.
     *
     * @param meterRect         The rectangle that describes the frame of the meter.
     * @param meterPaint        The style of the meter.
     * @param cursorCoordinates The coordinates of the cursor.
     * @param cursorPaint       The style of the cursor.
     */
    public MeterDrawer(Rect meterRect, Paint meterPaint, int[] cursorCoordinates, Paint cursorPaint) {
        this.meterRect = meterRect;
        this.meterPaint = meterPaint;
        this.cursorCoordinates = cursorCoordinates;
        this.cursorPaint = cursorPaint;
    }

    @Override
    public void draw(Canvas canvas) {
        drawMeter(canvas);
        drawCursor(canvas);
    }

    /**
     * Draws the meter.
     *
     * @param canvas The canvas onto which to draw on.
     */
    private void drawMeter(Canvas canvas) {
        canvas.drawRect(meterRect, meterPaint);
    }

    /**
     * Draws the cursor.
     *
     * @param canvas The canvas onto which to draw on.
     */
    private void drawCursor(Canvas canvas) {
        canvas.drawLine(
                cursorCoordinates[0],
                cursorCoordinates[1],
                cursorCoordinates[2],
                cursorCoordinates[3],
                cursorPaint);
  }
}
