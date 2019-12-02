package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.content.res.Resources;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel.PowerUpSelect;
import com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel.TimingGame;
import com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel.TimingGameStyle;
import com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel.TimingGameStyles;
import com.teamacademicprobation.probation.player.PlayerGameStatsAccess;
import com.teamacademicprobation.probation.player.PlayerManager;
import com.teamacademicprobation.probation.ui.ScoreScreenActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A model for the timing game. This class was created to prevent the timing game class
 * from over-bloating due to the addition of power ups.
 */

public class TimingGameModel implements Drawable, TimingGameListener {

    /**
     * The number of stages to be completed.
     */
    private static final int STAGES = 10;
    /**
     * The ID of this game.
     */
    static final String GAMEID = "Timing Game";
    private TimingGame timingGame;
    private PowerUpSelect powerUpSelect;
    /**
     * The current stage the game is in.
     */
    private int currStage = 1;
    /**
     * True if the stage is completed. This boolean is used to decide between the game screen
     * and the power up screen.
     */
    private boolean stageCompleted;
    /**
     * An object that allows the game to save data.
     */
    private PlayerGameStatsAccess playerAccess;
    /**
     * The player ID of the player currently playing this game.
     */
    private String currPlayerID;

    private static final String CURR_STAGE = "CURR_STAGE";
    private static final String CURR_LEVEL = "CURRENT_LEVEL";
    private static final String PLAYER_CURR_HEALTH = "PLAYER_CURRENT_HEALTH";
    private static final String PLAYER_CURR_DAMAGE = "PLAYER_CURRENT_DAMAGE";
    private static final String PLAYER_MAX_HEALTH = "PLAYER_MAX_HEALTH";
    private static final String ENEMY_CURR_HEALTH = "ENEMY_CURRENT_HEALTH";

    /**
     * Initializes a new timing game model.
     * @param screenWidth The width of the screen, in pixels.
     * @param screenHeight The height of the screen, in pixels.
     * @param playerID The ID of the player playing this game.
     */
    TimingGameModel(int screenWidth, int screenHeight, String playerID) {
        this.currPlayerID = playerID;
        this.playerAccess = new PlayerManager();
        TimingGameStyle gameStyle = generateGameStyle();
        this.timingGame = new TimingGame(screenWidth, screenHeight, gameStyle, this);
        this.powerUpSelect =
                new PowerUpSelect(
                        screenWidth,
                        screenHeight, gameStyle);

    }

    /**
     * Generates a gameStyle that depending on the playerID.
     * @return TimingGameStyle.
     */
    private TimingGameStyle generateGameStyle() {
        if (Pattern.matches("^[a-iA-i].*$", currPlayerID)) {
            return new TimingGameStyle(TimingGameStyles.STYLE1);
        } else if (Pattern.matches("^[j-sJ-s].*$", currPlayerID)) {
            return new TimingGameStyle(TimingGameStyles.STYLE2);
        } else {
            return new TimingGameStyle(TimingGameStyles.STYLE3);
        }

    }

    /**
     * Loads the player data into timing game.
     */
    void loadPlayerData() {
        if (currPlayerID != null && this.playerAccess.isBeingPlayed(currPlayerID, GAMEID)) {
            Map<String, Integer> statMap = this.playerAccess.getCurrStats(currPlayerID, GAMEID);
            loadMetaData(statMap);
            loadPlayerShipData(statMap);
            loadEnemyShipData(statMap);
            System.out.println(statMap);
        }

    }

    /**
     * Loads the stage, score, and current level.
     * @param statMap
     */
    @SuppressWarnings("ConstantConditions")
    private void loadMetaData(Map<String, Integer> statMap) {
        // These statements ensure that null exceptions are avoided.
        this.currStage = (statMap.get(CURR_STAGE) != null) ? statMap.get(CURR_STAGE) : 1;
        this.timingGame.setLevel((statMap.get(CURR_LEVEL) != null) ? statMap.get(CURR_LEVEL) : 1);
        int score = (statMap.get(ScoreScreenActivity.SCORE_KEY) != null) ? statMap.get(ScoreScreenActivity.SCORE_KEY) : 0;
        this.timingGame.setScore(score);
    }

