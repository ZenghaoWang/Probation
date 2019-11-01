package com.teamacademicprobation.probation.ui.login;

import android.view.View;

import com.teamacademicprobation.probation.player.PlayerAccess;
import com.teamacademicprobation.probation.player.PlayerManager;

import java.io.File;

public class LoginPresenter {

    private LoginView loginView;
    private PlayerAccess playerAccess;

    LoginPresenter(LoginView loginView, File dataFile){
        this.loginView = loginView;
        this.playerAccess = new PlayerManager(dataFile);
    }

    void login(String username, String password){
        String playerID = playerAccess.login(username, password);
        if(playerID != null){
            loginView.goToMainView(playerID);
        }
        else{
            loginView.setErrorMessage("Invalid username or password.");
        }
    }

    void register(String username, String password){
        String playerID = playerAccess.createNewPlayer(username, password);
        if(playerID != null){
            loginView.goToMainView(playerID);
        }
        else{
            loginView.setErrorMessage("Username has already been taken.");
        }
    }

}
