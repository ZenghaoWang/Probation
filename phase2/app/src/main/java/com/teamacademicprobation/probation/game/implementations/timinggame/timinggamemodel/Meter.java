package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

import android.graphics.Paint;
import android.graphics.Rect;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.timinggame.drawers.MeterDrawer;

import java.util.ArrayList;
import java.util.List;

/**
 * A Meter object that has a Cursor, a target zone and a bonus zone for TimingGame.
 */
class Meter implements Drawable {

    /**
     * The width of the meter.
     */
    private int width;
    /**
     * The height of the meter.
     */
    private int height;
    /**
     * The length between the left-most edge of the screen and the left-most edge of the meter.
     */
    private int widthMargins;
    /**
     * The length between the top-most edge of the screen and the top-most edge of the meter.
     */
    private int heightMargins;

    /**
     * The zone the player_ship has to try to hit.
     */
    private TargetZone targetZone;

    /**
     * The sliding line.
     */
    private Cursor cursor;

    /**
     * The paint object that describes the style of this box.
     */
    private Paint paint;

    /**
     * Initializes the width, height, margins, paint, target box and sliding line. The width, height,
     * margins in ratios of the screenWidth and screenHeight.
     *
     * @param screenWidth  The width of the screen in pixels.
     * @param screenHeight The height of the screen in pixels.
     * @param gameStyle    Object that defines the overall style.
     */
    Meter(int screenWidth, int screenHeight, TimingGameStyle gameStyle) {
        generateProportions(screenWidth, screenHeight);
        generateContents(gameStyle);
        generatePaint(gameStyle);
    }

    // ===== GENERATION METHODS =====
    private void generateContents(TimingGameStyle gameStyle) {
        this.targetZone = new TargetZone(this, gameStyle);
        this.cursor = new Cursor(gameStyle);
    }

    private void generateProportions(int screenWidth, int screenHeight) {
        this.width = Math.toIntExact(Math.round(screenWidth * 0.8));
        this.height = Math.toIntExact(Math.round(screenHeight * 0.06));

        // 2/3 is ratio of distance of top of the meter from top of the screen.
        this.heightMargins = Math.toIntExact(Math.round((2 * screenHeight) / 3 - (this.height / 2)));
        this.widthMargins = Math.toIntExact(Math.round((screenWidth - this.width) / 2));
    }

    private void generatePaint(TimingGameStyle gameStyle) {
        this.paint = new Paint();
        this.paint.setStrokeWidth(10);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(gameStyle.getMeterColor());
    }

    // ===== END OF GENERATION METHODS =====

    /**
     * Updates this meter.
     */
    public void update() {
        this.cursor.update();
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

    /**
     * Randomly make the target box move locations.
     */
    void newTarget() {
        this.targetZone.generateBoxStart();
    }

    /**
     * Determines if the cursor is in the target zone.
     *
     * @return true if cursor in target zone.
     */
    boolean cursorInTarget() {
        int cursorTargetDistance = Math.abs(this.getCursorDistanceFromTarget());
        int targetBoxWidth = this.getTargetBoxWidth();
        return cursorTargetDistance <= targetBoxWidth / 2;
    }

    // ====== GETTERS/SETTERS  ======
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

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>(targetZone.getDrawers());
        AndroidDrawer meterDrawer =
                new MeterDrawer(getMeterRect(), paint, this.cursor.getCoordinates(), this.cursor.paint);
        drawers.add(meterDrawer);

        return drawers;
    }

    // ====== END OF GETTERS =====

    /**
     * The cursor of this object.
     */
    private class Cursor {
        /**
         * The x position of this cursor in relation to the meter.
         */
        private int cursorPosition;
        /**
         * The velocity of the cusor.
         */
        private int cursorVelocity;
        /**
         * The paint object that describes the style of the score.
         */
        private Paint paint;

        /**
         * Initializes the position of this cursor and its velocity. The cursor starts by moving right,
         * and starts at the left-most edge of the meter.
         */
        private Cursor(TimingGameStyle gameStyle) {
            this.cursorPosition = 0;
            this.cursorVelocity =
                    Math.toIntExact(Math.round(width * 0.03)); // This can be adjusted for speed.
            generatePaint(gameStyle);
        }

        /**
         * Initializes the paint style of this cursor.
         *
         * @param gameStyle The style of the game.
         */
        private void generatePaint(TimingGameStyle gameStyle) {
            this.paint = new Paint();
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(10);
            this.paint.setColor(gameStyle.getCursorColor());
        }

        /**
         * Moves the cursor according to its velocity. If the cursor's position is less than 0 or more
         * than the width of the meter, turn around.
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
         * Returns the coordinates of the cursor. The size of returned result is 4.
         *
         * @return [startX, starY, endX, endY].
         */
        int[] getCoordinates() {
            int currX = cursorPosition + widthMargins;

            double margins =
                    0.2; // The ratio of how long the "extensions" are compared to the height of th ebox.
            long yMargins = Math.round(height * margins);
            int topY = Math.toIntExact(Math.round(heightMargins - yMargins));
            int bottomY = Math.toIntExact(Math.round(heightMargins + height + yMargins));

            return new int[]{currX, topY, currX, bottomY};
        }
    }
}
