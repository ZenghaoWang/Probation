package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.teamacademicprobation.probation.R;

/**
 * A NormalMole object that when players tap, they earn a point. NormalMole extends Mole.
 */
public class NormalMole extends Mole {

    /**
     * Constructor for NormalMole,
     *
     * @param context context to get the resources.
     * @param x       x-coordinate for NormalMole
     * @param y       y-coordinate for NormalMole
     */
    public NormalMole(Context context, int x, int y) {
        super(x, y);
        this.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.mole));
    }
}
