package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class DamageBoostDrawer implements AndroidDrawer {

    private Bitmap increaseDamage;
    private static final String MESSAGE = "+1 Damage";
    private int x;
    private int y;
    private Paint paint;


    public DamageBoostDrawer(Bitmap increaseDamage, int x, int y, Paint paint) {
        this.increaseDamage = increaseDamage;
        this.x = x;
        this.y = y;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(increaseDamage, x, y, null);
        canvas.drawText(MESSAGE, x-20, y-90, paint);
    }
}
