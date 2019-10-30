package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.util.Log;

import com.teamacademicprobation.probation.game.TriviaView;

//TODO: Documentation
class TriviaGame {
    // Used for logging purposes.
    private static final String TAG = "TriviaGame";
    // The UI interface associated with this TriviaGame instance.
    private final TriviaView triviaView;

    private static final String CORRECT_ANSWER_MESSAGE = "Correct!";
    private static final String WRONG_ANSWER_MESSAGE = "Wrong answer :(((";

    private boolean finished;
    private Question currentQuestion;
    private QuestionManager questionManager;
    private int numQuestionsAnswered;
    private int numQuestionsAnsweredCorrectly;


    TriviaGame(TriviaView triviaView) {
        finished = false;
        questionManager = new QuestionManager();
        numQuestionsAnswered = 0;
        numQuestionsAnsweredCorrectly = 0;

        this.triviaView = triviaView;
    }

    int getNumQuestionsAnswered() {
        return numQuestionsAnswered;
    }

    int getNumQuestionsAnsweredCorrectly() {
        return numQuestionsAnsweredCorrectly;
    }

    /**
     * Calls questionManager to get a new question, then either updates the TriviaView with a new
     * question or sends TriviaView to the score screen if there are no more questions.
     */
    void updateView() {
        try {
            currentQuestion = questionManager.getRandomQuestion();
        } catch (OutOfQuestionsException e) {
            Log.e(TAG, "Out of questions!");
            finished = true;
        } finally{
            if (this.finished) {
                triviaView.goToScoreScreen();
            } else {
                triviaView.setElement(TriviaView.Element.QUESTION, currentQuestion.getQuestion());
                triviaView.setElement(TriviaView.Element.ANSWER1, currentQuestion.getAnswer1());
                triviaView.setElement(TriviaView.Element.ANSWER2, currentQuestion.getAnswer2());
                triviaView.setElement(TriviaView.Element.ANSWER3, currentQuestion.getAnswer3());
                triviaView.setElement(TriviaView.Element.ANSWER4, currentQuestion.getAnswer4());
                triviaView.setElement(TriviaView.Element.SCOREBOARD, generateCurrentScore());

            }
        }
    }

    private String generateCurrentScore() {
        return "Current Score: "
                + numQuestionsAnsweredCorrectly + "/" + numQuestionsAnswered;
    }


    /**
     * Increase the number of questions answered by 1
     * Evaluate whether answer is correct
     * Update number of questions answered correctly if needed
     * @param answer The chosen answer to be compared to the actual answer
     */
    void answerQuestion(String answer) {
        numQuestionsAnswered += 1;
        boolean answerCorrect = currentQuestion.isAnswerCorrect(answer);
        if (answerCorrect) {
            numQuestionsAnsweredCorrectly += 1;
        }

        String message;
        if (answerCorrect) {
            message = CORRECT_ANSWER_MESSAGE;
        } else {
            message = WRONG_ANSWER_MESSAGE;
        }
        triviaView.showMessage(message);
    }

}
