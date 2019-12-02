package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

import android.graphics.Color;

/**
 * A class that determines the style of the game. It describes the different colors for each
 * component of the game.
 */
public class TimingGameStyle {

    private static final int STYLE1_ACCENT = Color.rgb(87, 255, 241);
    private static final int STYLE2_ACCENT = Color.rgb(255, 31, 31);
    private static final int STYLE3_ACCENT = Color.rgb(11, 222, 0);
    private int meterColor;
    private int bonusZoneColor;
    private int cursorColor;
    private int targetZoneColor;
    private int bulletColor;
    private int healthBarColor;
    private int healthFrameColor;

    /**
     * Initializes a new TimingGameStyle based on the preferences.
     *
     * @param style The style to use.
     */
    public TimingGameStyle(TimingGameStyles style) {
        switch (style) {
            case STYLE1:
                meterColor = STYLE1_ACCENT;
                cursorColor = STYLE1_ACCENT;
                targetZoneColor = STYLE1_ACCENT;
                bulletColor = STYLE1_ACCENT;
                healthBarColor = STYLE1_ACCENT;
                healthFrameColor = STYLE1_ACCENT;
                break;

            case STYLE2:
                meterColor = STYLE2_ACCENT;
                cursorColor = STYLE2_ACCENT;
                targetZoneColor = STYLE2_ACCENT;
                bulletColor = STYLE2_ACCENT;
                healthBarColor = STYLE2_ACCENT;
                healthFrameColor = STYLE2_ACCENT;
                break;
            case STYLE3:
                meterColor = STYLE3_ACCENT;
                cursorColor = STYLE3_ACCENT;
                targetZoneColor = STYLE3_ACCENT;
                bulletColor = STYLE3_ACCENT;
                healthBarColor = STYLE3_ACCENT;
                healthFrameColor = STYLE3_ACCENT;
                break;
            default:
                System.err.println("Not a style!");
        }
    }

    // ====== GETTERS ======

    int getMeterColor() {
        return meterColor;
    }

    int getCursorColor() {
        return cursorColor;
    }

    int getTargetZoneColor() {
        return targetZoneColor;
    }

    int getBulletColor() {
        return this.bulletColor;
    }

    int getHealthBarColor() {
        return healthBarColor;
    }

    int getHealthFrameColor() {
        return healthFrameColor;
    }
}
