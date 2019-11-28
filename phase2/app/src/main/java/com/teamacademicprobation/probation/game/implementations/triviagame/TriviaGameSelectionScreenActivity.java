package com.teamacademicprobation.probation.game.implementations.triviagame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.teamacademicprobation.probation.R;

import java.util.ArrayList;

public class TriviaGameSelectionScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_game_selection_screen);
    }

    public void onClick(View view) {
        ArrayList<QuestionSetBuilder> builderList = new ArrayList<>();
        switch (view.getId()) {
            case R.id.all_questions:
                builderList.add(new DefaultQuestionSetBuilder());


                Intent intent = getIntent();
                intent.putExtra("builderList", builderList);
                intent.setClass(this, TriviaGameActivity.class);
                startActivity(intent);
        }

    }

}
