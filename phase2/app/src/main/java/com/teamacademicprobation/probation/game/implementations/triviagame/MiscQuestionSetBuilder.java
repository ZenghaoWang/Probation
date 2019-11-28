package com.teamacademicprobation.probation.game.implementations.triviagame;

import java.util.ArrayList;
import java.util.Arrays;

class MiscQuestionSetBuilder extends QuestionSetBuilder {
  @Override
  void constructQuestions() {
    this.questions.add(
            new Question(
                    "What religion does Tom Cruise follow?",
                    new ArrayList<>(Arrays.asList("Hinduism", "Christianity", "Islam", "Scientology")),
                    3));
  }
}
