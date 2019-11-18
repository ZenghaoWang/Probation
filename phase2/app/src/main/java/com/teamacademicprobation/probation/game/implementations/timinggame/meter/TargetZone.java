package com.teamacademicprobation.probation.game.implementations.timinggame.meter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.teamacademicprobation.probation.game.implementations.timinggame.TimingGameStyle;
import com.teamacademicprobation.probation.game.implementations.timinggame.meter.Meter;

/** The target zone that describes the zone the player_ship has to hit. */
class TargetZone {
    /**
     * The start of the leftmost edge of the target zone, described in a ratio: (distance between left
     * edge of box and left edge of the target zone) / width of the meter.
     */
    private double zoneStart;
    /** The width of the target zone. */
    private int zoneWidth;
    /** The paint object that describes the target zone. */
    private Paint paint;

    private Meter meter;

    private double widthRatio;


    /** Initializes the target box's width, where the target box start and the paint. */
    TargetZone(Meter meter, TimingGameStyle gameStyle) {
        this(meter, 0.2, gameStyle);
    }

    TargetZone(Meter meter, double widthRatio, TimingGameStyle gameStyle) {
        this.meter = meter;
        this.zoneWidth = Math.toIntExact(Math.round(meter.getWidth() * widthRatio));
        this.widthRatio = widthRatio;
        this.generateBoxStart();
        this.generatePaint(gameStyle);

    }
    /** Generate a new, random start to the target box. */
    void generateBoxStart() {
//        Random rand = new Random();
//        this.zoneStart = rand.nextInt(Math.toIntExact(Math.round(100-(10*widthRatio)))) * (widthRatio * 0.1);

        do{
            this.zoneStart = Math.random();
        }while(this.zoneStart >= (1-widthRatio));

    }

    /** Initializes the paint style of this target box. */
    private void generatePaint(TimingGameStyle gameStyle) {
        this.paint = new Paint();
        this.paint.setStrokeWidth(3);
        this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paint.setColor(gameStyle.getTargetZoneColor());
    }

    Paint getPaint(){
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

    /**
     * Draws the target box onto the canvas.
     *
     * @param canvas The canvas on which to draw on.
     */
    void draw(Canvas canvas) {
        Rect targetRect = getZoneRect();
        canvas.drawRect(targetRect, paint);
    }

    int getWidth() {
        return this.zoneWidth;
    }

    double getStart() {
        return this.zoneStart;
    }

    double getWidthRatio(){
        return this.widthRatio;
    }
}
