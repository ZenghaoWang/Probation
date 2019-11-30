package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;

import java.util.List;

public abstract class TouchableObject implements Drawable {
    private int x;
    private int y;
    private int size;

    TouchableObject(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    /**
     * @return the x-coordinate of the Mole Object
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return the y-coordinate of the Mole Object
     */
    public int getY() {
        return this.y;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * @return the size of the Mole
     */
    public int getSize() {
        return size;
    }

    @Override
    public abstract List<AndroidDrawer> getDrawers();
}
