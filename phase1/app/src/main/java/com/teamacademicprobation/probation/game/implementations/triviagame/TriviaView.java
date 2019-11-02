package com.teamacademicprobation.probation.game.implementations.triviagame;

// MVP structure from https://github.com/antoniolg/androidmvp

public interface TriviaView {

  void goToScoreScreen(String s);

  void showToast(String message);

  void setQuestion(String newText);

  void setAnswer1(String newText);

  void setAnswer2(String newText);

  void setAnswer3(String newText);

  void setAnswer4(String newText);

  void setScore(String newText);
}
