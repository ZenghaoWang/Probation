package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

/**
 * A zone that has bonus features, but is smaller.
 */
class BonusZone extends TargetZone {

    /**
     * Describes if the bonus zone is visible in the game or not.
     */
    private boolean visible;

    /**
     * Initializes a new Bonus Zone.
     *
     * @param meterBox  The meter that this bonus zone is in.
     * @param gameStyle The style of the game.
     */
    BonusZone(Meter meterBox, TimingGameStyle gameStyle) {
        super(meterBox, 0.1, gameStyle);
        this.getPaint().setColor(gameStyle.getBonusZoneColor());
        this.visible = false;
    }

    // ====== SETTERS / GETTERS =======
    boolean isVisible() {
        return visible;
    }

    void setVisible(boolean visible) {
        this.visible = visible;
    }

    // ======= END OF SETTERS/GETTERS =======
}
