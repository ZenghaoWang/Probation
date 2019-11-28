package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.content.Context;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel.PowerUpSelect;
import com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel.TimingGame;
import com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel.TimingGameStyle;
import com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel.TimingGameStyles;
import com.teamacademicprobation.probation.player.PlayerGameStatsAccess;
import com.teamacademicprobation.probation.player.PlayerManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimingGameModel implements Drawable {

    private static final int STAGES = 4;
    private static final String GAMEID = "Timing Game";
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

    void buildShips(Context context) {
        timingGame.buildShips(context);
    }

    void buildPowerUpSelect(Context context) {
        this.powerUpSelect.buildPowerUpImages(context);
    }

    boolean isCompleted() {
        return currStage > STAGES || this.timingGame.playerDestroyed();
    }

    int getScore() {
        return timingGame.getScore();
    }

    void onTouch(double touchX) {
        if (this.stageCompleted) {
            powerUpSelect.onTouch(touchX);
            timingGame.upgradePlayer(powerUpSelect.getSelection());
            this.stageCompleted = false;
        } else {
            timingGame.onTouch();
        }
    }

    void updatePlayerStats() {
        Map<String, Integer> statMap = new HashMap<>();
        statMap.put("SCORE", timingGame.getScore());
        statMap.put("CURRENT_STAGE", this.currStage);
        statMap.put("CURRENT_LEVEL", timingGame.getLevel());
        statMap.put("PLAYER_CURR_HEALTH", timingGame.getPlayerHealth());
        statMap.put("PLAYER_MAX_HEALTH", timingGame.getPlayerMaxHealth());
        statMap.put("PLAYER_DAMAGE", timingGame.getPlayerDamage());

        this.playerAccess.updateStats(currPlayerID, GAMEID, statMap);
    }

    void endGame() {
        this.playerAccess.endGame(currPlayerID, true);
    }
}
