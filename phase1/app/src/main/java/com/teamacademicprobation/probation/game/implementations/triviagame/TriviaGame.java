package com.teamacademicprobation.probation.game.implementations.triviagame;

import com.teamacademicprobation.probation.game.Game;

/**
 * A series of trivia questions with a time limit.
 * Each question will have 4 possible answers; 1 is correct.
 */
public class TriviaGame extends Game {
  private QuestionManager questionManager = new QuestionManager();


  @Override
  public void play() {}

  @Override
  public void render() {}
}
