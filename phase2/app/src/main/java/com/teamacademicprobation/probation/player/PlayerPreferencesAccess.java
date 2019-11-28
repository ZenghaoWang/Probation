package com.teamacademicprobation.probation.player;

import java.util.Map;

public interface PlayerPreferencesAccess {

    void updatePreferences(String playerID, String preferenceKey, String preferenceSetting);

    void updatePreferences(String playerID, Map<String, String> preferences);
}
