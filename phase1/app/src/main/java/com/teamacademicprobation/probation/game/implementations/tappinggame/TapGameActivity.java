package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class TapGameActivity extends AppCompatActivity {
  // Code template from
  // https://www.simplifiedcoding.net/android-game-development-tutorial-1/#Android-Game-Development-with-Unity
  private TapGameView tapGameView;

  protected void onCreate(Bundle savedInstanceState) {
    tapGameView = new TapGameView(this.getApplicationContext());
    super.onCreate(savedInstanceState);
    setContentView(tapGameView);
    Thread thread = new Thread(tapGameView);
    thread.start();
  }

  @Override
  protected void onPause() {
    super.onPause();
    tapGameView.pause();
  }

  // running the game when activity is resumed
  @Override
  protected void onResume() {
    super.onResume();
    tapGameView.resume();
  }
}
