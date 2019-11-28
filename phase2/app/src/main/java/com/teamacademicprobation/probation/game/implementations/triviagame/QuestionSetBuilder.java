package com.teamacademicprobation.probation.game.implementations.triviagame;

import java.io.Serializable;
import java.util.ArrayList;

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
