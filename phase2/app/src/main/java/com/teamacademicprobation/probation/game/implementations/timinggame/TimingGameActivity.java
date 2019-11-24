package com.teamacademicprobation.probation.game.implementations.timinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.teamacademicprobation.probation.ui.login.LoginActivity;

/**
 * An activity for the timing game.
 */
public class TimingGameActivity extends AppCompatActivity {

    private TimingGameView timingGameView;

    // Code template from
    // https://www.simplifiedcoding.net/android-game-development-tutorial-1/#Android-Game-Development-with-Unity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This removes the bar at the top and makes it full screen.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow()
                .setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent contextIntent = getIntent();

        timingGameView =
                new TimingGameView(this, contextIntent.getStringExtra(LoginActivity.PLAYER_ID_KEY));
        setContentView(timingGameView);
        Thread thread = new Thread(timingGameView);
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timingGameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timingGameView.resume();
    }
}
