package com.teamacademicprobation.probation.ui.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.player.PlayerManager;
import com.teamacademicprobation.probation.ui.MainActivity;

import java.io.File;

public class LoginActivity extends AppCompatActivity implements LoginView {
  private EditText username;
  private EditText password;
  private TextView error;
  public final static String FILE_PATH = "PlayerData.txt";
  public final static String PLAYER_ID_KEY = "PlayerID";
  private LoginPresenter loginPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    loginPresenter = new LoginPresenter(this, new File(getFilesDir(), FILE_PATH));

    username = findViewById(R.id.editUsername);
    password = findViewById(R.id.editPassword);
    error = findViewById(R.id.txtErrorMsg);
  }

  public void loginClick(View view) {

    loginPresenter.login(username.getText().toString(), password.getText().toString());
  }

  public void registerClick(View view) {

    loginPresenter.register(username.getText().toString(), password.getText().toString());
  }

  @Override
  public void goToMainView(String playerID){
    Intent main = new Intent(this, MainActivity.class);
    main.putExtra(PLAYER_ID_KEY, playerID);
    startActivity(main);
  }


  @Override
  public void setErrorMessage(String errorMessage){
    error.setText(errorMessage);
    error.setVisibility(View.VISIBLE);

  }
}
