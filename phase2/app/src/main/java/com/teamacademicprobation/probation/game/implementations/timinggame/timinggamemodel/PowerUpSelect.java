package com.teamacademicprobation.probation.game.implementations.timinggame.timinggamemodel;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.game.implementations.AndroidDrawer;
import com.teamacademicprobation.probation.game.implementations.Drawable;
import com.teamacademicprobation.probation.game.implementations.timinggame.drawers.DamageBoostDrawer;
import com.teamacademicprobation.probation.game.implementations.timinggame.drawers.HealthBoostDrawer;
import com.teamacademicprobation.probation.game.implementations.timinggame.drawers.PowerUpLineDrawer;

import java.util.ArrayList;
import java.util.List;

/**
 * The power up selection. Has two options, a damage boost or a health boost.
 */
public class PowerUpSelect implements Drawable {

    /**
     * The size of the images for the boost.
     */
    private static final int SIZE = 128;
    private int screenWidth;
    private int screenHeight;
    /**
     * The selection chosen.
     */
    private PowerUps selection;
    private Resources resources;
    /**
     * The image for the damage boost.
     */
    private Bitmap increaseDamage;
    /**
     * The image for the health boost.
     */
    private Bitmap increaseHealth;
    /**
     * The style of the game.
     */
    private TimingGameStyle gameStyle;

    /**
     * Initializes a new PowerUpSelect.
     * @param screenWidth The width of the screen, in pixels.
     * @param screenHeight The height of the screen, in pixels.
     * @param gameStyle The style of the game.
     */
    public PowerUpSelect(int screenWidth, int screenHeight, TimingGameStyle gameStyle) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gameStyle = gameStyle;
    }

    /**
     * Builds the images for both boosts.
     * @param resources The resources to retrieve images from.
     */
    public void buildPowerUpImages(Resources resources) {
        this.resources = resources;
        this.generateImages();
    }

    /**
     * Generates the images for the boosts, and sets them to the correct size.
     */
    private void generateImages() {
        increaseDamage =
                BitmapFactory.decodeResource(resources, R.drawable.damage_upgrade);
        increaseHealth = BitmapFactory.decodeResource(resources, R.drawable.heart_upgrade);

        increaseDamage = Bitmap.createScaledBitmap(increaseDamage, SIZE, SIZE, false);
        increaseHealth = Bitmap.createScaledBitmap(increaseHealth, SIZE, SIZE, false);
    }

    public PowerUps getSelection() {
        return selection;
    }

    /**
     * Generates the paint for the text, according to the game style.
     * @return Paint that describes the style of the text.
     */
    private Paint generateTextPaint() {

        Paint paint = new Paint();
        paint.setTextSize(40);
        paint.setStrokeWidth(7);
        paint.setColor(gameStyle.getMeterColor());
        return paint;
    }

    @Override
    public List<AndroidDrawer> getDrawers() {
        List<AndroidDrawer> drawers = new ArrayList<>();
        int damageX = (int) (screenWidth * 0.25 - SIZE * 0.5);
        int healthX = (int) (screenWidth * 0.75 - SIZE * 0.5);
        int y = (int) (screenHeight * 0.5 - SIZE * 0.5);
        int length = SIZE + 150;
        DamageBoostDrawer dmDrawer =
                new DamageBoostDrawer(increaseDamage, damageX, y, generateTextPaint());
        HealthBoostDrawer hbDrawer =
                new HealthBoostDrawer(increaseHealth, healthX, y, generateTextPaint());
        PowerUpLineDrawer pulDrawer =
                new PowerUpLineDrawer(
                        (int) (screenWidth * 0.5),
                        (int) (screenHeight * 0.5 - (length * 0.5)),
                        length,
                        generateTextPaint());
        drawers.add(dmDrawer);
        drawers.add(hbDrawer);
        drawers.add(pulDrawer);
        return drawers;
    }

    /**
     * Changes the selection based on the choice. If the user taps the left of the screen,
     * selection is damage, else it is health.
     * @param touchX The x coordinate of the tap.
     */
    public void onTouch(double touchX) {
        this.selection = (touchX < screenWidth * 0.5) ? PowerUps.DAMAGE : PowerUps.HEALTH;
    }

    /**
     * Enums that descibe the possible powerups.
     */
    public enum PowerUps {
        DAMAGE,
        HEALTH
    }
}
