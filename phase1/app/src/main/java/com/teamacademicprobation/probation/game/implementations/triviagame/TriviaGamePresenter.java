package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.util.Log;



//TODO: Documentation

// MVP structure from https://github.com/antoniolg/androidmvp
class TriviaGamePresenter {
    // Used for logging purposes.
    private static final String TAG = "TriviaGamePresenter";
    // The UI interface associated with this TriviaGamePresenter instance.
    private TriviaView triviaView;

    private static final String CORRECT_ANSWER_MESSAGE = "Correct!";
    private static final String WRONG_ANSWER_MESSAGE = "Wrong answer :(((";

    private boolean finished;
    private Question currentQuestion;
    private QuestionManager questionManager;
    private int numQuestionsAnswered;
    private int numQuestionsAnsweredCorrectly;


    TriviaGamePresenter(TriviaView triviaView) {
        finished = false;
        questionManager = new QuestionManager(new DefaultQuestionSet());
        numQuestionsAnswered = 0;
        numQuestionsAnsweredCorrectly = 0;

        this.triviaView = triviaView;
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
                triviaView.setQuestion(currentQuestion.getQuestion());
                triviaView.setAnswer1(currentQuestion.getAnswer1());
                triviaView.setAnswer2(currentQuestion.getAnswer2());
                triviaView.setAnswer3(currentQuestion.getAnswer3());
                triviaView.setAnswer4(currentQuestion.getAnswer4());

                triviaView.setScore(generateCurrentScore());
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

    String generateScoreMessage() {
        return "You answered "
                + numQuestionsAnsweredCorrectly + " out of " + numQuestionsAnswered +
                " questions correctly!";
    }
}
