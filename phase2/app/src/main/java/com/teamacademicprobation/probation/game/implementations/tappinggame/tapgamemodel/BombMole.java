package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.teamacademicprobation.probation.R;

/**
 * A BombMole object that when the players tap, they lose a point. BombMole object extends Mole.
 */
public class BombMole extends Mole {

    /**
     * Initializes the paint and x and y coordinates of the BombMole Object.
     *
     * @param context The context to get resources.
     * @param x       The x coordinate of Mole in pixels.
     * @param y       The y coordinate of Mole in pixels.
     */
    public BombMole(Context context, int x, int y, int size) {
        super(x, y, size);
        this.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb_mole));
  }
}
