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
 * A model for the Tapping Game. Contains the backend logic of the Tapping game. Implement drawable.
 */
class TapGameModel implements Drawable {
    /**
     * The gameID of this game.
     */
    private static final String GAMEID = "TapGameModel";
    private String currPlayerID;
    private Context context;
    private PlayerGameStatsAccess playerAccess;
    /**
     * Represents if the game is completed.
     */
    private boolean gameComplete;
    /**
     * boolean to check if the bird has been touched in the game.
     */
    private boolean birdTouched;

    private Bird bird;
    private Mole currMole;
    private BackGround backGround;
    private TapScoreBoard scoreBoard;
    private NormalMoleCounter normalMoleCounter;

    private int screenWidth;
    private int screenHeight;
    private Random r = new Random();

    private static final String CURR_MOLE_COUNT = "CURRENT_MOLE_COUNT";
    private static final String BIRD_TOUCHED = "BIRD_TOUCHED";

    /**
     * Constructor for the TapGame Backend.
     */
    TapGameModel(Context context, int screenWidth, int screenHeight, String currPlayerID) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.context = context;
        this.gameComplete = false;
        this.scoreBoard = new TapScoreBoard(screenWidth, screenHeight);
        this.normalMoleCounter = new NormalMoleCounter(screenWidth, screenHeight);
        this.backGround = new BackGround(context, screenWidth, screenHeight);
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
     * Return the current score of the game.
     *
     * @return current score of the game.
     */
    int getScore() {
        return this.scoreBoard.getScore();
    }

    /**
     * Updates the TapGame.
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

    /**
     * Chooses a random mole type to be created and creates the selected mole.
     */
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

    /**
     * Randomly creates a bird object.
     */
    private void randomCreateBird() {
        double c = Math.random();
        if (c >= 0.9 && this.bird == null) {
            this.bird = new Bird(context, this.screenWidth, 100);
        }
    }

    /**
     * Creates and sets the current Mole to a KingMole.
     */
    private void createKingMole() {
        this.currMole =
                new KingMole(
                        context,
                        r.nextInt(((this.screenWidth - 200) - 150) + 150),
                        r.nextInt((this.screenHeight - 150) - (this.screenHeight / 2 + 50)) + this.screenHeight / 2);
    }

    /**
     * Creates and sets the current Mole to a NormalMole.
     */
    private void createNormalMole() {
        currMole =
                new NormalMole(
                        context,
                        r.nextInt(((this.screenWidth - 200) - 150) + 150),
                        r.nextInt((this.screenHeight - 150) - (this.screenHeight / 2 + 50)) + this.screenHeight / 2);
    }

    /**
     * Creates and sets the current Mole to a BombMole.
     */
    private void createBombMole() {
        this.currMole =
                new BombMole(
                        context,
                        r.nextInt(((this.screenWidth - 200) - 150) + 150),
                        r.nextInt((this.screenHeight - 150) - (this.screenHeight / 2 + 50)) + this.screenHeight / 2);
    }

    /**
     * Sets the game to completed.
     */
    private void setCompleted() {
        this.gameComplete = true;
    }

    /**
     * Updates the game accordingly to the user's touch input.
     *
     * @param touchX the x-coordinate where the user touched.
     * @param touchY the y-coordinate where the user touched
     */
    void updateOnTouch(double touchX, double touchY) {
        if (checkTouch(touchX, touchY, this.currMole) && this.currMole instanceof NormalMole) {
            this.scoreBoard.earnPoint();
        } else if (checkTouch(touchX, touchY, this.currMole) && this.currMole instanceof KingMole) {
            this.scoreBoard.earnFivePoints();
        } else if (checkTouch(touchX, touchY, this.currMole) && this.currMole instanceof BombMole) {
            this.scoreBoard.losePoint();
        } else if (checkTouch(touchX, touchY, this.bird) && !this.birdTouched) {
            this.birdTouched = true;
            this.normalMoleCounter.addFiveMoles();
        }
        updatePlayerStats();
    }


    /**
     * Checks if the user tapped on a touchableObject.
     *
     * @param touchX          the x-coordinate where the user touched.
     * @param touchY          the y-coordinate where the user touched.
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


    /**
     * Ends the game and sends the statistics.
     */
    void endGame() {
        this.playerAccess.endGame(currPlayerID, GAMEID,true);
    }

    private void updatePlayerStats() {
        Map<String, Integer> statMap = new HashMap<>();
        statMap.put(ScoreScreenActivity.SCORE_KEY, this.scoreBoard.getScore());
        statMap.put(CURR_MOLE_COUNT, this.normalMoleCounter.getNormalMoleLeft());
        statMap.put(BIRD_TOUCHED, this.birdTouched ? 1 : 0);
        this.playerAccess.updateStats(currPlayerID, GAMEID, statMap);
    }


    @SuppressWarnings("ConstantConditions")
    private void loadMetaData(Map<String, Integer> statMap) {
        int score = (statMap.get(ScoreScreenActivity.SCORE_KEY) != null) ? statMap.get(ScoreScreenActivity.SCORE_KEY) : 0;
        int moleLeft = (statMap.get(CURR_MOLE_COUNT) != null) ? statMap.get(CURR_MOLE_COUNT) : 30;
        int birdTouch = (statMap.get(BIRD_TOUCHED) != null) ? statMap.get(BIRD_TOUCHED) : 0;
        this.scoreBoard.setScore(score);
        this.normalMoleCounter.setLeft(moleLeft);
        this.birdTouched = (birdTouch == 1);
    }

    void loadPlayerData() {
        if (currPlayerID != null && this.playerAccess.isBeingPlayed(currPlayerID, GAMEID)) {
            Map<String, Integer> statMap = this.playerAccess.getCurrStats(currPlayerID, GAMEID);
            loadMetaData(statMap);
            System.out.println(statMap);
        }
    }
}
