package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.teamacademicprobation.probation.R;

/**
 * A KingMole object that when the players tap, they earn five points. KingMole object extends
 * Mole.
 */
public class KingMole extends Mole {
    /**
     * Initializes the paint and x and y coordinates of the BombMole Object.
     *
     * @param context The context to get resources.
     * @param x       The x coordinate of Mole in pixels.
     * @param y       The y coordinate of Mole in pixels.
     */
    public KingMole(Context context, int x, int y) {
        super(x, y);
        this.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.king_mole));
    }
}
