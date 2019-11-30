package com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamemodel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.tappinggame.tapgamedrawers.BackGroundDrawer;
import java.util.ArrayList;
import java.util.List;

/**
 * A Background Object for the tapping game.
 */
public class BackGround implements Drawable {
    /**
     * The bitmap of the background to be drawn/
     */
    private Bitmap backGroundBitmap;
    /**
     * The x-coordinate of the background.
     */
    private final int x = 0;
    /**
     * The y-coordinate of the background.
     */
    private final int y = 0;
    /**
     * The paint of the background for canvas drawing.
     */
    private Paint paint;

    /**
     * Initiates the BackGround Object.
     *
     * @param context      The context for retrieving resources.
     * @param screenWidth  screenWidth for scaling the background.
     * @param screenHeight screenHeight for scaling the background.
     */
    public BackGround(Context context, int screenWidth, int screenHeight) {
        this.backGroundBitmap =
                BitmapFactory.decodeResource(context.getResources(), R.drawable.tap_background);
        this.backGroundBitmap = Bitmap.createScaledBitmap(this.backGroundBitmap, screenWidth, screenHeight, true);
        this.paint = new Paint();
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        AndroidDrawer backgroundDrawer;
        backgroundDrawer = new BackGroundDrawer(backGroundBitmap, x, y, paint);
        drawers.add(backgroundDrawer);
        return drawers;
    }
}
