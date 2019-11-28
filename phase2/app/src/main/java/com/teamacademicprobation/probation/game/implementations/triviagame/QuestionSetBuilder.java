package com.teamacademicprobation.probation.game.implementations.triviagame;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * An abstract builder to construct sets of questions for the trivia game. Do not instantiate
 * directly.
 */
abstract class QuestionSetBuilder implements Serializable {
    ArrayList<Question> questions;

    QuestionSetBuilder() {
        this.questions = new ArrayList<>();
    }

    abstract void constructQuestions();

    ArrayList<Question> getQuestions() {
        return this.questions;
    }
}
