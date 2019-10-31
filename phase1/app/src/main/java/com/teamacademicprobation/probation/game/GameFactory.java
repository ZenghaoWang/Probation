package com.teamacademicprobation.probation.game;

import com.teamacademicprobation.probation.game.implementations.tappinggame.TapGameActivity;
import com.teamacademicprobation.probation.game.implementations.timinggame.TimingGameActivity;
import com.teamacademicprobation.probation.game.implementations.triviagame.TriviaGameActivity;

import java.util.Objects;

public class GameFactory {

    public Object getGame(String gameID) {
        if (Objects.equals(gameID, "tapping")) {
            return new TimingGameActivity();
        } else if (Objects.equals(gameID, "timing")) {
            return new TapGameActivity();

        } else if (Objects.equals(gameID, "trivia")) {
            return new TriviaGameActivity();
        } else {
            return null;
        }
    }
}
