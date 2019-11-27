package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.util.Log;

import com.teamacademicprobation.probation.player.PlayerManager;
import com.teamacademicprobation.probation.player.PlayerStatsAccess;

// MVP structure from https://github.com/antoniolg/androidmvp

/** The back-end for the trivia game. */
class TriviaGameModel {
  private static final String GAMEID = "TriviaGame";
  // For logging purposes
  private static final String TAG = "TriviaGameModel";

  private String playerID;
  private QuestionSet questionSet;
  private PlayerStatsAccess playerAccess;

  private int numQuestionsAnsweredCorrectly;
  private int totalNumQuestionsAnswered;
  private int numQuestionsRemaining;
  private Question currentQuestion;
  private boolean completed;

  /**
   * * Construct a new TriviaGameModel.
   *
   * @param questionSet The set of questions that this game will use.
   * @param playerID The ID of the player who is currently playing.
   */
  TriviaGameModel(QuestionSet questionSet, String playerID) {
    this.questionSet = questionSet;
    this.playerID = playerID;
    this.playerAccess = new PlayerManager();

    totalNumQuestionsAnswered = 0;
    numQuestionsAnsweredCorrectly = 0;
    numQuestionsRemaining = questionSet.getNumQuestions();

    this.completed = false;
  }

  // Start of Getters
  String getCurrentQuestion() {
    return currentQuestion.getQuestion();
  }

  String getAnswer1() {
    return currentQuestion.getAnswer1();
  }

  String getAnswer2() {
    return currentQuestion.getAnswer2();
  }

  String getAnswer3() {
    return currentQuestion.getAnswer3();
  }

  String getAnswer4() {
    return currentQuestion.getAnswer4();
  }
  // End of Getters

  /** Grabs a new question if possible. If out of questions, the game is completed. */
  void getRandomQuestion() {
    try {
      currentQuestion = questionSet.getRandomQuestion();
      numQuestionsRemaining -= 1;
    } catch (OutOfQuestionsException e) {
      Log.e(TAG, "Out of questions!");
      completed = true;
    }
  }

  /**
   * Increase the number of questions answered by 1 Evaluate whether answer is correct Update number
   * of questions answered correctly if needed
   *
   * @param answer The chosen answer to be compared to the actual answer
   * @return a boolean of whether the answer is correct or not
   */
  boolean answerQuestion(String answer) {
    totalNumQuestionsAnswered += 1;
    boolean answerCorrect = currentQuestion.isAnswerCorrect(answer);
    if (answerCorrect) {
      numQuestionsAnsweredCorrectly += 1;
    }
    return answerCorrect;
  }

  /** @return Whether the game is completed. */
  boolean isCompleted() {
    return completed;
  }

  /** @return A string with information about the current score. */
  String generateCurrentScoreString() {
    return "Current Score: " + numQuestionsAnsweredCorrectly + "/" + totalNumQuestionsAnswered;
  }

  /**
   * @return A score between 0 and 100 representing the proportion of questions answered correctly.
   *     100 is a perfect score.
   */
  private int generateScorePercentage() {
    if (totalNumQuestionsAnswered != 0) {
      return (numQuestionsAnsweredCorrectly * 100 / totalNumQuestionsAnswered);
    }
    return 0;
  }

  /** @return a message for the score screen. */
  String generateScoreMessage() {
    return "You answered "
        + numQuestionsAnsweredCorrectly
        + " out of "
        + totalNumQuestionsAnswered
        + " questions correctly!";
  }

  /** @return A message that indicates how many questions are left to answer. */
  String generateQuestionsRemainingString() {
    return numQuestionsRemaining + " Questions Left";
  }

  /** Send the percentage of correct answers for this game to the score tracker. */
  void updateStats() {
    playerAccess.updateStats(playerID, GAMEID, "score", generateScorePercentage());
  }

  /** Ends the game */
  void endGame() {
    this.playerAccess.endGame(playerID, true);
  }
}
