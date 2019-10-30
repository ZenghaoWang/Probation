package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

public class Box {

    private int boxWidth;
    private int boxHeight;
    private int boxWidthMargins;
    private int boxHeightMargins;

    private TargetBox targetBox;
    private SlidingLine slidingLine;

    private Paint boxPaint;

    Box(int screenWidth, int screenHeight){
        this.boxWidth = Math.toIntExact(Math.round(screenWidth * 0.8));
        this.boxHeight = Math.toIntExact(Math.round(screenHeight * 0.06));
        this.boxHeightMargins = Math.toIntExact(Math.round((screenHeight - this.boxHeight) / 2));
        this.boxWidthMargins = Math.toIntExact(Math.round((screenWidth - this.boxWidth) / 2));
        generatePaint();
        this.targetBox = new TargetBox();
        this.slidingLine = new SlidingLine();
    }

    private void generatePaint() {
        this.boxPaint = new Paint();
        this.boxPaint.setStrokeWidth(3);
        this.boxPaint.setStyle(Paint.Style.STROKE);
        this.boxPaint.setColor(TimingGame.getGameColor());
    }

    public void draw(Canvas canvas){
        drawBox(canvas);
        targetBox.draw(canvas);
        slidingLine.draw(canvas);
    }

    public void update(){
        this.slidingLine.update();
    }

    private void drawBox(Canvas canvas) {
        Rect box = getBoxRect();

        canvas.drawRect(box, this.boxPaint);
    }

    private Rect getBoxRect() {
        return new Rect(
                boxWidthMargins,
                boxHeightMargins,
                boxWidthMargins + boxWidth,
                boxHeightMargins + boxHeight);
    }

    public int getLineDistanceFromTarget(){
        return slidingLine.linePosition - targetBox.getTargetCenter();
    }

    private class TargetBox {
        private double targetBoxStart;
        private int targetBoxWidth;
        private Paint targetBoxPaint;

        TargetBox(){
            this.targetBoxWidth = Math.toIntExact(Math.round(boxWidth * 0.1));
            Random rand = new Random();
            this.targetBoxStart = rand.nextInt(90) * 0.01;
            generateTargetPaint();
        }

        private void generateTargetPaint() {
            this.targetBoxPaint = new Paint();
            this.targetBoxPaint.setStrokeWidth(3);
            this.targetBoxPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            this.targetBoxPaint.setColor(TimingGame.getGameColor());
        }

        private Rect getTargetBoxRect() {
            int left = Math.toIntExact(Math.round(boxWidthMargins + (targetBoxStart * boxWidth)));
            int top = boxHeightMargins;
            int right = left + targetBoxWidth;
            int bottom = top + boxHeight;

            return new Rect(left, top, right, bottom);
        }

        private int getTargetCenter(){
            Rect targetBox = getTargetBoxRect();
            return targetBox.centerX() - boxWidthMargins;
        }

        private void draw(Canvas canvas){
            Rect targetBox = getTargetBoxRect();
            canvas.drawRect(targetBox, targetBoxPaint);
        }

    }

    private class SlidingLine{
        /** The X position of this line in relation to the hitBox. */
        private int linePosition;
        /** The velocity of the line. */
        private int lineVelocity;
        /** The paint object that describes the style of the line. */
        private Paint paint;

        private SlidingLine() {
            this.linePosition = 0;
            this.lineVelocity = boxWidth / 25; // This can be adjusted for difficulty.
            generatePaint();
        }

        /** Initializes the paint style of this line. */
        private void generatePaint() {
            this.paint = new Paint();
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(7);
            this.paint.setColor(TimingGame.getGameColor());
        }

        private void update() {
            this.linePosition += this.lineVelocity;
            if (this.linePosition <= 0 || this.linePosition >= boxWidth) {
                // This code makes it so that the line matches up with the box.
                this.linePosition = Math.max(0, Math.min(this.linePosition, boxWidth));
                this.lineVelocity *= -1;
            }
        }

        /**
         * Draws the line.
         *
         * @param canvas The canvas on which to draw on.
         */
        private void draw(Canvas canvas) {
            int currX = linePosition + boxWidthMargins;

            double lineMargins = 0.1; // The ratio of how long the "extensions" are.
            long yMargins = Math.round(boxHeight * lineMargins);
            int topY = Math.toIntExact(Math.round(boxHeightMargins - yMargins));
            int bottomY =
                    Math.toIntExact(Math.round(boxHeightMargins + boxHeight + yMargins));

            canvas.drawLine(currX, topY, currX, bottomY, paint);
        }
    }

}
