package com.teamacademicprobation.probation.ui.login;

import com.teamacademicprobation.probation.player.PlayerAccess;
import com.teamacademicprobation.probation.player.PlayerManager;

import java.io.File;

public class LoginPresenter {

  private LoginView loginView;
  private PlayerAccess playerAccess;

  LoginPresenter(LoginView loginView, File dataFile) {
    this.loginView = loginView;
    this.playerAccess = new PlayerManager();
    PlayerManager.setDataFile(dataFile);
  }

  void login(String username, String password) {
    if (checkEmpty(username, password)) {
      return;
    }

    String playerID = playerAccess.login(username, password);
    if (playerID != null) {
      loginView.goToMainView(playerID);
    } else {
      loginView.setErrorMessage("Invalid username or password.");
    }
  }

  void register(String username, String password) {
    if (checkEmpty(username, password)) {
      return;
    }

    String playerID = playerAccess.createNewPlayer(username, password);
    if (playerID != null) {
      loginView.goToMainView(playerID);
    } else {
      loginView.setErrorMessage("Username has already been taken.");
    }
  }

  private boolean checkEmpty(String username, String password) {
    if (username.equals("")) {
      loginView.setErrorMessage("Please enter a Username.");
      return true;
    } else if (password.equals("")) {
      loginView.setErrorMessage("Please enter a Password");
      return true;
    }
    return false;
  }
}
