package com.teamacademicprobation.probation.ui.highscores;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
public class ViewPagerAdapter extends FragmentPagerAdapter {
	
	private String playerId;
	
	public ViewPagerAdapter(@NonNull FragmentManager fm, String playerId) {
		super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
		this.playerId = playerId;
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
		bundle.putString("playerid", playerId);
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
