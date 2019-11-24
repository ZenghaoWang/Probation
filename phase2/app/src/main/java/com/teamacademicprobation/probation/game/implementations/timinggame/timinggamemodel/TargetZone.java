package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.teamacademicprobation.probation.game.implementations.timinggame.drawers.Drawable;
import com.teamacademicprobation.probation.game.implementations.timinggame.drawers.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.timinggame.drawers.ZoneDrawer;

import java.util.ArrayList;
import java.util.List;

/**
 * The target zone that describes the zone the player_ship has to hit.
 */
class TargetZone implements Drawable {
    /**
     * The start of the leftmost edge of the target zone, described in a ratio: (distance between left
     * edge of box and left edge of the target zone) / width of the meter.
     */
    private double zoneStart;
    /**
     * The width of the target zone.
     */
    private int zoneWidth;
    /**
     * The paint object that describes the target zone.
     */
    private Paint paint;

    /**
     * The meter this target zone is in.
     */
    private Meter meter;

    /**
     * The ratio of the width of the target zone in relation to the meter.
     */
    private double widthRatio;


    /**
     * Initializes a new target zone with the default width ratio.
     *
     * @param meter     The meter this target zone is in.
     * @param gameStyle The style of the game.
     */
    TargetZone(Meter meter, TimingGameStyle gameStyle) {
        this(meter, 0.2, gameStyle);
    }

    /**
     * Initializes a new target zone with a specified width ratio.
     *
     * @param meter      The meter this target zone is ion.
     * @param widthRatio The ratio of the width of the target zone in relation ot the meter.
     * @param gameStyle  The style of the game.
     */
    TargetZone(Meter meter, double widthRatio, TimingGameStyle gameStyle) {
        this.meter = meter;
        this.zoneWidth = Math.toIntExact(Math.round(meter.getWidth() * widthRatio));
        this.widthRatio = widthRatio;
        this.generateBoxStart();
        this.generatePaint(gameStyle);

    }

    /**
     * Generate a new, random start to the target box.
     */
    void generateBoxStart() {
        do {
            this.zoneStart = Math.random();
        } while (this.zoneStart >= (1 - widthRatio));

    }

    /**
     * Initializes the paint style of this target box.
     *
     * @param gameStyle The style of the game.
     */
    private void generatePaint(TimingGameStyle gameStyle) {
        this.paint = new Paint();
        this.paint.setStrokeWidth(3);
        this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paint.setColor(gameStyle.getTargetZoneColor());
    }

    // ====== GETTERS =====
    Paint getPaint() {
        return this.paint;
    }

    /**
     * Returns a Rect object that represents the dimensions of the target box.
     *
     * @return Rect
     */
    private Rect getZoneRect() {
        int left = Math.toIntExact(Math.round(meter.getWidthMargins() + (zoneStart * meter.getWidth())));
        int top = meter.getHeightMargins();
        int right = left + zoneWidth;
        int bottom = top + meter.getHeight();

        return new Rect(left, top, right, bottom);
    }

    /**
     * Returns the center of the targetBox in relation to the whole box, not the screen.
     *
     * @return int
     */
    int getTargetCenter() {
        Rect targetRect = getZoneRect();
        return targetRect.centerX() - meter.getWidthMargins();
    }


    int getWidth() {
        return this.zoneWidth;
    }

    double getStart() {
        return this.zoneStart;
    }

    double getWidthRatio() {
        return this.widthRatio;
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        drawers.add(new ZoneDrawer(getZoneRect(), getPaint()));
        return drawers;
    }

    // ===== END OF GETTERS ======
}
