package com.teamacademicprobation.probation.game.implementations.triviagame;

import java.util.ArrayList;
import java.util.Random;


abstract class QuestionSet {
    private static Random rand = new Random();
    ArrayList<Question> questions = new ArrayList<>();

    /**
     * Returns a random question and removes it so that
     * a player cannot get the same question 2 times.
     *
     * @return An instance of a question.
     * @throws OutOfQuestionsException if there are no questions left.
     */
    Question getRandomQuestion() throws OutOfQuestionsException {
        if (questions.size() == 0) {
            throw new OutOfQuestionsException();
        }
        return questions.remove(rand.nextInt(questions.size()));
    }
}
