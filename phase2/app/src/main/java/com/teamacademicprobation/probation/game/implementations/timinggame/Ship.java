package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

class Ship {

    private Bitmap ship;
    private Bitmap explosion;
    private int x;
    private int y;
    private int size;

    private boolean destroyed;

    private Paint paint;



    Ship(int screenWidth, int screenHeight, double widthRatio){
        this(screenWidth, screenHeight, widthRatio, 128);
    }

    Ship(int screenWidth, int screenHeight, double widthRatio, int size){
        this.size = size;
        this.x = Math.toIntExact(Math.round(screenWidth * widthRatio) - size/2);
        this.y = Math.toIntExact(Math.round(screenHeight * 0.25) - size/2);
        this.destroyed = false;

        this.paint = new Paint();
        paint.setAntiAlias(false);
        paint.setFilterBitmap(false);

    }
    void setShip(Bitmap ship, int angle){
        this.ship = rotateAndScale(ship, angle);
    }


    private Bitmap rotateAndScale(Bitmap ship, int angle){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap scaled = Bitmap.createScaledBitmap(ship, size, size, false);
        return Bitmap.createBitmap(scaled, 0, 0, scaled.getWidth(), scaled.getHeight(), matrix, false);
    }

    void setExplosion(Bitmap explosion){ this.explosion = explosion;}

    void draw(Canvas canvas){
        if(!this.destroyed) {
            canvas.drawBitmap(ship, x, y, paint);
        }
        else{
            canvas.drawBitmap(explosion, x, y, paint);
        }
    }

    void setDestroyed(boolean destroyed){this.destroyed = destroyed;}

    boolean isDestroyed(){return this.destroyed;}

}
