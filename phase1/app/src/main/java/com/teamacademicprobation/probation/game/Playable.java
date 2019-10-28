package com.teamacademicprobation.probation.game;

import android.graphics.Canvas;

public interface Playable {

    void update();
    void draw(Canvas canvas);
    void setCompleted();
    boolean isCompleted();
    void updateScore();
}
