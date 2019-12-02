package com.teamacademicprobation.probation.ui.highscores;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.game.implementations.tappinggame.TapGameModel;
import com.teamacademicprobation.probation.game.implementations.timinggame.TimingGameModel;
import com.teamacademicprobation.probation.game.implementations.triviagame.TriviaGameModel;
import com.teamacademicprobation.probation.ui.ScoreScreenActivity;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HighScoresFragment extends Fragment {
	
	private TextView triviaScore;
	private TextView tapScore;
	private TextView timingScore;
	
	public HighScoresFragment() {
		// Required empty public constructor
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_high_scores, container, false);
		// Inflate the layout for this fragment
		String playerid = getArguments().getString(ScoreScreenActivity.PLAYERID_KEY);
		HighScoresModel model = new HighScoresModel(playerid);
		Map<String, String> scores = model.getBestScores();
		
		tapScore = view.findViewById(R.id.txtTapHighScore);
		triviaScore = view.findViewById(R.id.txtTriviaHighScore);
		timingScore = view.findViewById(R.id.txtTimingHighScore);
		
		triviaScore.setText(scores.containsKey(TriviaGameModel.GAMEID) ? scores.get(TriviaGameModel.GAMEID) : "0");
		tapScore.setText(scores.containsKey(TapGameModel.GAMEID) ? scores.get(TapGameModel.GAMEID) : "0");
		timingScore.setText(scores.containsKey(TimingGameModel.GAMEID) ? scores.get(TimingGameModel.GAMEID) : "0");
		return view;
	}
	
}
