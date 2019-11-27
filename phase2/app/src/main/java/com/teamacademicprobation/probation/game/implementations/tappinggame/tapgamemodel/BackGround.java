package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamedrawers.BackGroundDrawer;

import java.util.ArrayList;
import java.util.List;

public class BackGround implements Drawable {
    private Bitmap backGroundBitmap;
    private int x;
    private int y;
    private Paint paint;

    public BackGround(Context context, int x, int y) {
        this.backGroundBitmap =
                BitmapFactory.decodeResource(context.getResources(), R.drawable.tap_background);
        this.backGroundBitmap = Bitmap.createScaledBitmap(this.backGroundBitmap, x, y, true);
        this.x = 0;
        this.y = 0;
        this.paint = new Paint();
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        AndroidDrawer backgroundDrawer;
        backgroundDrawer = new BackGroundDrawer(backGroundBitmap, x, y, paint);
        drawers.add(backgroundDrawer);
        return drawers;
    }
}
