package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel;

import android.graphics.Color;

import com.teamacademicprobation.probation.game.ScoreBoard;
import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamedrawers.TapScoreBoardDrawer;

import java.util.ArrayList;
import java.util.List;

/**
 * A scoreboard for the TapGame.
 */
public class TapScoreBoard extends ScoreBoard implements Drawable {
    /**
     * Initializes the Tap Game scoreboard to fit on the top right of the screen.
     *
     * @param screenWidth  The width of the screen, in pixels.
     * @param screenHeight The height of the screen, in pixels.
     */
    public TapScoreBoard(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
        this.getPaint().setColor(Color.BLACK);
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        AndroidDrawer drawer = new TapScoreBoardDrawer(super.getX(), super.getY(), this.getScore(), super.getPaint());
        drawers.add(drawer);
        return drawers;
    }
}
