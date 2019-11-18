package com.teamacademicprobation.probation.game.implementations.timinggame;

class EnemyShip extends Ship{


    static final double WIDTHRATIO = 0.8;
    private int health;


    EnemyShip(int screenWidth, int screenHeight) {
        super(screenWidth, screenHeight, WIDTHRATIO);
    }

    EnemyShip(int screenWidth, int screenHeight, int size) {
        super(screenWidth, screenHeight, WIDTHRATIO, size);
    }

    void setHealth(int health){
        this.health = health;
    }

    int getHealth(){
        return this.health;
    }

    void takeDamage(){this.health -= 1;}

}
