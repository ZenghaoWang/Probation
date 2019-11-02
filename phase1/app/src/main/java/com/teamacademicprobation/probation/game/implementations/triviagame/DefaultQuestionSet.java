package com.teamacademicprobation.probation.game.implementations.triviagame;

import java.util.ArrayList;
import java.util.Arrays;

class DefaultQuestionSet extends QuestionSet {

  DefaultQuestionSet() {
    questions.add(
        new Question("What is 1 + 1?", new ArrayList<>(Arrays.asList("1", "2", "69", "420")), 1));

    questions.add(
        new Question(
            "What is the capital of Canada?",
            new ArrayList<>(Arrays.asList("Ottawa", "Quebec City", "UofT", "Shanghai")),
            0));

    questions.add(
        new Question(
            "What is the average case runtime of QuickSort?",
            new ArrayList<>(Arrays.asList("n^2", "constant", "2^n", "nlogn")),
            3));

    questions.add(
        new Question(
            "Who is the president of the University of Toronto?",
            new ArrayList<>(
                Arrays.asList("A pack of geese", "A rock", "Meric Gertler", "Ronald Mcdonald")),
            2));
  }
}
