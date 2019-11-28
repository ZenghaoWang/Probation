package com.teamacademicprobation.probation.game.implementations.triviagame;

import java.util.ArrayList;
import java.util.Random;

/**
 * When initialized with no parameters, will create a set of default parameters. Can be initialized
 * with different QuestionSetBuilders for different questions.
 */
class QuestionSet {
  private static Random rand = new Random();
  private ArrayList<Question> questions = new ArrayList<>();

  /**
   * Creates a set of questions with question types of each builder in builderList.
   * @param builderList A list of builders, which will add a certain category of questions to the
   *     set.
   */
  QuestionSet(ArrayList<QuestionSetBuilder> builderList) {
    for (QuestionSetBuilder builder : builderList) {
      builder.constructQuestions();
      this.questions.addAll(builder.getQuestions());
    }
  }

  /**
   * Returns a random question and removes it so that a player cannot get the same question 2 times.
   *
   * @return An instance of a question.
   * @throws OutOfQuestionsException if there are no questions left.
   */
  Question getRandomQuestion() throws OutOfQuestionsException {
    if (questions.size() == 0) {
      throw new OutOfQuestionsException();
    }
    return questions.remove(rand.nextInt(questions.size()));
  }

  /** @return The number of questions in the set */
  int getNumQuestions() {
      return questions.size() + 1;
  }
}
