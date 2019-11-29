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

public class PowerUpSelect implements Drawable {

    private static final int SIZE = 128;
    private int screenWidth;
    private int screenHeight;
    private PowerUps selection;
    private Resources resources;
    private Bitmap increaseDamage;
    private Bitmap increaseHealth;
    private TimingGameStyle gameStyle;

    public PowerUpSelect(int screenWidth, int screenHeight, TimingGameStyle gameStyle) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.gameStyle = gameStyle;
    }

    public void buildPowerUpImages(Resources resources) {
        this.resources = resources;
        this.generateImages();
    }

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

    private Paint generateTextPaint() {

        Paint paint = new Paint();
        paint.setTextSize(40);
        paint.setStrokeWidth(7);
        paint.setColor(gameStyle.getMeterColor()); // TODO: ADD TEXT COLOR
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

    public void onTouch(double touchX) {
        this.selection = (touchX < screenWidth * 0.5) ? PowerUps.DAMAGE : PowerUps.HEALTH;
    }

    public enum PowerUps {
        DAMAGE,
        HEALTH
    }
}
