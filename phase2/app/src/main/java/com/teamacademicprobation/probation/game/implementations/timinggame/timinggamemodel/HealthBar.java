package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

import android.graphics.Paint;
import android.graphics.Rect;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.timinggame.drawers.HealthBarDrawer;

import java.util.ArrayList;
import java.util.List;

class HealthBar implements Drawable {

    private int maxHealth;
    private int health;
    private int width;
    private int height;
    private int widthMargins;
    private int heightMargins;
    private Paint framePaint;
    private Paint barPaint;

    HealthBar(int width, int height, int widthMargins, int heightMargins, TimingGameStyle gameStyle) {
        this.width = width;
        this.height = height;
        this.widthMargins = widthMargins;
        this.heightMargins = heightMargins;
        generatePaint(gameStyle);
    }

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

    void setMaxHealth(int health) {
        this.maxHealth = health;
        this.health = health;
    }

    void takeDamage(int damage){
        this.health = (this.health - damage >= 0) ? (this.health - damage) : 0;
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        AndroidDrawer drawer = new HealthBarDrawer(getFrameRect(), getBarRect(), framePaint, barPaint);
        drawers.add(drawer);
        return drawers;
    }

    private Rect getBarRect() {
        return new Rect(widthMargins, heightMargins, widthMargins+(width*health/maxHealth), heightMargins + height);

    }

    private Rect getFrameRect() {

        return new Rect(widthMargins, heightMargins, widthMargins+width, heightMargins+height);

    }

    int getMaxHealth() {
        return this.maxHealth;
    }
}
