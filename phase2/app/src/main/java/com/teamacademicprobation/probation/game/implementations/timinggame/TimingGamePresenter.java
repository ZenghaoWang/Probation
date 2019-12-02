package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;

import java.util.List;

/**
 * A presenter for the timing game.
 */
class TimingGamePresenter {

    private TimingGameModel timingGameModel;
    private TimingGameViewInterface gameView;

    /**
     * Initializes a new TimingGamePresenter.
     *
     * @param gameView The view of the game.
     * @param playerID The playerID of the player currently playing.
     */
    TimingGamePresenter(TimingGameView gameView, String playerID) {

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        this.gameView = gameView;
        this.timingGameModel = new TimingGameModel(screenWidth, screenHeight, playerID);
        this.timingGameModel.buildShips(gameView.getContext().getResources());
        this.timingGameModel.loadPlayerData();
        this.timingGameModel.buildPowerUpSelect(gameView.getContext().getResources());
    }

    /**
     * Updates timing game, and goes to the score screen if completed.
     */
    void update() {
        this.timingGameModel.update();
        if (this.timingGameModel.isCompleted()) {
            String score = "" + this.timingGameModel.getScore();
            this.gameView.goToScoreScreen(score, this.timingGameModel.getPlayerID(), TimingGameModel.GAMEID);
        }
    }

    /**
     * Draws the game onto the canvas.
     *
     * @param canvas The canvas onto which to draw on.
     */
    void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        List<AndroidDrawer> drawers = this.timingGameModel.getDrawers();
        for (AndroidDrawer drawer : drawers) {
            drawer.draw(canvas);
        }
    }

    /**
     * Updates the timing game once a tap is detected.
     *
     * @param touchX the x coordinate of where the tap.
     */
    void onTouch(double touchX) {
        this.timingGameModel.onTouch(touchX);
    }

}