    /**
     * Loads the recent enemy, and rebuilds them.
     * @param statMap
     */
    @SuppressWarnings("ConstantConditions")
    private void loadEnemyShipData(Map<String, Integer> statMap) {
        this.timingGame.buildEnemyShip();
        int enemyHealth = (statMap.get(ENEMY_CURR_HEALTH) != null) ? statMap.get(ENEMY_CURR_HEALTH) : 1;
        this.timingGame.setEnemyShipHealth(enemyHealth);
    }

    /**
     * Loads the player ship data.
     * @param statMap
     */
    @SuppressWarnings("ConstantConditions")
    private void loadPlayerShipData(Map<String, Integer> statMap) {
        int playerMaxHealth = (statMap.get(PLAYER_MAX_HEALTH) != null) ? statMap.get(PLAYER_MAX_HEALTH) : 1;
        int playerHealth = (statMap.get(PLAYER_CURR_HEALTH) != null) ? statMap.get(PLAYER_CURR_HEALTH) : 1;
        int playerDamage = (statMap.get(PLAYER_CURR_DAMAGE) != null) ? statMap.get(PLAYER_CURR_DAMAGE) : 1;
        this.timingGame.setPlayerShip(playerMaxHealth, playerHealth, playerDamage);
    }

    /**
     * Updates the timing game.
     */
    void update() {
        timingGame.update();
    }

    @Override
    public List<AndroidDrawer> getDrawers() {

        return this.stageCompleted ? powerUpSelect.getDrawers() : timingGame.getDrawers();
    }

    /**
     * Builds the ships in the timing game.
     * @param resources
     */
    void buildShips(Resources resources) {
        timingGame.buildShips(resources);
    }

    /**
     * Builds the power up selection screen.
     * @param resources
     */
    void buildPowerUpSelect(Resources resources) {
        this.powerUpSelect.buildPowerUpImages(resources);
    }

    /**
     * Returns true if the game has been completed. A game is completed once all stages are cleared,
     * or once the player is defeated.
     * @return True if complete.
     */
    boolean isCompleted() {
        return currStage > STAGES || this.timingGame.playerDestroyed();
    }

    /**
     * Returns the score of the game.
     * @return
     */
    int getScore() {
        return timingGame.getScore();
    }

    /**
     * Returns the playerID that is playing the game
     * @return
     */
    String getPlayerID() {
        return this.currPlayerID;
    }

    /**
     * Updates the game accordingly to the touch.
     * @param touchX The x coordinate of the tap. Used for the power up selection screen.
     */
    void onTouch(double touchX) {
        if (this.stageCompleted && !isCompleted()) {
            powerUpSelect.onTouch(touchX);
            timingGame.upgradePlayer(powerUpSelect.getSelection());
            this.stageCompleted = false;
        } else {
            timingGame.onTouch();
        }
    }

    /**
     * Updates the player statistics.
     */
    private void updatePlayerStats() {
        Map<String, Integer> statMap = new HashMap<>();
        statMap.put(ScoreScreenActivity.SCORE_KEY, timingGame.getScore());
        statMap.put(CURR_STAGE, this.currStage);
        statMap.put(CURR_LEVEL, timingGame.getLevel());
        statMap.put(PLAYER_CURR_HEALTH, timingGame.getPlayerHealth());
        statMap.put(PLAYER_MAX_HEALTH, timingGame.getPlayerMaxHealth());
        statMap.put(PLAYER_CURR_DAMAGE, timingGame.getPlayerDamage());
        statMap.put(ENEMY_CURR_HEALTH, timingGame.getEnemyHealth());

        this.playerAccess.updateStats(currPlayerID, GAMEID, statMap);
    }


    @Override
    public void notifyChange() {
        this.updatePlayerStats();
    }

    @Override
    public void notifyComplete() {
        this.stageCompleted = true;
        this.currStage++;
        this.timingGame.setCompleted(false);
    }

}
