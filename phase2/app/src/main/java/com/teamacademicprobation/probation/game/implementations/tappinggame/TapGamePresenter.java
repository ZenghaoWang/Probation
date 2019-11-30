package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.content.res.Resources;
import android.graphics.Canvas;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

import java.util.List;

/**
 * A presenter for the TapGameModel.
 */
class TapGamePresenter {
    private TapGameModel tapGameModel;
    private TapGameView tapGameView;

    TapGamePresenter(TapGameView tapGameView, String playerID) {
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        this.tapGameView = tapGameView;
        this.tapGameModel = new TapGameModel(tapGameView.getContext(), screenWidth, screenHeight, playerID);
        this.tapGameModel.loadPlayerData();
    }

    /**
     * Draws the game onto the canvas.
     *
     * @param canvas The canvas onto which to draw on.
     */
    void draw(Canvas canvas) {
        List<AndroidDrawer> drawers = this.tapGameModel.getDrawers();
        for (AndroidDrawer drawer : drawers) {
            drawer.draw(canvas);
        }
    }

    /**
     * Updates tap game, and goes to the score screen if completed.
     */
    void update() {
        this.tapGameModel.update();
        if (this.tapGameModel.getGameComplete()) {
            this.tapGameModel.endGame();
            String score = "" + this.tapGameModel.getScore();
            this.tapGameView.goToScoreScreen(score);
        }
    }

    /**
     * Updates the tap game score once a tap is detected.
     */
    void updateOnTouch(double touchX, double touchY) {
        this.tapGameModel.updateOnTouch(touchX, touchY);
    }
}
