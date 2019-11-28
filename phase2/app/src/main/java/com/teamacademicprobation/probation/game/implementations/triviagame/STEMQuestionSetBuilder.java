package com.teamacademicprobation.probation.game.implementations.triviagame;

import java.util.ArrayList;
import java.util.Arrays;

class STEMQuestionSetBuilder extends QuestionSetBuilder {
    @Override
    void constructQuestions() {
        this.questions.add(
                new Question("What is 1 + 1?", new ArrayList<>(Arrays.asList("1", "2", "69", "420")), 1));

        this.questions.add(
                new Question(
                        "What is the average case runtime of QuickSort?",
                        new ArrayList<>(Arrays.asList("n^2", "constant", "2^n", "nlogn")),
                        3));

        this.questions.add(
                new Question(
                        "Which set of numbers " + "do you perform simple induction over?",
                        new ArrayList<>(Arrays.asList("Naturals", "Rationals", "Reals", "Idk I failed 165")),
                        0));

        this.questions.add(
                new Question(
                        "What is the mitochondria?",
                        new ArrayList<>(
                                Arrays.asList(
                                        "Your questions suck",
                                        "The cell of the powerhouse",
                                        "The powerhouse of the cell",
                                        "e")),
                        2));

        this.questions.add(
                new Question(
                        "Is it a good idea to take MAT237?",
                        new ArrayList<>(Arrays.asList("NO", "no", "No", "Hell no")),
                        1));
    }
}
