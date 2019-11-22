package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.teamacademicprobation.probation.R;

/**
 * A Non-Target object that when players tap, they lose a point. Non-Target object extends
 * Mole.
 */
class BombMole extends Mole {
    /**
     * Initializes the paint and x and y coordinates of the non-target Object.
     *
     * @param x The x coordinate of Mole in pixels.
     * @param y The y coordinate of Mole in pixels.
     */

    BombMole(Context context, int x, int y) {
        super(x, y);
        this.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb_mole));
    }

}
