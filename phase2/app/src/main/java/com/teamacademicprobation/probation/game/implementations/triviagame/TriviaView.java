package com.teamacademicprobation.probation.game.implementations.triviagame;

// MVP structure from https://github.com/antoniolg/androidmvp

/**
 * An interface implemented by trivia game UIs.
 */
public interface TriviaView {

    /**
     * Send the app to the score screen
     *
     * @param scoreMessage the message to be displayed on the score screen.
     */
    void goToScoreScreen(String scoreMessage, String playerID, String gameID);

    /**
     * Show a toast pop-up.
     *
     * @param message The message for the toast to show.
     */
    void showToast(String message);


    void playSound(Boolean isCorrect);

    // Setters for the elements on the screen.
    void setQuestion(String newText);

    void setAnswer1(String newText);

    void setAnswer2(String newText);

    void setAnswer3(String newText);

    void setAnswer4(String newText);

    void setScore(String newText);

    void setQuestionsRemaining(String newText);

    void disableInput();
}
