package com.teamacademicprobation.probation.game.implementations.triviagame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;

import java.util.ArrayList;

public class TriviaGameSelectionScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game_selection_screen);
    }

    /**
     * Upon the user selecting a category, initializes the trivia game activity with the appropriate
     * set of questions.
     *
     * @param view the button that was clicked.
     */
    public void onClick(View view) {
        ArrayList<QuestionSetBuilder> builderList = new ArrayList<>();
        switch (view.getId()) {
            case R.id.geography:
                builderList.add(new GeoQuestionSetBuilder());
                break;
            case R.id.stem:
                builderList.add(new STEMQuestionSetBuilder());
                break;
            case R.id.misc:
                builderList.add(new MiscQuestionSetBuilder());
                break;
            case R.id.all_questions:
                builderList.add(new GeoQuestionSetBuilder());
                builderList.add(new STEMQuestionSetBuilder());
                builderList.add(new MiscQuestionSetBuilder());
        }
        Intent intent = getIntent();
        intent.putExtra("builderList", builderList);
        intent.setClass(this, TriviaGameActivity.class);
        startActivity(intent);
    }
}
