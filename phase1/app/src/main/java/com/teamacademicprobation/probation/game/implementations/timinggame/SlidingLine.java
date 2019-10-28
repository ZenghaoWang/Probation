package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.graphics.Canvas;
import android.graphics.Paint;

class SlidingLine {
    private int linePosition; // In relation to the width of the hitBox, not to the whole screen.
    private int lineSpeed;
    private HitBox hitBox;
    private Paint paint;

    public SlidingLine(int screenWidth, int screenHeight, HitBox hitBox) {
        this.hitBox = hitBox;
        this.linePosition = 0;
        this.lineSpeed = this.hitBox.getWidth() / 40;
        generatePaint();

    }

    private void generatePaint() {
        this.paint = new Paint();
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(7);
        this.paint.setColor(TimingGame.getGameColour());
    }

    public void update(){
        this.linePosition += this.lineSpeed;
        if(this.linePosition <= 0 || this.linePosition >= hitBox.getWidth()){
            this.linePosition = Math.max(0, Math.min(this.linePosition, hitBox.getWidth()));
            this.lineSpeed *= -1;
        }
    }

    public void draw(Canvas canvas){
        int currX = linePosition + hitBox.getWidthMargins();

        double lineMargins = 0.1;
        long yMargins = Math.round(hitBox.getHeight()* lineMargins);
        int topY = Math.toIntExact(Math.round(hitBox.getHeightMargins() - yMargins));
        int bottomY = Math.toIntExact(Math.round(hitBox.getHeightMargins() + hitBox.getHeight() + yMargins));

        canvas.drawLine(currX, topY, currX, bottomY, paint);

    }


    public int getActualPosition() {
        return this.linePosition + hitBox.getWidthMargins();
    }
}
