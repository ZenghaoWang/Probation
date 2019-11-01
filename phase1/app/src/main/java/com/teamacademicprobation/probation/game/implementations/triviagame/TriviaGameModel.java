package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.util.Log;

import com.teamacademicprobation.probation.player.PlayerAccess;
import com.teamacademicprobation.probation.player.PlayerManager;

/**
 * Responsible for picking random questions and making sure each question does not get picked more
 * than once. Each instance of TriviaGamePresenter will have its unique TriviaGameModel instance.
 */
class TriviaGameModel {
    private static final String GAMEID = "TriviaGame";
    // For logging purposes
    private static final String TAG = "TriviaGameModel";

    private String playerID;
    private QuestionSet questionset;
    private PlayerAccess playerAccess;

    private int numQuestionsAnswered;
    private Question currentQuestion;
    private boolean finished;
    private int numQuestionsAnsweredCorrectly;

    /**
     * Constructs a new TriviaGameModel with a set of questions QuestionSet
     */
    TriviaGameModel(QuestionSet questionset, String playerID) {
        this.questionset = questionset;
        this.playerID = playerID;
        this.playerAccess = new PlayerManager();

        numQuestionsAnswered = 0;
        numQuestionsAnsweredCorrectly = 0;

        this.finished = false;
    }

    public int getNumQuestionsAnswered() {
        return numQuestionsAnswered;
    }

    public int getNumQuestionsAnsweredCorrectly() {
        return numQuestionsAnsweredCorrectly;
    }

    /**
     * Returns a random question and removes it so that a player cannot get the same question 2 times.
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
     * Increase the number of questions answered by 1 Evaluate whether answer is correct Update number
     * of questions answered correctly if needed
     *
     * @param answer The chosen answer to be compared to the actual answer
     * @return a boolean of whether the answer is correct or not
     */
    boolean answerQuestion(String answer) {
        numQuestionsAnswered += 1;
        boolean answerCorrect = currentQuestion.isAnswerCorrect(answer);
        if (answerCorrect) {
            numQuestionsAnsweredCorrectly += 1;
        }
        return answerCorrect;
    }

    void updateStats(int score) {
        playerAccess.updateStats(playerID, GAMEID, "Score", score);
    }

    boolean isFinished() {
        return finished;
    }

    public String getCurrentQuestion() {
        return currentQuestion.getQuestion();
    }

    public String getAnswer1() {
        return currentQuestion.getAnswer1();
    }

    public String getAnswer2() {
        return currentQuestion.getAnswer2();
    }

    public String getAnswer3() {
        return currentQuestion.getAnswer3();
    }

    public String getAnswer4() {
        return currentQuestion.getAnswer4();
    }
}