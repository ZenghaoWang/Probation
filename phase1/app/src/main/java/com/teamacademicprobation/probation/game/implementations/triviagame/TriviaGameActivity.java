package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.game.TriviaView;
import com.teamacademicprobation.probation.ui.ScoreScreenActivity;

//TODO: Documentation
public class TriviaGameActivity extends AppCompatActivity implements TriviaView {
    private static TriviaGame triviaGame;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private TextView question;
    private TextView score;

    public static final String SCORE =
            "com.teamacademicprobation.probation.game.implementations.triviagame.SCORE";


    private int numQuestionsAnswered;
    private int numQuestionsAnsweredCorrectly;


    /**
     * 1. Set up the layout of the screen
     * 2. Set the local variables to the corresponding UI elements
     * 3. Construct a new TriviaGame instance
     * 4. Updates the UI with the first question and answers.
     *
     * @param savedInstanceState The saved state of the application.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game);

        // Update UI with 1st question
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        question = findViewById(R.id.question);
        score = findViewById(R.id.current_score);

        triviaGame = new TriviaGame(this);
        updateView();
    }

    /**
     * Update all elements on the screen.
     */
    public void updateView() {
        triviaGame.updateView();
    }

    /**
     * Evaluate whether the answerClicked is correct and update the score accordingly,
     * then grab the next question.
     *
     * @param answerClicked The answerClicked that was clicked.
     */
    public void onAnswer(View answerClicked) {

        String answer = ((Button) answerClicked).getText().toString();
        triviaGame.answerQuestion(answer);
        updateScore();
        updateView();
    }

    public void updateScore() {
        numQuestionsAnswered = triviaGame.getNumQuestionsAnswered();
        numQuestionsAnsweredCorrectly = triviaGame.getNumQuestionsAnsweredCorrectly();
    }

    /**
     * When all questions have been answered, head to the results screen.
     * The intent sent to the results screen contains the message to be displayed.
     */
    public void goToScoreScreen() {
        Intent intent = new Intent(this, ScoreScreenActivity.class);
        String scoreMessage = "You answered "
                + numQuestionsAnsweredCorrectly + " out of " + numQuestionsAnswered + " correctly!";
        intent.putExtra(SCORE, scoreMessage);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param element The question or answer on the screen to be set
     * @param newText The text to set the chosen element to
     */
    @Override
    public void setElement(TriviaView.Element element, String newText) {
        switch (element) {
            case QUESTION:
                this.question.setText(newText);
            case ANSWER1:
                this.answer1.setText(newText);
            case ANSWER2:
                this.answer2.setText(newText);
            case ANSWER3:
                this.answer3.setText(newText);
            case ANSWER4:
                this.answer4.setText(newText);
            case SCOREBOARD:
                this.score.setText(newText);
        }
    }


}