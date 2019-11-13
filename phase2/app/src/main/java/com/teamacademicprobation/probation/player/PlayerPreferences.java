package com.teamacademicprobation.probation.player;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The preferences a player. These include options such as difficulty, current avatar, color, etc.
 */
public class PlayerPreferences {

  private Map<String, String> preferenceMap;
  private String[] preferenceKeys = {"Difficulty", "Color", "Avatar"};

  public PlayerPreferences() {
    this.preferenceMap = buildPreferenceMap();
  }

  private Map<String, String> buildPreferenceMap() {
    Map<String, String> result = new HashMap<>();
    for (String preferenceKey : preferenceKeys) {
      result.put(preferenceKey, "");
    }
    return result;
  }

  public String getPreference(String preferenceKey) {
    return this.preferenceMap.get(preferenceKey);
  }

  public void updatePreference(String preferenceKey, String preference) {
    this.preferenceMap.put(preferenceKey, preference);
  }

  public Map<String, String> getPreferences() {
    return this.preferenceMap;
  }
}
