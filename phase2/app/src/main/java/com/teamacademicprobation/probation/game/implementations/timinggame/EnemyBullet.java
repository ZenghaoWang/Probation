package com.teamacademicprobation.probation.game.implementations.timinggame;

import com.teamacademicprobation.probation.game.implementations.timinggame.Bullet;

class EnemyBullet extends Bullet {
    EnemyBullet(int screenWidth, int screenHeight, TimingGameStyle gameStyle) {
        super(screenHeight, gameStyle);
        this.setX(Math.toIntExact(Math.round(screenWidth * (EnemyShip.WIDTHRATIO))));
        this.setBulletVelocity(Math.toIntExact(Math.round(screenWidth*(PlayerShip.WIDTHRATIO - EnemyShip.WIDTHRATIO)/Bullet.FRAMES)));
    }

}
