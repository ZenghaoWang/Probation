package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel;

import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;

import java.util.List;

/**
 * Abstract class for all Touchable Objects in the tapping game. Implements Drawable.
 */
public abstract class TouchableObject implements Drawable {
    /**
     * The x-coordinate of the TouchableObject.
     */
    private int x;
    /**
     * The y-coordinate of the TouchableObject.
     */
    private int y;
    /**
     * The size of the TouchableObject for rescaling.
     */
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
     * Sets the x-coordinate of the touchable object.
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * @return the y-coordinate of the touchable object
     */
    public int getY() {
        return this.y;
    }

    /**
     * @return the size of the touchable object.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of the touchable object.
     */
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public abstract List<AndroidDrawer> getDrawers();
}
