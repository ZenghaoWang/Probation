package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.teamacademicprobation.probation.R;

import java.util.Random;

/**
 * A Factory design pattern that generates new ships.
 */
class ShipFactory {

    /**
     * The resources.
     */
    private Resources resources;

    private TimingGameStyle gameStyle;

    /**
     * Initializes a new ShipFactory.
     *
     * @param resources The resource.
     */
    ShipFactory(Resources resources, TimingGameStyle gameStyle) {
        this.resources = resources;
        this.gameStyle = gameStyle;
    }

    /**
     * Creates an enemy ship based on the level. If the level is equal or greater than the level the
     * boss should be, return a boss enemy ship.
     *
     * @param screenWidth  The width of the screen, in pixels.
     * @param screenHeight The height of the screen, in pixels.
     * @param level        The level of the enemy.
     * @return Enemy ship of appropriate level.
     */
    EnemyShip createEnemyShip(int screenWidth, int screenHeight, int level) {
        EnemyShip result;
        if ((level % TimingGame.BOSS_LEVEL) != 0) {
            result = createBasicEnemy(screenWidth, screenHeight, level);
        } else {
            result = createBoss(screenWidth, screenHeight, level);
        }
        return result;
    }

    /**
     * Creates a new player ship.
     *
     * @param screenWidth  The width of the screen, in pixels.
     * @param screenHeight The height of the screen, in pixels.
     * @return Player ship.
     */
    PlayerShip createPlayerShip(int screenWidth, int screenHeight) {
        PlayerShip playerShip = new PlayerShip(screenWidth, screenHeight);
        Bitmap ship = BitmapFactory.decodeResource(resources, R.drawable.player_ship);
        Bitmap explosion = BitmapFactory.decodeResource(resources, R.drawable.explosion);
        playerShip.setExplosion(explosion);
        playerShip.setShip(ship, 0);
        playerShip.setHealth(1, gameStyle);
        return playerShip;
    }

    /**
     * Creates a new boss enemy ship. A boss enemy ship has more health than a normal enemy, and has a
     * special image.
     *
     * @param screenWidth  The width of the screen, in pixels.
     * @param screenHeight The height of the screen, in pixels.
     * @return Boss type enemy ship
     */
    private EnemyShip createBoss(int screenWidth, int screenHeight, int level) {
        EnemyShip enemyShip = new EnemyShip(screenWidth, screenHeight, 236);
        enemyShip.setHealth(level + 2, gameStyle);
        Bitmap ship = BitmapFactory.decodeResource(resources, R.drawable.boss_ship);
        Bitmap explosion = BitmapFactory.decodeResource(resources, R.drawable.explosion);
        enemyShip.setExplosion(explosion);
        enemyShip.setShip(ship, 270);
        return enemyShip;
    }

    /**
     * Creates a new basic enemy type with a randomized space ship. The enemy ship has the same number
     * of health as the current level.
     *
     * @param screenWidth  The width of the screen, in pixels.
     * @param screenHeight The height of the screen, in pixels.
     * @param level        The level this enemy ship should be.
     * @return Basic type enemy ship with appropriate health.
     */
    private EnemyShip createBasicEnemy(int screenWidth, int screenHeight, int level) {
        EnemyShip enemyShip = new EnemyShip(screenWidth, screenHeight);
        setRandomShip(enemyShip);
        Bitmap explosion = BitmapFactory.decodeResource(resources, R.drawable.explosion);
        enemyShip.setExplosion(explosion);
        enemyShip.setHealth(level, gameStyle);
        return enemyShip;
    }

    /**
     * Sets a random ship for the enmy.
     *
     * @param enemyShip The enemy ship.
     */
    private void setRandomShip(EnemyShip enemyShip) {
        Random random = new Random();
        int colorChoice = random.nextInt(4);
        Bitmap ship;
        switch (colorChoice) {
            case 1:
                ship = BitmapFactory.decodeResource(resources, R.drawable.pink_ship);
                break;
            case 2:
                ship = BitmapFactory.decodeResource(resources, R.drawable.red_ship);
                break;
            case 3:
                ship = BitmapFactory.decodeResource(resources, R.drawable.yellow_ship);
                break;
            default:
                ship = BitmapFactory.decodeResource(resources, R.drawable.blue_ship);
        }

        enemyShip.setShip(ship, 180);
    }

}
