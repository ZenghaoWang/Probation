package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.view.View;

interface TriviaView {
     void updateQuestion();
     void onAnswer(View view);
     void updateScore();
     void goToScoreScreen();

     enum Element{
          QUESTION, ANSWER1, ANSWER2, ANSWER3, ANSWER4
     }
     void setElement(Element element, String newText);
}
