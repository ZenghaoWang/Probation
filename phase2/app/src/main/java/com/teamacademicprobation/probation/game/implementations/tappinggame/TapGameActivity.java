package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.ui.login.LoginActivity;

public class TapGameActivity extends AppCompatActivity {
    // Code template from
    // https://www.simplifiedcoding.net/android-game-development-tutorial-1/#Android-Game-Development-with-Unity
    private TapGameView tapGameView;

    protected void onCreate(Bundle savedInstanceState) {
        Intent contextIntent = getIntent();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow()
                .setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tapGameView = new TapGameView(this, contextIntent.getStringExtra(LoginActivity.PLAYER_ID_KEY));
        setContentView(tapGameView);
        super.onCreate(savedInstanceState);
        setContentView(tapGameView);
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
