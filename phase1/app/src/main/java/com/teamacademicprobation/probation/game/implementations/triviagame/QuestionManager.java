package com.teamacademicprobation.probation.game.implementations.triviagame;

/**
 * Responsible for picking random questions and making sure each question does not get picked more
 * than once.
 * Each instance of TriviaGamePresenter will have its unique QuestionManager instance.
 */

class QuestionManager {
    private QuestionSet questionset;

    /**
     * Constructs a new QuestionManager with a set of questions QuestionSet.
     */
    QuestionManager(QuestionSet questionset) {
        this.questionset = questionset;
    }

    /**
     * Returns a random question and removes it so that
     * a player cannot get the same question 2 times.
     *
     * @return An instance of a question.
     * @throws OutOfQuestionsException if there are no questions left.
     */
    Question getRandomQuestion() throws OutOfQuestionsException {
        return questionset.getRandomQuestion();
    }
}
