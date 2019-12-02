package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

/**
 * An implementation of AndroidDrawer that draws the health bar.
 */
public class HealthBarDrawer implements AndroidDrawer {

    /**
     * The Rect that represents the frame of the health bar.
     */
    private Rect healthFrameRect;
    /**
     * The Rect that represents the filled area of the health bar.
     */
    private Rect healthBarRect;
    /**
     * The style of the frame.
     */
    private Paint framePaint;
    /**
     * The style of the bar.
     */
    private Paint barPaint;

    /**
     * Initializes a new HealthBarDrawer.
     *
     * @param healthFrameRect The Rect that represents the frame of the health bar.
     * @param healthBarRect   The Rect that represents the filled area of the health bar.
     * @param framePaint      The style of the frame.
     * @param barPaint        The style of the bar.
     */
    public HealthBarDrawer(
            Rect healthFrameRect, Rect healthBarRect, Paint framePaint, Paint barPaint) {
        this.healthFrameRect = healthFrameRect;
        this.healthBarRect = healthBarRect;
        this.framePaint = framePaint;
        this.barPaint = barPaint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(healthBarRect, barPaint);
        canvas.drawRect(healthFrameRect, framePaint);
    }
}
