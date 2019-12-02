package com.teamacademicprobation.probation.ui.highscores;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.player.PlayerManager;
import com.teamacademicprobation.probation.player.PlayerTotalStatsAccess;
import com.teamacademicprobation.probation.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HighScoresActivity extends AppCompatActivity {
	private String playerID;
	private HighScoresModel model;
	private Toolbar toolbar;
	private ViewPager viewPager;
	private TabLayout tabLayout;
	
	/**
	 * Generate strings and display them on the ListView.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);
		
		playerID = getIntent().getStringExtra(LoginActivity.PLAYER_ID_KEY);
		model = new HighScoresModel(playerID);
		
		toolbar = findViewById(R.id.score_toolbar);
		setSupportActionBar(toolbar);
		
		viewPager = findViewById(R.id.pager);
		FragmentPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), playerID);
		viewPager.setAdapter(adapter);
		
		tabLayout = findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewPager);
		
		
//		ListView scoreList = findViewById(R.id.statsListView);
//		ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, model.getScoreStrings());
//		scoreList.setAdapter(adapter);
	}
	
	
	/**
	 * Returns to the home screen / main activity.
	 *
	 * @param view The button.
	 */
	public void returnToHomeScreen(View view) {
		finish();
	}
}
