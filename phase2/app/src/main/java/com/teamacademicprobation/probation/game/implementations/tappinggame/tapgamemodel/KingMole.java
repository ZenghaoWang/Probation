package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.teamacademicprobation.probation.R;

/**
 * A KingMole object that when the players tap, they earn five points. KingMole object extends Mole.
 */
public class KingMole extends Mole {
    /**
     * Initializes the BombMole Object.
     *
     * @param context The context to get resources.
     * @param x       The x coordinate of BombMole in pixels.
     * @param y       The y coordinate of BombMole in pixels.
     */
    public KingMole(Context context, int x, int y) {
        super(x, y);
        this.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.king_mole));
    }
}
