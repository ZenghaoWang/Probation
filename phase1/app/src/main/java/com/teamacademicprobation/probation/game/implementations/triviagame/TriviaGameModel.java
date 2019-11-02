package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.util.Log;

import com.teamacademicprobation.probation.player.PlayerAccess;
import com.teamacademicprobation.probation.player.PlayerManager;

// MVP structure from https://github.com/antoniolg/androidmvp

/**
 * The back-end for the trivia game.
 */
class TriviaGameModel {
    private static final String GAMEID = "TriviaGame";
    // For logging purposes
    private static final String TAG = "TriviaGameModel";

    private String playerID;
    private QuestionSet questionset;
    private PlayerAccess playerAccess;

    private int numQuestionsAnsweredCorrectly;
    private int totalNumQuestionsAnswered;
    private Question currentQuestion;
    private boolean finished;

    /**
     * * Construct a new TriviaGameModel.
     *
     * @param questionset The set of questions that this game will use.
     * @param playerID    The ID of the player who is currently playing.
     */
    TriviaGameModel(QuestionSet questionset, String playerID) {
        this.questionset = questionset;
        this.playerID = playerID;
        this.playerAccess = new PlayerManager();

        totalNumQuestionsAnswered = 0;
        numQuestionsAnsweredCorrectly = 0;

        this.finished = false;
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


    /**
     * Grabs a new question if possible.
     * If out of questions, the game is finished.
     */
    void getRandomQuestion() {
        try {
            currentQuestion = questionset.getRandomQuestion();
        } catch (OutOfQuestionsException e) {
            Log.e(TAG, "Out of questions!");
            finished = true;
        }
    }

    /**
     * Increase the number of questions answered by 1
     * Evaluate whether answer is correct
     * Update number of questions answered correctly if needed
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


    /**
     * @return Whether the game is finished.
     */
    boolean isFinished() {
        return finished;
    }


    /**
     * @return A string with information about the current score.
     */
    String generateCurrentScoreString() {
        return "Current Score: "
                + numQuestionsAnsweredCorrectly + "/"
                + totalNumQuestionsAnswered;
    }

    /**
     * @return A score between 0 and 100 representing the proportion of questions answered
     * correctly. 100 is a perfect score.
     */
    private int generateScorePercentage() {
        return (numQuestionsAnsweredCorrectly * 100
                / totalNumQuestionsAnswered);
    }

    /**
     * @return a message for the score screen.
     */
    String generateScoreMessage() {
        return "You answered "
                + numQuestionsAnsweredCorrectly
                + " out of "
                + totalNumQuestionsAnswered +
                " questions correctly!";
    }

    /**
     * Send the percentage of correct answers for this game to the score tracker.
     */
    void updateStats() {
        playerAccess.updateStats(playerID, GAMEID, "score", generateScorePercentage());
    }
}