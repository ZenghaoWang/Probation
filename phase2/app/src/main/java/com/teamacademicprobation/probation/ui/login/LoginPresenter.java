package com.teamacademicprobation.probation.ui.login;

import com.teamacademicprobation.probation.player.PlayerLoginAccess;
import com.teamacademicprobation.probation.player.PlayerManager;

import java.io.File;

/**
 * A presenter object for login purposes.
 */
class LoginPresenter {

  private LoginView loginView;
  private PlayerLoginAccess playerAccess;

  /**
   * Initializes this login presenter.
   *
   * @param loginView The view that instantiated this presenter
   * @param dataFile The file to save data into.
   */
  LoginPresenter(LoginView loginView, File dataFile) {
    this.loginView = loginView;
    this.playerAccess = new PlayerManager();
    PlayerManager.setDataFile(dataFile);
  }

    /**
     * Logs into the game. It does not log into the game if the username or password is empty, or if
     * the password is invalid.
     *
   * @param username The username written in the text field.
   * @param password The password written in the text field.
   */
  void login(String username, String password) {
    if (checkEmpty(username, password)) {
      return;
    }

    String playerID = playerAccess.login(username.trim(), password);
    if (playerID != null) {
      loginView.goToMainView(playerID);
    } else {
      loginView.setErrorMessage("Invalid username or password.");
    }
  }

    /**
     * Registers a new player, and logs them into the game. It does not register if the username has
     * already been taken.
     *
   * @param username The username written in the text field.
   * @param password The password written in the text field.
   */
  void register(String username, String password) {
    if (checkEmpty(username, password)) {
      return;
    }

    String playerID = playerAccess.createNewPlayer(username.trim(), password);
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
