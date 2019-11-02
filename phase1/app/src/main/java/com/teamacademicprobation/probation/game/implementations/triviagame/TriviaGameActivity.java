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

//TODO: Documentation
//MVP structure from https://github.com/antoniolg/androidmvp
public class TriviaGameActivity extends AppCompatActivity implements TriviaView {
    private static TriviaGamePresenter triviaGamePresenter;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private TextView question;
    private TextView score;


    /**
     * 1. Set up the layout of the screen
     * 2. Set the local variables to the corresponding UI elements
     * 3. Construct a new TriviaGamePresenter instance
     * 4. Updates the UI with the first question and answers.
     *
     * @param savedInstanceState The saved state of the application.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);

        Intent intent = getIntent();
        String playerID = intent.getStringExtra(LoginActivity.PLAYER_ID_KEY);

        // Update UI with 1st question
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        question = findViewById(R.id.question);
        score = findViewById(R.id.current_score);

        triviaGamePresenter = new TriviaGamePresenter(this, playerID);
        triviaGamePresenter.updateView();
    }


    /**
     * Evaluate whether the answerClicked is correct and update the score accordingly,
     * then grab the next question.
     *
     * @param answerClicked The answerClicked that was clicked.
     */
    public void onAnswer(View answerClicked) {

        String answer = ((Button) answerClicked).getText().toString();
        triviaGamePresenter.answerQuestion(answer);
        triviaGamePresenter.updateView();
    }


    /**
     * When all questions have been answered, head to the results screen.
     * The intent sent to the results screen contains the message to be displayed.
     * @param scoreMessage The message that will appear on the score screen
     */
    public void goToScoreScreen(String scoreMessage) {
        Intent intent = new Intent(this, ScoreScreenActivity.class);
        intent.putExtra("score", scoreMessage);
        startActivity(intent);
    }


    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

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
}