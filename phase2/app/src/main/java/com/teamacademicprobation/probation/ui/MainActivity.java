package com.teamacademicprobation.probation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.game.implementations.tappinggame.TapGameActivity;
import com.teamacademicprobation.probation.game.implementations.timinggame.TimingGameActivity;
import com.teamacademicprobation.probation.game.implementations.triviagame.TriviaGameSelectionScreenActivity;
import com.teamacademicprobation.probation.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView welcomeMessage = findViewById(R.id.txt_welcome_player);
    welcomeMessage.setText(String.format("Welcome %s", getUsername()));

  }



  public void onClick(View v) {
    Class<?> activityClass = null;
    switch (v.getId()) {
      case R.id.btnTapping:
        activityClass = TapGameActivity.class;
        break;
      case R.id.btnTiming:
        activityClass = TimingGameActivity.class;
        break;
      case R.id.btnTrivia:
        activityClass = TriviaGameSelectionScreenActivity.class;
        break;
        // Go to high score screen
      case R.id.bestgames:
        activityClass = HighScoresActivity.class;
    }

    Intent intent = new Intent(this, activityClass);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
    intent.putExtra(LoginActivity.PLAYER_ID_KEY, getPlayerID());
    startActivity(intent);
  }

  private String getPlayerID() {
    Intent contextIntent = getIntent();
    return contextIntent.getStringExtra(LoginActivity.PLAYER_ID_KEY);
  }

  private String getUsername() {
    Intent contextIntent = getIntent();
    return contextIntent.getStringExtra(LoginActivity.PLAYER_USERNAME_KEY);
  }
}
