package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

import java.util.List;

/**
 * A presenter for the TapGame.
 */
class TapGamePresenter {
    private TapGame tapGame;
    private TapGameView tapGameView;

    TapGamePresenter(TapGameView tapGameView, String playerID) {
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        this.tapGameView = tapGameView;
        this.tapGame = new TapGame(tapGameView.getContext(), screenWidth, screenHeight, playerID);
    }


    /**
     * Draws the game onto the canvas.
     *
     * @param canvas The canvas onto which to draw on.
     */
    void draw(Canvas canvas) {
        List<AndroidDrawer> drawers = this.tapGame.getDrawers();
        for (AndroidDrawer drawer : drawers) {
            drawer.draw(canvas);
        }
    }

    /**
     * Updates tap game, and goes to the score screen if completed.
     */
    void update() {
        this.tapGame.update();
        if (this.tapGame.getGameComplete()) {
            String score = "" + this.tapGame.getScore();
            this.tapGameView.goToScoreScreen(score);
        }
    }

    /**
     * Updates the tap game score once a tap is detected.
     */
    void updateScore(double touchX, double touchY) {
        this.tapGame.updateScore(touchX, touchY);
    }
}
