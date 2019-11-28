package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

public class HealthBarDrawer implements AndroidDrawer {

    private Rect healthFrameRect;
    private Rect healthBarRect;
    private Paint framePaint;
    private Paint barPaint;

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
