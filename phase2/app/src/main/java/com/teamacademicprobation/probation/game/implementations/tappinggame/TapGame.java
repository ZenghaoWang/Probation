package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.content.Context;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.BackGround;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.BombMole;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.KingMole;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.Mole;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.NormalMole;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.NormalMoleCounter;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.TapScoreBoard;
import com.teamacademicprobation.probation.player.PlayerManager;
import com.teamacademicprobation.probation.player.PlayerStatsAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Tapping game where the player tries to tap the target object and avoid tapping non-target.
 */
class TapGame implements Drawable {
    /**
     * The gameID of this game.
     */
    private static final String GAMEID = "TapGame";
    /**
     * Represents if the game is completed.
     */
    private boolean gameComplete;

    private Mole currMole;
    private BackGround backGround;
    private TapScoreBoard scoreBoard;
    private Random r = new Random();
    private NormalMoleCounter targetCounter;
    private int x;
    private int y;
    private String currPlayerID;
    private Context context;
    private PlayerStatsAccess playerAccess;

    /**
     * Constructor for the Tap game.
     */
    TapGame(Context context, int x, int y, String currPlayerID) {
        this.x = x;
        this.y = y;
        this.context = context;
        this.gameComplete = false;
        this.scoreBoard = new TapScoreBoard(x, y);
        this.targetCounter = new NormalMoleCounter(x, y);
        this.backGround = new BackGround(context, x, y);
        this.currPlayerID = currPlayerID;
        this.playerAccess = new PlayerManager();
    }

    /**
     * Return the status of the game (Completed or not Completed).
     */
    boolean getGameComplete() {
        return this.gameComplete;
    }

    /**
     * Return the score of the game.
     *
     * @return score of the game.
     */
    int getScore() {
        return this.scoreBoard.getScore();
    }


    /**
     * Updates the TapGame.
     */
    void update() {
        double c = Math.random(); // Randomly choose if a Target or Non-target should be created.
        if (c > 0.7) {
            createBombMole();
        } else if (0.1 < c && c <= 0.7) {
            createNormalMole();
            targetCounter.addCount();
        } else if (c <= 0.1) {
            createKingMole();
        }
        // If target objects reach the limit. The game is completed
        if (targetCounter.getNormalMoleCount() == this.targetCounter.getNormalMoleLimit()) {
            this.setCompleted();
        }
    }

    /**
     * Sets the current Mole to a KingMole.
     */
    private void createKingMole() {
        this.currMole =
                new KingMole(
                        context,
                        r.nextInt(((this.x - 200) - 150) + 150),
                        r.nextInt((this.y - 150) - (this.y / 2 + 50)) + this.y / 2);
    }

    /**
     * Sets the current Mole to a NormalMole.
     */
    private void createNormalMole() {
        currMole =
                new NormalMole(
                        context,
                        r.nextInt(((this.x - 200) - 150) + 150),
                        r.nextInt((this.y - 150) - (this.y / 2 + 50)) + this.y / 2);
    }

    /**
     * Sets the current Mole to a BombMole.
     */
    private void createBombMole() {
        this.currMole =
                new BombMole(
                        context,
                        r.nextInt(((this.x - 200) - 150) + 150),
                        r.nextInt((this.y - 150) - (this.y / 2 + 50)) + this.y / 2);
    }

    /**
     * Sets the game to completed and sends the statistics.
     */
    private void setCompleted() {
        this.gameComplete = true;
    }

    /**
     * Updates the score accordingly to what type of mole the user tapped.
     *
     * @param touchX the x-coordinate where the user touched.
     * @param touchY the x-coordinate where the user touched
     */
    void updateScore(double touchX, double touchY) {
        if (checkTouch(touchX, touchY, this.currMole) && this.currMole instanceof NormalMole) {
            this.scoreBoard.earnPoint();
            updatePlayerStats();
        } else if (checkTouch(touchX, touchY, this.currMole) && this.currMole instanceof KingMole) {
            this.scoreBoard.earnFivePoints();
            updatePlayerStats();
        } else if (checkTouch(touchX, touchY, this.currMole) && this.currMole instanceof BombMole)
            this.scoreBoard.losePoint();
        updatePlayerStats();
    }

    /**
     * Checks if the user tapped on a mole.
     *
     * @param touchX the x-coordinate where the user touched.
     * @param touchY the x-coordinate where the user touched.
     * @param mole   the mole that the user tapped.
     */
    private boolean checkTouch(double touchX, double touchY, Mole mole) {
        return mole.getX() < touchX
                && touchX < mole.getX() + mole.getSize()
                && mole.getY() < touchY
                && touchY < mole.getY() + mole.getSize();
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        drawers.addAll(backGround.getDrawers());
        drawers.addAll(currMole.getDrawers());
        drawers.addAll(scoreBoard.getDrawers());
        drawers.addAll(targetCounter.getDrawers());
        return drawers;
    }

    void updatePlayerStats() {
        this.playerAccess.updateStats(currPlayerID, GAMEID, "score", scoreBoard.getScore());
    }

    /**
     *
     */
    void endGame() {
        this.playerAccess.endGame(currPlayerID, true);
    }
}

