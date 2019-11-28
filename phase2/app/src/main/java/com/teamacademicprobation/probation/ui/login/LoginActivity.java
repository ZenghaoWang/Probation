package com.teamacademicprobation.probation.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.ui.MainActivity;

import java.io.File;

/**
 * A login activity.
 */
public class LoginActivity extends AppCompatActivity implements LoginView {
    public static final String FILE_PATH = "PlayerData.txt";
    public static final String PLAYER_ID_KEY = "PlayerID";
  private EditText username;
  private EditText password;
  private TextView error;
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

  /**
   * Method that is called when the login button is clicked.
   *
   * @param view
   */
  public void loginClick(View view) {
    loginPresenter.login(username.getText().toString(), password.getText().toString());
  }

  /**
   * Method that is called when the register button is called.
   *
   * @param view
   */
  public void registerClick(View view) {

    loginPresenter.register(username.getText().toString(), password.getText().toString());
  }

  @Override
  public void goToMainView(String playerID) {
    Intent main = new Intent(this, MainActivity.class);
    main.putExtra(PLAYER_ID_KEY, playerID);
    startActivity(main);
  }

  @Override
  public void setErrorMessage(String errorMessage) {
    error.setText(errorMessage);
    error.setVisibility(View.VISIBLE);
  }
}
