package com.teamacademicprobation.probation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.player.Player;
import com.teamacademicprobation.probation.ui.login.LoginActivity;

public class HighScoresActivity extends AppCompatActivity {

  private Player player;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_high_scores);

    String playerId = getIntent().getExtras().getString(LoginActivity.PLAYER_ID_KEY);

    String[] arr = {"Test"};

    ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
    TextView label = findViewById(R.id.txtPlayerStatsTitle);
    ListView list = findViewById(R.id.statsListView);
    list.setAdapter(adapter);
  }

  public void returnToHomeScreen(View view) {
    startActivity(new Intent(this, MainActivity.class));
  }
}
