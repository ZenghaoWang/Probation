package com.teamacademicprobation.probation.ui.login;

/**
 * An interface that defines the methods required by the login GUI.
 */
interface LoginView {

  void goToMainView(String playerID, String username);

  void setErrorMessage(String errorMessage);
}
