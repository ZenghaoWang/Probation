package com.teamacademicprobation.probation.game.implementations.timinggame;

class BonusZone extends TargetZone {

    private boolean visible;

    BonusZone(Meter meterBox, TimingGameStyle gameStyle){
        super(meterBox, 0.05, gameStyle);
        this.getPaint().setColor(gameStyle.getBonusZoneColor());
        this.visible = true;
    }

    boolean isVisible(){
        return visible;
    }

    void setVisible(boolean visible){
        this.visible = visible;
    }
}
