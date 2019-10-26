package com.teamacademicprobation.probation.ui;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.player.DataManager;
import com.teamacademicprobation.probation.player.PlayerManager;

import java.io.File;

public class LoginActivity extends AppCompatActivity {
  EditText username;
  EditText password;
  TextView error;
  final String FILE_PATH = "PlayerData.txt";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    DataManager.setDataFile(new File(FILE_PATH));

    username = findViewById(R.id.editUsername);
    password = findViewById(R.id.editPassword);
    error = findViewById(R.id.txtErrorMsg);
  }

  public void loginClick(View view) {
    if (PlayerManager.login(username.getText().toString(), password.getText().toString())) {
      Intent main = new Intent(this, MainActivity.class);
      startActivity(main);
    } else {
      error.setVisibility(View.VISIBLE);
    }
  }
}
