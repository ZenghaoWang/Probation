package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

public class HealthBoostDrawer implements AndroidDrawer {

    private static final String MESSAGE = "+1 Health";
    private Bitmap increaseHealth;
    private int x;
    private int y;
    private Paint paint;

    public HealthBoostDrawer(Bitmap increaseHealth, int x, int y, Paint paint) {
        this.increaseHealth = increaseHealth;
        this.x = x;
        this.y = y;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(increaseHealth, x, y, null);
        canvas.drawText(MESSAGE, x, y - 90, paint);
    }
}
