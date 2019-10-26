package com.teamacademicprobation.probation.game.implementations.triviagame;

import java.util.ArrayList;

/**
 * Represents a single multiple-choice trivia question.
 * A questions has 4 possible answers, one of which is correct.
 */
class Question {
    private String question;
    private ArrayList<String> potentialAnswers;

    /**
     * The index of the correct answer in potentialAnswers
     */
    private int correctAnswerID;

    /**
     * Constructs a question.
     *
     * @param question         The question.
     * @param potentialAnswers 4 potential answers.
     * @param correctAnswerID  The index of the correct answer.
     */
    Question(String question, ArrayList<String> potentialAnswers, int correctAnswerID) {
        this.question = question;
        this.potentialAnswers = potentialAnswers;
        this.correctAnswerID = correctAnswerID;
    }

    public String getQuestion() {
        return this.question;
    }

    public ArrayList<String> getPotentialAnswers() {
        return potentialAnswers;
    }

    public boolean isAnswerCorrect(int guess) {
        return guess == correctAnswerID;
    }
}
