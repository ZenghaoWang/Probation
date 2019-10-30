package com.teamacademicprobation.probation.game;

import android.view.View;

public interface TriviaView {


     void updateQuestion();
     void onAnswer(View view);
     void updateScore();
     void goToScoreScreen();
     void showMessage(String message);

     enum Element{
          QUESTION, ANSWER1, ANSWER2, ANSWER3, ANSWER4
     }
     void setElement(Element element, String newText);
}
