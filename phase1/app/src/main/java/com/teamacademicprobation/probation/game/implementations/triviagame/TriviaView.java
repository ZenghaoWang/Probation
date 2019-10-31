package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.view.View;

public interface TriviaView {


     void updateView();

    void onAnswer(View answerClicked);
     void updateScore();
     void goToScoreScreen();
     void showMessage(String message);

     enum Element{
          QUESTION, ANSWER1, ANSWER2, ANSWER3, ANSWER4, SCOREBOARD
     }
     void setElement(Element element, String newText);
}
