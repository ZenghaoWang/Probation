package com.teamacademicprobation.probation.game.implementations.triviagame;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The default set of questions for the trivia game.
 * To create a new set of questions, extend QuestionSet.
 */
class DefaultQuestionSet extends QuestionSet {

    /**
     * Construct a new questionset with the following questions.
     */
    DefaultQuestionSet() {
        super();

        questions.add(new Question("What is 1 + 1?",
                new ArrayList<>(Arrays.asList("1", "2", "69", "420")),
                1));

        questions.add(new Question("What is the capital of Canada?",
                new ArrayList<>(Arrays.asList("Ottawa", "Quebec City", "UofT", "Shanghai")),
                0));

        questions.add(new Question("What is the average case runtime of QuickSort?",
                new ArrayList<>(Arrays.asList("n^2", "constant", "2^n", "nlogn")),
                3));

        questions.add(new Question("Who is the president of the University of Toronto?",
                new ArrayList<>(Arrays.asList("A pack of geese", "A rock",
                        "Meric Gertler", "Ronald Mcdonald")),
                2));

        questions.add(new Question
                ("Which set of numbers " + "do you perform simple induction over?",
                        new ArrayList<>
                                (Arrays.asList("Naturals", "Rationals", "Reals", "Idk I failed 165")),
                        0));

        questions.add(new Question("What is the mitochondria?",
                new ArrayList<>(Arrays.asList("Your questions suck",
                        "The cell of the powerhouse", "The powerhouse of the cell", "e")),
                2));

        questions.add(new Question("Is it a good idea to take MAT237?",
                new ArrayList<>(Arrays.asList("NO", "no", "No", "Hell no")),
                1));

        questions.add(new Question("What religion does Tom Cruise follow?",
                new ArrayList<>(Arrays.asList("Hinduism", "Christianity",
                        "Islam", "Scientology")),
                3));
    }
}
