package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.content.Intent;
import android.media.MediaPlayer;
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

// MVP structure from https://github.com/antoniolg/androidmvp

/**
 * The front-end for the trivia game.
 */
public class TriviaGameActivity extends AppCompatActivity implements TriviaView {
    private TriviaGamePresenter presenter;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private TextView question;
    private TextView score;
    private TextView questionsRemaining;
    private MediaPlayer incorrectSound;
    private MediaPlayer correctSound;


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

        presenter = new TriviaGamePresenter(this, playerID, builderList);
        presenter.updateView();

        incorrectSound = MediaPlayer.create(this, R.raw.bruh);
        correctSound = MediaPlayer.create(this, R.raw.bruh);


    }

    /**
     * Updates the screen after a question is answered.
     *
     * @param answerClicked The answerClicked that was clicked.
     */
    public void onAnswer(View answerClicked) {

        String answer = ((Button) answerClicked).getText().toString();
        presenter.answerQuestion(answer);
        presenter.updateView();
    }

    /**
     * Head to the score screen.
     *
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

    /**
     * Called when a question is answered.
     * @param isCorrect Determines what kind of noise to play.
     */
    @Override
    public void playSound(Boolean isCorrect) {
        if (isCorrect) {
            correctSound.start();
        } else incorrectSound.start();
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
    
    @Override
    public void disableInput() {
        this.answer1.setEnabled(false);
        this.answer2.setEnabled(false);
        this.answer3.setEnabled(false);
        this.answer4.setEnabled(false);
    }
    // End of Setters

}
