package com.teamacademicprobation.probation.game.implementations.triviagame;

//TODO: Documentation

// MVP structure from https://github.com/antoniolg/androidmvp
class TriviaGamePresenter {
    // The UI interface associated with this TriviaGamePresenter instance.
    private TriviaView triviaView;

    private static final String CORRECT_ANSWER_MESSAGE = "Correct!";
    private static final String WRONG_ANSWER_MESSAGE = "Wrong answer :(((";

    private TriviaGameModel triviagamemodel;


    TriviaGamePresenter(TriviaView triviaView, String playerID) {
        triviagamemodel = new TriviaGameModel(new DefaultQuestionSet(), playerID);


        this.triviaView = triviaView;
    }


    /**
     * Calls TriviaGameModel to get a new question, then either updates the TriviaView with a new
     * question or sends TriviaView to the score screen if there are no more questions.
     */
    void updateView() {
        triviagamemodel.getRandomQuestion();
        if (triviagamemodel.isFinished()) {
            triviaView.goToScoreScreen();
            triviagamemodel.updateStats(generateScorePercentage());
        } else {
            triviaView.setQuestion(triviagamemodel.getCurrentQuestion());
            triviaView.setAnswer1(triviagamemodel.getAnswer1());
            triviaView.setAnswer2(triviagamemodel.getAnswer2());
            triviaView.setAnswer3(triviagamemodel.getAnswer3());
            triviaView.setAnswer4(triviagamemodel.getAnswer4());
            triviaView.setScore(generateCurrentScoreString());
        }
    }


    private String generateCurrentScoreString() {
        return "Current Score: "
                + triviagamemodel.getNumQuestionsAnsweredCorrectly() + "/"
                + triviagamemodel.getNumQuestionsAnswered();
    }

    /**
     * @return A score between 0 and 100.
     */
    private int generateScorePercentage() {
        return (triviagamemodel.getNumQuestionsAnsweredCorrectly()
                / triviagamemodel.getNumQuestionsAnswered()) * 100;
    }


    /**
     * Increase the number of questions answered by 1
     * Evaluate whether answer is correct
     * Update number of questions answered correctly if needed
     * @param answer The chosen answer to be compared to the actual answer
     */
    void answerQuestion(String answer) {
        boolean answerCorrect = triviagamemodel.answerQuestion(answer);
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
                + triviagamemodel.getNumQuestionsAnsweredCorrectly()
                + " out of "
                + triviagamemodel.getNumQuestionsAnswered() +
                " questions correctly!";
    }
}
