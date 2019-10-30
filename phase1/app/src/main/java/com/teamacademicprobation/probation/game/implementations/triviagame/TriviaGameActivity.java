package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.ui.ScoreScreenActivity;
import com.teamacademicprobation.probation.game.TriviaView;

//TODO: Documentation
public class TriviaGameActivity extends AppCompatActivity implements TriviaView {
    private TriviaGame triviaGame;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private TextView question;

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

        triviaGame = new TriviaGame(this);
        updateQuestion();
    }

    public void updateQuestion() {
        triviaGame.updateQuestion();
    }

    /**
     * Evaluate whether the answerClicked is correct and update the score accordingly,
     * then grab the next question.
     *
     * @param answerClicked The answerClicked that was clicked.
     */
    public void onAnswer(View answerClicked) {

        //TODO: CaSTiNG iS BAd
        String answer = ((Button) answerClicked).getText().toString();
        triviaGame.answerQuestion(answer);
        updateScore();
        updateQuestion();
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
                + numQuestionsAnsweredCorrectly + " out of " + numQuestionsAnswered + "!";
        intent.putExtra(SCORE, scoreMessage);
        startActivity(intent);
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
        }
    }


}