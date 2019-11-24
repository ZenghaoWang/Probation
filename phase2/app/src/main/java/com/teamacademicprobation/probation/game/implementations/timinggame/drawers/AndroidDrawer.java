package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;


import android.graphics.Canvas;

/**
 * An interface that defines the methods that drawer classes that draw to an android
 * canvas have to implement. All of the variables that define the object to be drawn is decided
 * by the class that instantiates a drawer, and not by the drawer itself.
 * <p>
 * Not to be confused with a drawer that stores items.
 * <p>
 * Classes that implement drawer are associated with their respective classes.
 */
public interface AndroidDrawer {

    /**
     * Draws an object onto the canvas.
     *
     * @param canvas The canvas onto which to draw on.
     */
    void draw(Canvas canvas);
}
