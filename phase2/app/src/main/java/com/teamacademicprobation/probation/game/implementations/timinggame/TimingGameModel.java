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

public class TimingGameModel implements Drawable, TimingGameListener {

    private static final int STAGES = 10;
    static final String GAMEID = "Timing Game";
    private TimingGame timingGame;
    private PowerUpSelect powerUpSelect;
    private int currStage = 1;
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


    TimingGameModel(int screenWidth, int screenHeight, String playerID) {
        this.timingGame = new TimingGame(screenWidth, screenHeight);
        this.powerUpSelect =
                new PowerUpSelect(
                        screenWidth,
                        screenHeight,
                        new TimingGameStyle(TimingGameStyles.STYLE3)); // TODO: FIX THIS FOR PREFERENCES
        this.currPlayerID = playerID;
        this.playerAccess = new PlayerManager();

    }

    void loadPlayerData() {
        if (currPlayerID != null && this.playerAccess.isBeingPlayed(currPlayerID, GAMEID)) {
            Map<String, Integer> statMap = this.playerAccess.getCurrStats(currPlayerID, GAMEID);
            loadMetaData(statMap);
            loadPlayerShipData(statMap);
            loadEnemyShipData(statMap);
            System.out.println(statMap);
        }

    }

    @SuppressWarnings("ConstantConditions")
    private void loadMetaData(Map<String, Integer> statMap) {
        this.currStage = (statMap.get(CURR_STAGE) != null) ? statMap.get(CURR_STAGE) : 1;
        this.timingGame.setLevel((statMap.get(CURR_LEVEL) != null) ? statMap.get(CURR_LEVEL) : 1);
        int score = (statMap.get(ScoreScreenActivity.SCORE_KEY) != null) ? statMap.get(ScoreScreenActivity.SCORE_KEY) : 0;
        this.timingGame.setScore(score);
    }

    @SuppressWarnings("ConstantConditions")
    private void loadEnemyShipData(Map<String, Integer> statMap) {
        this.timingGame.buildEnemyShip();
        int enemyHealth = (statMap.get(ENEMY_CURR_HEALTH) != null) ? statMap.get(ENEMY_CURR_HEALTH) : 1;
        this.timingGame.setEnemyShipHealth(enemyHealth);
    }

    @SuppressWarnings("ConstantConditions")
    private void loadPlayerShipData(Map<String, Integer> statMap) {
        int playerMaxHealth = (statMap.get(PLAYER_MAX_HEALTH) != null) ? statMap.get(PLAYER_MAX_HEALTH) : 1;
        int playerHealth = (statMap.get(PLAYER_CURR_HEALTH) != null) ? statMap.get(PLAYER_CURR_HEALTH) : 1;
        int playerDamage = (statMap.get(PLAYER_CURR_DAMAGE) != null) ? statMap.get(PLAYER_CURR_DAMAGE) : 1;
        this.timingGame.setPlayerShip(playerMaxHealth, playerHealth, playerDamage);
    }

    void update() {
        timingGame.update();
        if (this.timingGame.isCompleted()) {
            this.stageCompleted = true;
            this.currStage++;
            this.timingGame.setCompleted(false);
        }
    }

    @Override
    public List<AndroidDrawer> getDrawers() {

        return this.stageCompleted ? powerUpSelect.getDrawers() : timingGame.getDrawers();
    }

    void buildShips(Resources resources) {
        timingGame.buildShips(resources);
    }

    void buildPowerUpSelect(Resources resources) {
        this.powerUpSelect.buildPowerUpImages(resources);
    }

    boolean isCompleted() {
        return currStage > STAGES || this.timingGame.playerDestroyed();
    }

    int getScore() {
        return timingGame.getScore();
    }

    String getPlayerID() {
        return this.currPlayerID;
    }

    void onTouch(double touchX) {
        if (this.stageCompleted) {
            powerUpSelect.onTouch(touchX);
            timingGame.upgradePlayer(powerUpSelect.getSelection());
            this.stageCompleted = false;
        } else {
            timingGame.onTouch(this);
        }
    }

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
    public void updateListener() {
        this.updatePlayerStats();
    }

}
