package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

class HitBox {

    private int boxWidth;
    private int boxHeight;
    private int targetWidth;
    private double targetStart; // The distance from the most left of the box / width of the box.

    private int boxWidthMargins;
    private int boxHeightMargins;

    private Paint targetPaint;
    private Paint boxPaint;


    public HitBox(int screenWidth, int screenHeight) {
        this.boxWidth = Math.toIntExact(Math.round(screenWidth * 0.8));
        this.boxHeight = Math.toIntExact(Math.round(screenHeight * 0.06));

        this.targetWidth = Math.toIntExact(Math.round(this.boxWidth * 0.1));
        Random rand = new Random();
        this.targetStart = rand.nextInt(90) * 0.01;

        this.boxHeightMargins = Math.toIntExact(Math.round((screenHeight - this.boxHeight) / 2));
        this.boxWidthMargins = Math.toIntExact(Math.round((screenWidth - this.boxWidth)/2));
        generateBoxPaint();
        generateTargetPaint();

    }

    private void generateTargetPaint() {
        this.targetPaint = new Paint();
        this.targetPaint.setStrokeWidth(3);
        this.targetPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.targetPaint.setColor(TimingGame.getGameColour());

    }

    private void generateBoxPaint() {
        this.boxPaint = new Paint();
        this.boxPaint.setStrokeWidth(3);
        this.boxPaint.setStyle(Paint.Style.STROKE);
        this.boxPaint.setColor(TimingGame.getGameColour());

    }

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

    public void draw(Canvas canvas) {
        drawBox(canvas);
        drawTargetBox(canvas);
    }

    private void drawTargetBox(Canvas canvas) {

        int left = Math.toIntExact(Math.round(boxWidthMargins + (targetStart*boxWidth)));
        int top = boxHeightMargins;
        int right = left + targetWidth;
        int bottom = top + boxHeight;

        Rect targetBox = new Rect(left, top, right, bottom);

        canvas.drawRect(targetBox, this.targetPaint);
    }

    private void drawBox(Canvas canvas) {
        Rect box = new Rect(boxWidthMargins, boxHeightMargins,
                boxWidthMargins+boxWidth, boxHeightMargins+boxHeight);

        canvas.drawRect(box, this.boxPaint);

    }

    public int getTargetCenter() {

        int left = Math.toIntExact(Math.round(boxWidthMargins + (targetStart*boxWidth)));
        int top = boxHeightMargins;
        int right = left + targetWidth;
        int bottom = top + boxHeight;

        Rect targetBox = new Rect(left, top, right, bottom);

        System.out.println(targetBox.centerX());
        return targetBox.centerX();

    }
}
