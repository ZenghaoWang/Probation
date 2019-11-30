package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.content.Context;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.BackGround;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.Bird;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.BombMole;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.KingMole;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.Mole;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.NormalMole;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.NormalMoleCounter;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.TapScoreBoard;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel.TouchableObject;
import com.teamacademicprobation.probation.player.PlayerGameStatsAccess;
import com.teamacademicprobation.probation.player.PlayerManager;
import com.teamacademicprobation.probation.ui.ScoreScreenActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A Tapping game where the player tries to tap the target object and avoid tapping non-target.
 */
class TapGameModel implements Drawable {
    /**
     * The gameID of this game.
     */
    private static final String GAMEID = "TapGameModel";
    /**
     * Represents if the game is completed.
     */
    private final int moleSize = 250;
    private final int birdSize = 250;
    private boolean birdTouched;
    private boolean gameComplete;
    private Bird bird;
    private Mole currMole;
    private BackGround backGround;
    private TapScoreBoard scoreBoard;
    private Random r = new Random();
    private NormalMoleCounter normalMoleCounter;
    private int x;
    private int y;
    private String currPlayerID;
    private Context context;
    private PlayerGameStatsAccess playerAccess;
    private static final String CURR_MOLE_COUNT = "CURRENT_MOLE_COUNT";

    /**
     * Constructor for the Tap game.
     */
    TapGameModel(Context context, int x, int y, String currPlayerID) {
        this.x = x;
        this.y = y;
        this.context = context;
        this.gameComplete = false;
        this.scoreBoard = new TapScoreBoard(x, y);
        this.normalMoleCounter = new NormalMoleCounter(x, y);
        this.backGround = new BackGround(context, x, y);
        this.currPlayerID = currPlayerID;
        this.playerAccess = new PlayerManager();
        this.birdTouched = false;
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
     * Updates the TapGameModel.
     */
    void update() {
        randomCreateMole();
        randomCreateBird();
        if (this.bird != null && this.bird.getX() >= 0) {
            bird.move();
        } else {
            this.bird = null;
        }
        // If target objects reach the limit. The game is completed
        if (normalMoleCounter.getNormalMoleLeft() == 0) {
            this.setCompleted();
        }
    }

    private void randomCreateMole() {
        double c = Math.random();
        if (c > 0.7) {
            createBombMole();
        } else if (0.1 < c && c <= 0.7) {
            createNormalMole();
            normalMoleCounter.reduceOneMole();
        } else if (c <= 0.1) {
            createKingMole();
        }
    }

    private void randomCreateBird() {
        double c = Math.random();
        if (c >= 0.9 && this.bird == null) {
            this.bird = new Bird(context, this.x, 100, birdSize);
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
                        r.nextInt((this.y - 150) - (this.y / 2 + 50)) + this.y / 2, moleSize);
    }

    /**
     * Sets the current Mole to a NormalMole.
     */
    private void createNormalMole() {
        currMole =
                new NormalMole(
                        context,
                        r.nextInt(((this.x - 200) - 150) + 150),
                        r.nextInt((this.y - 150) - (this.y / 2 + 50)) + this.y / 2, moleSize);
    }

    /**
     * Sets the current Mole to a BombMole.
     */
    private void createBombMole() {
        this.currMole =
                new BombMole(
                        context,
                        r.nextInt(((this.x - 200) - 150) + 150),
                        r.nextInt((this.y - 150) - (this.y / 2 + 50)) + this.y / 2, moleSize);
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
    void updateOnTouch(double touchX, double touchY) {
        if (checkTouch(touchX, touchY, this.currMole) && this.currMole instanceof NormalMole) {
            this.scoreBoard.earnPoint();
            updatePlayerStats();
        } else if (checkTouch(touchX, touchY, this.currMole) && this.currMole instanceof KingMole) {
            this.scoreBoard.earnFivePoints();
            updatePlayerStats();
        } else if (checkTouch(touchX, touchY, this.currMole) && this.currMole instanceof BombMole) {
            this.scoreBoard.losePoint();
            updatePlayerStats();
        } else if (checkTouch(touchX, touchY, this.bird) && !this.birdTouched) {
            this.birdTouched = true;
            this.normalMoleCounter.addFiveMoles();
        }
    }


    /**
     * Checks if the user tapped on a mole.
     *
     * @param touchX          the x-coordinate where the user touched.
     * @param touchY          the x-coordinate where the user touched.
     * @param touchableObject the touchableObject that the user tapped.
     */
    private boolean checkTouch(double touchX, double touchY, TouchableObject touchableObject) {
        return (touchableObject != null) && (touchableObject.getX() < touchX
                && touchX < touchableObject.getX() + touchableObject.getSize()
                && touchableObject.getY() < touchY
                && touchY < touchableObject.getY() + touchableObject.getSize());
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        drawers.addAll(backGround.getDrawers());
        drawers.addAll(currMole.getDrawers());
        drawers.addAll(scoreBoard.getDrawers());
        drawers.addAll(normalMoleCounter.getDrawers());
        if (this.bird != null) {
            drawers.addAll(bird.getDrawers());
        }
        return drawers;
    }

    private void updatePlayerStats() {
        Map<String, Integer> statMap = new HashMap<>();
        statMap.put(ScoreScreenActivity.SCORE_KEY, scoreBoard.getScore());
        statMap.put(CURR_MOLE_COUNT, this.normalMoleCounter.getNormalMoleLeft());
        this.playerAccess.updateStats(currPlayerID, GAMEID, "score", scoreBoard.getScore());
    }


    @SuppressWarnings("ConstantConditions")
    private void loadMetaData(Map<String, Integer> statMap) {
        int score = (statMap.get(ScoreScreenActivity.SCORE_KEY) != null) ? statMap.get(ScoreScreenActivity.SCORE_KEY) : 0;
        this.scoreBoard.setScore(score);
    }
    /**
     *
     */
    void endGame() {
        this.playerAccess.endGame(currPlayerID, GAMEID,true);
    }
}
