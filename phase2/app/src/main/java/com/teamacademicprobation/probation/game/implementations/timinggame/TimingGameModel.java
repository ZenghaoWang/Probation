package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.content.Context;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel.PowerUpSelect;
import com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel.TimingGame;
import com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel.TimingGameStyle;
import com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel.TimingGameStyles;
import com.teamacademicprobation.probation.player.PlayerManager;
import com.teamacademicprobation.probation.player.PlayerStatsAccess;

import java.util.List;

public class TimingGameModel implements Drawable {

    private TimingGame timingGame;

    private PowerUpSelect powerUpSelect;

    private int currStage = 1;

    private static final int STAGES = 4;

    private boolean stageCompleted;

    /**
     * An object that allows the game to save data.
     */
    private PlayerStatsAccess playerAccess;
    /**
     * The player ID of the player currently playing this game.
     */
    private String currPlayerID;

    TimingGameModel(int screenWidth, int screenHeight, String playerID){
        this.timingGame = new TimingGame(screenWidth, screenHeight);
        this.powerUpSelect = new PowerUpSelect(screenWidth, screenHeight, new TimingGameStyle(TimingGameStyles.STYLE3)); //TODO: FIX THIS FOR PREFERENCES
        this.currPlayerID = playerID;
        this.playerAccess = new PlayerManager();
    }


    void update(){
        timingGame.update();
        if(this.timingGame.isCompleted()){
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

    void buildPowerUpSelect(Context context) {this.powerUpSelect.buildPowerUpImages(context);}

    boolean isCompleted() {
        return currStage > STAGES || this.timingGame.playerDestroyed();
    }

    int getScore() {
        return timingGame.getScore();
    }

    void onTouch(double touchX) {
        if(this.stageCompleted){
            powerUpSelect.onTouch(touchX);
            timingGame.upgradePlayer(powerUpSelect.getSelection());
            this.stageCompleted = false;
        }
        else{
            timingGame.onTouch();
        }
    }
}
