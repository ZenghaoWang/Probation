package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

public class PowerUpLineDrawer implements AndroidDrawer {

    private int x;
    private int y;
    private int length;
    private Paint paint;

    public PowerUpLineDrawer(int x, int y, int length, Paint paint) {
        this.x = x;
        this.y = y;
        this.length = length;
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(x, y, x, y + length, paint);
    }
}
