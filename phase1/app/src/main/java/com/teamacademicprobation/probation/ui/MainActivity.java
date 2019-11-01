package com.teamacademicprobation.probation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.game.implementations.tappinggame.TapGameActivity;
import com.teamacademicprobation.probation.game.implementations.timinggame.TimingGameActivity;
import com.teamacademicprobation.probation.game.implementations.triviagame.TriviaGameActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button trivia = findViewById(R.id.trivia);
    Button timing = findViewById(R.id.timing);
    Button tapping = findViewById(R.id.tapping);
    Button bestgames = findViewById(R.id.bestgames);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
          }
        });
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

  public void startTrivia(View v){
    startActivity(new Intent(this, TriviaGameActivity.class));
  }

  public void startTiming(View v){
    startActivity(new Intent(this, TimingGameActivity.class));
  }

  public void startTapping(View v){
    startActivity(new Intent(this, TapGameActivity.class));
  }

  public void enterScoreScreen(View v){
    startActivity(new Intent(this, HighScoresActivity.class));
  }
}
