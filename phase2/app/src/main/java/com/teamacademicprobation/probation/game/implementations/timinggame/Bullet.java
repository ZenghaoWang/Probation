package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.graphics.Canvas;
import android.graphics.Paint;

class Bullet {

    private static final int RADIUS = 16;
    static final int FRAMES = 10;
    private int currFrame = 0;
    private int bulletVelocity;
    private int x;
    private int y;
    private Paint paint;


    Bullet(int screenHeight, TimingGameStyle gameStyle){
        this.y = Math.toIntExact(Math.round(screenHeight * 0.25));
        generatePaint(gameStyle);
    }

    void setBulletVelocity(int bulletVelocity) {
        this.bulletVelocity = bulletVelocity;
    }

    void setX(int x) {
        this.x = x;
    }


    private void generatePaint(TimingGameStyle gameStyle){
        this.paint = new Paint();
        this.paint.setColor(gameStyle.getBulletColor());
    }

    void draw(Canvas canvas){
        if(this.currFrame <= FRAMES) {
            canvas.drawCircle(x, y, RADIUS, paint);
            this.x += bulletVelocity;
            this.currFrame += 1;
        }
    }

    boolean contact(){
        return this.currFrame > FRAMES;
    }

}
