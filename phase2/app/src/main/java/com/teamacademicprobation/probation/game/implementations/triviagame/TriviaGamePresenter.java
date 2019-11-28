package com.teamacademicprobation.probation.game.implementations.triviagame;

// MVP structure from https://github.com/antoniolg/androidmvp

import java.util.ArrayList;

/**
 * Passes information between the front-end(TriviaGameActivity) and TriviaGameModel.
 */
class TriviaGamePresenter {

    // Messages for the toast pop-up upon answering a question
    private static final String CORRECT_ANSWER_MESSAGE = "Correct!";
    private static final String WRONG_ANSWER_MESSAGE = "Wrong answer :(((";
    // The UI interface associated with this TriviaGamePresenter instance.
    private TriviaView view;
    // The back-end
    private TriviaGameModel model;

    /**
     * Constructs a TriviaGamePresenter on the creation of a given game instance being played by
     * playerID.
     *
     * @param view        The UI interface which initialized this presenter.
     * @param playerID    The ID of the player currently playing. Used for stat-tracking
     * @param builderList A list of all question categories to be included.
     */
    TriviaGamePresenter(TriviaView view, String playerID, ArrayList<QuestionSetBuilder> builderList) {
        model = new TriviaGameModel(new QuestionSet(builderList), playerID);
        this.view = view;
    }

    /**
     * Called when a button is clicked on the view. Gets a new question, sends the player to the score
     * screen if there are no more questions, and updates the elements on the screen otherwise.
     */
    void updateView() {
        model.getRandomQuestion();
        if (model.isCompleted()) {
            model.endGame();
            view.goToScoreScreen(model.generateScoreMessage());
        } else {
            view.setQuestion(model.getCurrentQuestion());
            view.setAnswer1(model.getAnswer1());
            view.setAnswer2(model.getAnswer2());
            view.setAnswer3(model.getAnswer3());
            view.setAnswer4(model.getAnswer4());
            view.setScore(model.generateCurrentScoreString());
            view.setQuestionsRemaining(model.generateQuestionsRemainingString());
        }
    }

    /**
     * Evaluate whether the user-inputted answer is correct and display the appropriate toast message
     * on-screen. Updates high school stats accordingly.
     */
    void answerQuestion(String answer) {
        boolean answerCorrect = model.answerQuestion(answer);
        view.playSound(answerCorrect);
        model.updateStats();

        String message;
        if (answerCorrect) {
            message = CORRECT_ANSWER_MESSAGE;
        } else {
            message = WRONG_ANSWER_MESSAGE;
        }
        view.showToast(message);
  }
}
