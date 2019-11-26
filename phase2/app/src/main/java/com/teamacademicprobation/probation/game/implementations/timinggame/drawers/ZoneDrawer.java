package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

/**
 * An implementation of AndroidDrawer that draws the zones on the meter.
 */
public class ZoneDrawer implements AndroidDrawer {
    /**
     * The rectangle that defines the zone area.
     */
    private Rect zoneRect;
    /**
     * The style of the zone.
     */
    private Paint paint;

    /**
     * Initializes a new ZoneDrawer.
     *
     * @param zoneRect The rectangle that defines the zone area.
     * @param paint    The style of the zone.
     */
    public ZoneDrawer(Rect zoneRect, Paint paint) {
        this.zoneRect = zoneRect;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(this.zoneRect, this.paint);
    }
}
