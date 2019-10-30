package com.teamacademicprobation.probation.game.implementations.triviagame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Responsible for picking random questions and making sure each question does not get picked more
 * than once.
 * Each instance of TriviaGame will have its unique QuestionManager instance.
 */
class QuestionManager {
    private ArrayList<Question> questions = new ArrayList<>();
    private static Random rand = new Random();

    /**
     * Constructs a new QuestionManager with a fixed set of questions.
     */
    QuestionManager() {
        //TODO: Add more questions
        //TODO: Maybe refactor using builder pattern idk
        //TODO:
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




    }

    /**
     * Returns a random question and removes it so that
     * a player cannot get the same question 2 times.
     *
     * @return An instance of a question.
     * @throws OutOfQuestionsException if there are no questions left.
     */
    public Question getRandomQuestion() throws OutOfQuestionsException {
        if (questions.size() == 0) {
            throw new OutOfQuestionsException();
        }
        return questions.remove(rand.nextInt(questions.size()));
    }
}
