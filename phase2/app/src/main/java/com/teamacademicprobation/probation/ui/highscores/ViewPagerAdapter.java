package com.teamacademicprobation.probation.ui.highscores;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.teamacademicprobation.probation.ui.ScoreScreenActivity;

public class ViewPagerAdapter extends FragmentPagerAdapter {
	
	private String playerID;
	
	public ViewPagerAdapter(@NonNull FragmentManager fm, String playerID) {
		super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
		this.playerID = playerID;
	}
	
	@NonNull
	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		if (position == 0) {
			fragment = new HighScoresFragment();
		} else if (position == 1) {
//			fragment = new HighScoresFragment();
			fragment = new TotalScoresFragment();
		}
		Bundle bundle = new Bundle();
		bundle.putString(ScoreScreenActivity.PLAYERID_KEY, playerID);
		fragment.setArguments(bundle);
		return fragment;
	}
	
	@Override
	public int getCount() {
		return 2;
	}
	
	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		if (position == 0) {
			return "High Scores";
		} else if (position == 1) {
			return "Total Scores";
		} else {
			return "";
		}
	}
}
