package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.timinggame.drawers.ShipDrawer;

import java.util.ArrayList;
import java.util.List;

/**
 * A space ship.
 */
class Ship implements Drawable {

    /**
     * The image of the ship. The bitmap must be a square.
     */
    private Bitmap ship;
    /**
     * The explosion effect of the ship, to be drawn when the ship is destroyed. The bitmap
     * must be a square.
     */
    private Bitmap explosion;

    /**
     * The x coordinate of the left most of the ship.
     */
    private int x;
    /**
     * The y coordinate of the top most of the ship.
     */
    private int y;
    /**
     * The size of the ship.
     */
    private int size;

    /**
     * The health of the ship.
     */
    private HealthBar health;
    /**
     * Represents if the ship has been destroyed or not.
     */
    private boolean destroyed;

    /**
     * The style of how to draw the image of the ship.
     */
    private Paint paint;

    private int damage;


    /**
     * Initializes a new ship with the default size.
     *
     * @param screenWidth  The width of the screen, in pixels.
     * @param screenHeight The height of the screen, in pixels.
     * @param widthRatio   The ratio of the location of the ship in relation to the width of the screen.
     */
    Ship(int screenWidth, int screenHeight, double widthRatio) {
        this(screenWidth, screenHeight, widthRatio, 128);
    }

    /**
     * Initializes a new ship with the specified size.
     *
     * @param screenWidth  The width of the screen, in pixels.
     * @param screenHeight The height of the screen, in pixels.
     * @param widthRatio   The ratio of the location of the ship in relation to the width of the screen.
     * @param size         The size of the ship, in pixels.
     */
    Ship(int screenWidth, int screenHeight, double widthRatio, int size) {
        this.size = size;
        this.x = Math.toIntExact(Math.round(screenWidth * widthRatio) - size / 2);
        this.y = Math.toIntExact(Math.round(screenHeight * 0.25) - size / 2);
        this.destroyed = false;

        this.paint = new Paint();
        paint.setAntiAlias(false);
        paint.setFilterBitmap(false);

        this.damage = 1;

    }


    /**
     * Rotates and scales the image of the ship.
     *
     * @param ship  The image of the ship.
     * @param angle the angle the ship should be rotated.
     * @return The new ship image scaled and rotated properly.
     */
    private Bitmap rotateAndScale(Bitmap ship, int angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap scaled = Bitmap.createScaledBitmap(ship, size, size, false);
        return Bitmap.createBitmap(scaled, 0, 0, scaled.getWidth(), scaled.getHeight(), matrix, false);
    }

    /**
     * Sets the explosion effect of the ship.
     *
     * @param explosion The image of the explosion.
     */
    void setExplosion(Bitmap explosion) {
        this.explosion = explosion;
    }

    // ===== SETTERS/GETTERS =====

    /**
     * Sets the image of the ship.
     *
     * @param ship  The image of the ship.
     * @param angle The angle the ship should be rotated.
     */
    void setShip(Bitmap ship, int angle) {
        this.ship = rotateAndScale(ship, angle);
    }

    void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    boolean isDestroyed() {
        return this.destroyed;
    }

    int getHealth(){return health.getcurrHealth();}

    void setHealth(int health, TimingGameStyle gameStyle){
        this.health = new HealthBar(size, 20, x, y+size+40, gameStyle);
        this.health.setMaxHealth(health);
    }

    void setHealth(int health){
        this.health.setMaxHealth(health);
    }

    void takeDamage(int damage){this.health.takeDamage(damage);}

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        AndroidDrawer shipDrawer;
        if (!this.destroyed) {
            shipDrawer = new ShipDrawer(ship, x, y, paint);
        } else {
            shipDrawer = new ShipDrawer(explosion, x, y, paint);
        }
        drawers.add(shipDrawer);
        drawers.addAll(health.getDrawers());
        return drawers;

    }

    void increaseDamage(int damage) {
        this.damage += damage;
    }

    int getDamage(){
        return this.damage;
    }

    int getMaxHealth() {
        return this.health.getMaxHealth();
    }

    // ====== END OF SETTERS/GETTERS =====
}
