package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

import android.graphics.Color;

/**
 * A class that determines the style of the game. It describes the different colors for each
 * component of the game.
 */
class TimingGameStyle {

    private int meterColor;
    private int bonusZoneColor;
    private int cursorColor;
    private int targetZoneColor;
    private int bulletColor;

    /**
     * Initializes a new TimingGameStyle based on the preferences.
     *
     * @param style The style to use.
     */
    TimingGameStyle(TimingGameStyles style) {
        switch (style) {
            case STYLE1:
                meterColor = Color.rgb(87, 255, 241);
                cursorColor = Color.rgb(87, 255, 241);
                targetZoneColor = Color.rgb(87, 255, 241);
                bonusZoneColor = Color.rgb(255, 227, 115);
                bulletColor = Color.rgb(87, 255, 241);
                break;
            case STYLE2:
                meterColor = Color.rgb(255, 31, 31);
                cursorColor = Color.rgb(255, 31, 31);
                targetZoneColor = Color.rgb(255, 31, 31);

                bonusZoneColor = Color.rgb(255, 227, 115);

                bulletColor = Color.rgb(255, 31, 31);
                break;
            case STYLE3:
                meterColor = Color.rgb(11, 222, 0);
                cursorColor = Color.rgb(11, 222, 0);
                targetZoneColor = Color.rgb(11, 222, 0);
                bonusZoneColor = Color.rgb(255, 227, 115);

                bulletColor = Color.rgb(11, 222, 0);
                break;
            default:
                meterColor = Color.rgb(255, 255, 255);
                bonusZoneColor = Color.rgb(255, 255, 255);
        }
    }

    // ====== GETTERS ======
    int getBonusZoneColor() {
        return bonusZoneColor;
    }

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
}
