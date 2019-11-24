package com.teamacademicprobation.probation.game.implementations.timinggame.drawers;

import java.util.List;

/**
 * An interface that every class that wishes to be drawn onto a canvas must implement. This
 * interface and the methods in this interface are important to fully separate the model with the
 * front end.
 */
public interface Drawable {

    /**
     * Returns a list of objects that implement the AndroidDrawer interface. This list may be empty.
     *
     * @return A list of drawer objects.
     */
    List<AndroidDrawer> getDrawers();
}
