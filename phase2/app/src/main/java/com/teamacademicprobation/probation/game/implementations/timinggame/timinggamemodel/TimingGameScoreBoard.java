package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

import com.teamacademicprobation.probation.game.ScoreBoard;
import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.timinggame.drawers.ScoreBoardDrawer;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of ScoreBoard for timing game.
 */
class TimingGameScoreBoard extends ScoreBoard {

    /**
     * Initializes a new TiminGameScoreBoard.
     *
     * @param screenWidth  The width of the screen, in pixels.
     * @param screenHeight The height of the screen, in pixels.
     */
    TimingGameScoreBoard(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight);
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        AndroidDrawer drawer =
                new ScoreBoardDrawer(super.getX(), super.getY(), this.getScore(), super.getPaint());
        drawers.add(drawer);
        return drawers;
    }
}
