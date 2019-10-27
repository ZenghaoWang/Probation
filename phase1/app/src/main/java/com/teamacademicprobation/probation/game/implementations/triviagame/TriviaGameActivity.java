package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;

//TODO: Documentation
public class TriviaGameActivity extends AppCompatActivity {
    private TriviaGame triviaGame;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private TextView question;

    /*
    TODO: Add these 2 views to xml
     */
    private int numQuestionsAnswered;
    private int numQuestionsAnsweredCorrectly;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);

        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        question = findViewById(R.id.question);

        triviaGame = new TriviaGame();
        updateQuestion();
    }

    private void updateQuestion() {
        triviaGame.updateQuestion();
        if (triviaGame.isFinished()) {
            scoreScreen();
        } else {
            /*
            TODO
            Update values of question, answer1, 2, 3, 4
             */
            this.question.setText(triviaGame.getQuestion());
            this.answer1.setText(triviaGame.getAnswer1());
            this.answer2.setText(triviaGame.getAnswer2());
            this.answer3.setText(triviaGame.getAnswer3());
            this.answer4.setText(triviaGame.getAnswer4());

        }
    }

    /**
     * Evaluate whether the answer is correct and update the score accordingly.
     *
     * @param view The answer that was clicked.
     */
    public void onClick(View view) {

        //TODO: CaSTiNG iS BAd
        String answer = ((Button) view).getText().toString();
        triviaGame.answerQuestion(answer);
        updateScore();
        updateQuestion();
    }

    private void updateScore() {
        numQuestionsAnswered = triviaGame.getNumQuestionsAnswered();
        numQuestionsAnsweredCorrectly = triviaGame.getNumQuestionsAnsweredCorrectly();
    }

    /**
     * When all questions have been answered, head to the results screen.
     */
    private void scoreScreen() {
        Intent intent = new Intent(this, TriviaScoreScreenActivity.class);
        //TODO: Give intent score and total questions

        startActivity(intent);
    }


}