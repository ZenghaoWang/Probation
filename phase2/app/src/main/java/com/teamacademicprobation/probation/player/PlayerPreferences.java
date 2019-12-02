package com.teamacademicprobation.probation.player;

import java.util.HashMap;
import java.util.Map;

/**
 * The preferences a player. These include options such as difficulty, current avatar, color, etc.
 */
public class PlayerPreferences {

    private Map<String, String> preferenceMap;
    private String[] preferences = {
            "Difficulty", "Theme"
    }; // TODO: don't have this hardcoded choose a different solution

    public PlayerPreferences() {
        preferenceMap = new HashMap<>();
        for (String preferenceKey : this.preferences) {
            this.preferenceMap.put(preferenceKey, "default");
        }
    }

    // ==== SETTER METHODS

    public String getPreference(String preferenceKey) {
        return this.preferenceMap.get(preferenceKey);
    }

    // ==== GETTER METHODS

    public Map<String, String> getPreferences() {
        return this.preferenceMap;
    }

    public void setPreferences(Map<String, String> playerPreferences) {
        this.preferenceMap = playerPreferences;
    }

    // ==== UPDATE METHODS

    public void updatePreferences(String preferenceKey, String preferenceSetting) {
        this.preferenceMap.put(preferenceKey, preferenceSetting);
    }

    public void updatePreferences(Map<String, String> newPreferences) {
        for (String preferenceKey : newPreferences.keySet()) {
            this.preferenceMap.put(preferenceKey, newPreferences.get(preferenceKey));
        }
    }
}
