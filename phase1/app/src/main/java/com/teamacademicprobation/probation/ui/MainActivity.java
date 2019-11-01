package com.teamacademicprobation.probation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.game.implementations.tappinggame.TapGameActivity;
import com.teamacademicprobation.probation.game.implementations.timinggame.TimingGameActivity;
import com.teamacademicprobation.probation.game.implementations.triviagame.TriviaGameActivity;

public class MainActivity extends AppCompatActivity {

  private Button trivia;
  private Button timing;
  private Button tapping;
  private Button bestgames;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    trivia = findViewById(R.id.btnTrivia);
    timing = findViewById(R.id.btnTiming);
    tapping = findViewById(R.id.btnTapping);
    bestgames = findViewById(R.id.bestgames);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void startGame(View v) {
    Class<?> activityClass = null;
    switch (v.getId()) {
      case R.id.btnTapping:
        activityClass = TapGameActivity.class;
        break;
      case R.id.btnTiming:
        activityClass = TimingGameActivity.class;
        break;
      case R.id.btnTrivia:
        activityClass = TriviaGameActivity.class;
    }
    if (v.getId() == R.id.btnTapping) {
      activityClass = TapGameActivity.class;
    }
    Intent intent = new Intent(this, activityClass);
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
    startActivity(intent);
  }

  public void enterScoreScreen(View v) {
    startActivity(new Intent(this, HighScoresActivity.class));
  }
}
