package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.ui.ScoreScreenActivity;
import com.teamacademicprobation.probation.ui.login.LoginActivity;

import java.util.ArrayList;

//MVP structure from https://github.com/antoniolg/androidmvp

/**
 * The front-end for the trivia game.
 */
public class TriviaGameActivity extends AppCompatActivity implements TriviaView {
    private TriviaGamePresenter triviaGamePresenter; // This was static.
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private TextView question;
    private TextView score;
    private TextView questionsRemaining;


    /**
     * Set up the screen, capture all the elements, initialize the Presenter with the ID of the
     * current player, and update the screen with the first question.
     *
     * @param savedInstanceState The saved state of the application.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);

        Intent intent = getIntent();
        String playerID = intent.getStringExtra(LoginActivity.PLAYER_ID_KEY);
        ArrayList<QuestionSetBuilder> builderList =
                (ArrayList<QuestionSetBuilder>) intent.getSerializableExtra("builderList");
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        question = findViewById(R.id.question);
        score = findViewById(R.id.current_score);
        questionsRemaining = findViewById(R.id.questionsRemaining);

        triviaGamePresenter = new TriviaGamePresenter(this, playerID, builderList);
        triviaGamePresenter.updateView();
    }


    /**
     * Updates the screen after a question is answered.
     * @param answerClicked The answerClicked that was clicked.
     */
    public void onAnswer(View answerClicked) {

        String answer = ((Button) answerClicked).getText().toString();
        triviaGamePresenter.answerQuestion(answer);
        triviaGamePresenter.updateView();
    }


    /**
     * Head to the score screen.
     * @param scoreMessage The message that will appear on the score screen.
     */
    public void goToScoreScreen(String scoreMessage) {
        Intent intent = new Intent(this, ScoreScreenActivity.class);
        intent.putExtra(ScoreScreenActivity.SCORE_KEY, scoreMessage);
        startActivity(intent);
    }


    /**
     * Shows a toast pop-up on-screen.
     *
     * @param message The message to be shown.
     */
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // Start of Setters
    @Override
    public void setQuestion(String newText) {
        this.question.setText(newText);
    }

    @Override
    public void setAnswer1(String newText) {
        this.answer1.setText(newText);
    }

    @Override
    public void setAnswer2(String newText) {
        this.answer2.setText(newText);
    }

    @Override
    public void setAnswer3(String newText) {
        this.answer3.setText(newText);
    }

    @Override
    public void setAnswer4(String newText) {
        this.answer4.setText(newText);
    }

    @Override
    public void setScore(String newText) {
        this.score.setText(newText);
    }

    @Override
    public void setQuestionsRemaining(String newText) {
        this.questionsRemaining.setText(newText);
    }
    // End of Setters

}