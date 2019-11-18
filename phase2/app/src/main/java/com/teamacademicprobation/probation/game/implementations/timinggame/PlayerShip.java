package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

class PlayerShip extends Ship{

    static final double WIDTHRATIO = 0.15;

    PlayerShip(int screenWidth, int screenHeight){
        super(screenWidth, screenHeight, WIDTHRATIO);
    }

    public PlayerShip(int screenWidth, int screenHeight, int size) {
        super(screenWidth, screenHeight, WIDTHRATIO, size);
    }
}
