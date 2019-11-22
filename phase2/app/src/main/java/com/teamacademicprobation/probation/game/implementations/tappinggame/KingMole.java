package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.teamacademicprobation.probation.R;

class KingMole extends Mole {
    KingMole(Context context, int x, int y) {
        super(x, y);
        this.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.king_mole));
    }
}
