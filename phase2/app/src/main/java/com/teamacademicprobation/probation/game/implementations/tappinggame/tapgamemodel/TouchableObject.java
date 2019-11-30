package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;

import java.util.List;

/**
 * Abstract class for all Touchable Objects in the tapping game. Implements Drawable.
 */
public abstract class TouchableObject implements Drawable {
    private int x;
    private int y;
    private int size;

    TouchableObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x-coordinate of the touchable object
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return the y-coordinate of the touchable object
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets the x-coordinate of the touchable object.
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * Sets the size of the touchable object.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the size of the touchable object.
     */
    public int getSize() {
        return size;
    }

    @Override
    public abstract List<AndroidDrawer> getDrawers();
}
