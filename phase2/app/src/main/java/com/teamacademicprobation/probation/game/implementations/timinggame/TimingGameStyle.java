package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.graphics.Color;

public class TimingGameStyle {

    private int meterColor;
    private int bonusZoneColor;
    private int cursorColor;
    private int targetZoneColor;
    private int bulletColor;

    TimingGameStyle(TimingGameStyles style){
        switch(style){
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
                cursorColor =  Color.rgb(11, 222, 0);
                targetZoneColor =  Color.rgb(11, 222, 0);
                bonusZoneColor = Color.rgb(255, 227, 115);

                bulletColor =  Color.rgb(11, 222, 0);
                break;
            default:
                meterColor = Color.rgb(255,255,255);
                bonusZoneColor = Color.rgb(255,255,255);
        }
    }

    public int getBonusZoneColor() {
        return bonusZoneColor;
    }

    public int getMeterColor() {
        return meterColor;
    }

    public int getCursorColor() {
        return cursorColor;
    }

    public int getTargetZoneColor() {
        return targetZoneColor;
    }

    public int getBulletColor() {
        return this.bulletColor;
    }
}
