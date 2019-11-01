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


    public String getQuestion() {
        return this.question;
    }

    public boolean isAnswerCorrect(String guess) {
        return guess.equals(potentialAnswers.get(correctAnswerIndex));
    }


    public String getCurrentQuestion() {
        return this.question;
    }

    public String getAnswer1() {
        return this.potentialAnswers.get(0);
    }

    public String getAnswer2() {
        return this.potentialAnswers.get(1);
    }

    public String getAnswer3() {
        return this.potentialAnswers.get(2);
    }

    public String getAnswer4() {
        return this.potentialAnswers.get(3);
    }
}
