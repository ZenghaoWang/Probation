package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

import android.graphics.Paint;
import android.graphics.Rect;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.timinggame.drawers.HealthBarDrawer;

import java.util.ArrayList;
import java.util.List;

/**
 * A healthbar for a ship.
 */
class HealthBar implements Drawable {

    private int maxHealth;
    /**
     * The current health.
     */
    private int health;
    /**
     * The width of the health bar.
     */
    private int width;
    /**
     * The height of the health bar.
     */
    private int height;
    /**
     * The distance from the left of the bar to the left of the screen.
     */
    private int widthMargins;
    /**
     * The distance from the top of the bar to the top of the screen.
     */
    private int heightMargins;
    /**
     * The style of the frame of the health bar.
     */
    private Paint framePaint;
    /**
     * The style of the bar of the health bar.
     */
    private Paint barPaint;

    /**
     * @param width         The width of the health bar.
     * @param height        The height of the health bar.
     * @param widthMargins  The distance from the left of the bar to the left of the screen.
     * @param heightMargins The distance from the top of the bar to the top of the screen.
     * @param gameStyle     The style of the game.
     */
    HealthBar(int width, int height, int widthMargins, int heightMargins, TimingGameStyle gameStyle) {
        this.width = width;
        this.height = height;
        this.widthMargins = widthMargins;
        this.heightMargins = heightMargins;
        generatePaint(gameStyle);
    }

    /**
     * Generates the style of the health ar according to game style.
     *
     * @param gameStyle The style of the game.
     */
    private void generatePaint(TimingGameStyle gameStyle) {
        this.framePaint = new Paint();
        this.barPaint = new Paint();
        this.framePaint.setColor(gameStyle.getHealthFrameColor());
        this.framePaint.setStyle(Paint.Style.STROKE);
        this.framePaint.setStrokeWidth(8);
        this.barPaint.setColor(gameStyle.getHealthBarColor());
        this.barPaint.setStyle(Paint.Style.FILL);
    }

    int getcurrHealth() {
        return health;
    }

    /**
     * Reduces the health by damage. If the resulting health is below 0, set it to 0.
     *
     * @param damage The damage taken.
     */
    void takeDamage(int damage) {
        this.health = (this.health - damage >= 0) ? (this.health - damage) : 0;
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        AndroidDrawer drawer = new HealthBarDrawer(getFrameRect(), getBarRect(), framePaint, barPaint);
        drawers.add(drawer);
        return drawers;
    }

    /**
     * Returns a rect representation of the health bar.
     *
     * @return Rect
     */
    private Rect getBarRect() {
        return new Rect(
                widthMargins,
                heightMargins,
                widthMargins + (width * health / maxHealth),
                heightMargins + height);
    }

    /**
     * Returns a rect representation of the frame of the bar.
     *
     * @return Rect
     */
    private Rect getFrameRect() {

        return new Rect(widthMargins, heightMargins, widthMargins + width, heightMargins + height);
    }

    int getMaxHealth() {
        return this.maxHealth;
    }

    void setMaxHealth(int health) {
        this.maxHealth = health;
        this.health = health;
    }

    void setCurrHealth(int currHealth) {
        this.health = currHealth;
    }
}
