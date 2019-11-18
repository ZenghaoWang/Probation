package com.teamacademicprobation.probation.game.implementations.timinggame.meter;

import com.teamacademicprobation.probation.game.implementations.timinggame.TimingGameStyle;

class BonusZone extends TargetZone {

    private boolean visible;

    BonusZone(Meter meterBox, TimingGameStyle gameStyle){
        super(meterBox, 0.1, gameStyle);
        this.getPaint().setColor(gameStyle.getBonusZoneColor());
        this.visible = false;
    }

    boolean isVisible(){
        return visible;
    }

    void setVisible(boolean visible){
        this.visible = visible;
    }
}
