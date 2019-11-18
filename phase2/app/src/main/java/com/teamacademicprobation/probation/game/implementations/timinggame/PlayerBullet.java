package com.teamacademicprobation.probation.game.implementations.timinggame;


class PlayerBullet extends Bullet {
    PlayerBullet(int screenWidth, int screenHeight, TimingGameStyle gameStyle) {
        super(screenHeight, gameStyle);
        this.setX(Math.toIntExact(Math.round(screenWidth * (PlayerShip.WIDTHRATIO))));
        this.setBulletVelocity(Math.toIntExact(Math.round(screenWidth*(EnemyShip.WIDTHRATIO - PlayerShip.WIDTHRATIO)/Bullet.FRAMES)));
    }
}
