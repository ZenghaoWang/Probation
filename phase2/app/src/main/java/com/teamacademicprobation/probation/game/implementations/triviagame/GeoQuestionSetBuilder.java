package com.teamacademicprobation.probation.game.implementations.triviagame;

import java.util.ArrayList;
import java.util.Arrays;

public class GeoQuestionSetBuilder extends QuestionSetBuilder {
    @Override
    void constructQuestions() {
        this.questions.add(
                new Question(
                        "What is the capital of Canada?",
                        new ArrayList<>(Arrays.asList("Ottawa", "Quebec City", "UofT", "Shanghai")),
                        0));

        this.questions.add(
                new Question(
                        "Who is the president of the University of Toronto?",
                        new ArrayList<>(
                                Arrays.asList("A pack of geese", "A rock", "Meric Gertler", "Ronald Mcdonald")),
                        2));
    }
}
