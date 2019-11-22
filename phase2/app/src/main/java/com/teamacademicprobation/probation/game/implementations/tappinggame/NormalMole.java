package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.teamacademicprobation.probation.R;

/**
 * A Target object that when players tap, they earn a point. NormalMole extends Mole.
 */
class NormalMole extends Mole {

    /**
     * Constructor for NormalMole,
     *
     * @param x x-coordinate for NormalMole
     * @param y y-coordinate for NormalMole
     */
    NormalMole(Context context, int x, int y) {
        super(x, y);
        this.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.mole));
    }
}
