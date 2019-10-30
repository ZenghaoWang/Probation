package com.teamacademicprobation.probation.ui;

import android.view.View;

public interface TriviaView {
     void updateQuestion();
     void onAnswer(View view);
     void updateScore();
     void goToScoreScreen();

     enum Element{
          QUESTION, ANSWER1, ANSWER2, ANSWER3, ANSWER4
     }
     void setElement(Element element, String newText);
}
