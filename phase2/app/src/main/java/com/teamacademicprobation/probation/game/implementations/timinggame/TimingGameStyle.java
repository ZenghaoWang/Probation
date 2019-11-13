package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.graphics.Color;

class TimingGameStyle {

    private int meterColor;
    private int bonusZoneColor;
    private int cursorColor;
    private int targetZoneColor;

    TimingGameStyle(TimingGameStyles style){
        switch(style){
            case STYLE1:
                meterColor = Color.rgb(87, 255, 241);
                cursorColor = Color.rgb(87, 255, 241);
                targetZoneColor = Color.rgb(87, 255, 241);
                bonusZoneColor = Color.rgb(255, 227, 115);
                break;
            case STYLE2:
                meterColor = Color.rgb(255, 31, 31);
                cursorColor = Color.rgb(255, 31, 31);
                targetZoneColor = Color.rgb(255, 31, 31);

                bonusZoneColor = Color.rgb(255, 227, 115);
                break;
            case STYLE3:
                meterColor = Color.rgb(11, 222, 0);
                cursorColor =  Color.rgb(11, 222, 0);
                targetZoneColor =  Color.rgb(11, 222, 0);
                bonusZoneColor = Color.rgb(255, 227, 115);
                break;
            default:
                meterColor = Color.rgb(255,255,255);
                bonusZoneColor = Color.rgb(255,255,255);
        }
    }

    int getBonusZoneColor() {
        return bonusZoneColor;
    }

    int getMeterColor() {
        return meterColor;
    }

    public int getCursorColor() {
        return cursorColor;
    }

    public int getTargetZoneColor() {
        return targetZoneColor;
    }
}
