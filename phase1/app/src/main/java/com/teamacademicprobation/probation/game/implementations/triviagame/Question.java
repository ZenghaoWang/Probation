package com.teamacademicprobation.probation.game.implementations.triviagame;

import java.util.ArrayList;

/**
 * Represents a single multiple-choice trivia question.
 * A questions has 4 possible answers, one of which is correct.
 */
//TODO: Documentation
class Question {
    private String question;
    private ArrayList<String> potentialAnswers;

    /**
     * The index of the correct answer in potentialAnswers
     */
    private int correctAnswerIndex;

    /**
     * Constructs a question.
     *
     * @param question         The question.
     * @param potentialAnswers 4 potential answers.
     * @param correctAnswerIndex  The index of the correct answer.
     */
    Question(String question, ArrayList<String> potentialAnswers, int correctAnswerIndex) {
        this.question = question;
        this.potentialAnswers = potentialAnswers;
        this.correctAnswerIndex = correctAnswerIndex;
    }


    boolean isAnswerCorrect(String guess) {
        return guess.equals(potentialAnswers.get(correctAnswerIndex));
    }

    String getQuestion() {
        return this.question;
    }


    String getAnswer1() {
        return this.potentialAnswers.get(0);
    }

    String getAnswer2() {
        return this.potentialAnswers.get(1);
    }

    String getAnswer3() {
        return this.potentialAnswers.get(2);
    }

    String getAnswer4() {
        return this.potentialAnswers.get(3);
    }
}
