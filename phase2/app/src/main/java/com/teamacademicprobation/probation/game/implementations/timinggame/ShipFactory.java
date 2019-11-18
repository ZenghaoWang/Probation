package com.teamacademicprobation.probation.game.implementations.timinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.teamacademicprobation.probation.R;

import java.util.Random;

class ShipFactory {


    private Context context;

    ShipFactory(Context context){
        this.context = context;
    }

    EnemyShip createEnemyShip(int screenWidth, int screenHeight,
                              int level){
        EnemyShip result;
        if(level < TimingGame.BOSS_LEVEL){
            result = createBasicEnemy(screenWidth, screenHeight, level);
        }
        else{
            result = createBoss(screenWidth, screenHeight);
        }
        return result;
    }

    PlayerShip createPlayerShip(int screenWidth, int screenHeight){
        PlayerShip playerShip = new PlayerShip(screenWidth, screenHeight);
        Bitmap ship = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_ship);
        Bitmap explosion = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion);
        playerShip.setExplosion(explosion);
        playerShip.setShip(ship, 0);
        return playerShip;
    }

    private  EnemyShip createBoss(int screenWidth, int screenHeight) {
        EnemyShip enemyShip = new EnemyShip(screenWidth, screenHeight, 236);
        enemyShip.setHealth(7);
        Bitmap ship = BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_ship);
        Bitmap explosion = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion);
        enemyShip.setExplosion(explosion);
        enemyShip.setShip(ship, 270);
        return enemyShip;
    }

    private  EnemyShip createBasicEnemy(int screenWidth, int screenHeight, int level) {
        EnemyShip enemyShip = new EnemyShip(screenWidth, screenHeight);
        Random random = new Random();
        int colorChoice = random.nextInt(4);
        Bitmap ship;
        switch(colorChoice){
            case 1:
                ship = BitmapFactory.decodeResource(context.getResources(), R.drawable.pink_ship);
                break;
            case 2:
                ship = BitmapFactory.decodeResource(context.getResources(), R.drawable.red_ship);
                break;
            case 3:
                ship = BitmapFactory.decodeResource(context.getResources(), R.drawable.yellow_ship);
                break;
            default:
                ship = BitmapFactory.decodeResource(context.getResources(), R.drawable.blue_ship);
        }

        enemyShip.setShip(ship, 180);
        Bitmap explosion = BitmapFactory.decodeResource(context.getResources(), R.drawable.explosion);
        enemyShip.setExplosion(explosion);
        enemyShip.setHealth(level);
        return enemyShip;
    }

    void setContext(Context context){
        this.context = context;
    }
}
