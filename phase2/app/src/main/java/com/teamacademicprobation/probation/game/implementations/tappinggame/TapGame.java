package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.teamacademicprobation.probation.R;
import com.teamacademicprobation.probation.game.ScoreBoard;
import com.teamacademicprobation.probation.player.PlayerAccess;
import com.teamacademicprobation.probation.player.PlayerManager;
import com.teamacademicprobation.probation.player.PlayerStatsAccess;

import java.util.Random;

/**
 * A Tapping game where the player tries to tap the target object and avoid tapping non-target.
 */
class TapGame {
    /**
     * The gameID of this game.
     */
    private static final String GAME_ID = "TapGame";
    /**
     * Represents if the game is completed.
     */
    private boolean gameComplete;

    private NormalMole normalMole;
    private KingMole kingMole;
    private BombMole bombMole;
    private ScoreBoard scoreBoard;
    private Random r = new Random();
    private NormalMoleCounter targetCounter;
    private int x;
    private int y;
    private PlayerStatsAccess playerAccess;
    private String currPlayerID;
    private Context context;
    private Bitmap backGroundBitmap;

    /**
     * Constructor for the Tap game.
     */
    TapGame(Context context, int x, int y, String currPlayerID) {
        this.x = x;
        this.y = y;
        this.context = context;
        this.gameComplete = false;
        this.scoreBoard = new ScoreBoard(x, y);
        this.targetCounter = new NormalMoleCounter(x, y);
        this.playerAccess = new PlayerManager();
        this.currPlayerID = currPlayerID;
        this.backGroundBitmap =
                BitmapFactory.decodeResource(context.getResources(), R.drawable.tap_background);
        this.backGroundBitmap = Bitmap.createScaledBitmap(this.backGroundBitmap, x, y, true);
    }

    /**
     * Return the status of the game (Completed or not Completed).
     */
    boolean getGameComplete() {
        return this.gameComplete;
    }

    /**
     * Return the score of the game.
     *
     * @return score of the game.
     */
    int getScore() {
        return this.scoreBoard.getScore();
    }

    /**
     * Return targetObject.
     *
     * @return NormalMole
     */
    private NormalMole getNormalMole() {
        return this.normalMole;
    }

    /**
     * Return targetObject.
     *
     * @return nonTargetObject
     */
    private BombMole getBombMole() {
        return this.bombMole;
    }

    private KingMole getKingMole() {
        return kingMole;
    }

    /**
     * Draw the TapGame objects on the canvas.
     */
    void draw(Canvas canvas) {
        canvas.drawBitmap(this.backGroundBitmap, 0, 0, null);
        this.scoreBoard.draw(canvas);
        this.targetCounter.draw(canvas);
        if (this.normalMole != null) {
            this.normalMole.draw(canvas);
        } else if (this.bombMole != null) {
            this.bombMole.draw(canvas);
        } else if (this.kingMole != null) {
            this.kingMole.draw(canvas);
        }
    }

    /**
     * Updates the TapGame.
     */
    void update() {
        double c = Math.random(); // Randomly choose if a Target or Non-target should be created.
        if (c > 0.7) {
            createBombMole();
        } else if (0.1 < c && c <= 0.7) {
            createNormalMole();
            targetCounter.addCount();
        } else if (c <= 0.1) {
            createKingMole();
        }
        // If target objects reach the limit. The game is completed
        if (targetCounter.getNormalMoleCount() == this.targetCounter.getNormalMoleLimit()) {
            this.setCompleted();
        }
    }

    private void createKingMole() {
        this.normalMole = null;
        this.bombMole = null;
        this.kingMole =
                new KingMole(
                        context,
                        r.nextInt(((this.x - 200) - 150) + 150),
                        r.nextInt((this.y - 150) - (this.y / 2 + 50)) + this.y / 2);
    }

    private void createNormalMole() {
        this.bombMole = null;
        this.kingMole = null;
        normalMole =
                new NormalMole(
                        context,
                        r.nextInt(((this.x - 200) - 150) + 150),
                        r.nextInt((this.y - 150) - (this.y / 2 + 50)) + this.y / 2);
    }

    private void createBombMole() {
        this.normalMole = null;
        this.kingMole = null;
        this.bombMole =
                new BombMole(
                        context,
                        r.nextInt(((this.x - 200) - 150) + 150),
                        r.nextInt((this.y - 150) - (this.y / 2 + 50)) + this.y / 2);
    }

    /**
     * Sets the game to completed and sends the statistics.
     */
    private void setCompleted() {
        this.gameComplete = true;
        playerAccess.updateStats(currPlayerID, GAME_ID, "score", this.scoreBoard.getScore());
    }

    /**
     * Checks if the user tapped on the non-target or target object.
     *
     * @param touch_x the x-coordinate where the user touched.
     * @param touch_y the x-coordinate where the user touched
     */
    void check_touch(double touch_x, double touch_y) {
        if (this.getNormalMole() != null) {
            if (this.getNormalMole().getX() < touch_x
                    && touch_x < this.getNormalMole().getX() + this.getNormalMole().getSize()
                    && this.getNormalMole().getY() < touch_y
                    && touch_y < this.getNormalMole().getY() + this.getNormalMole().getSize()) {
                this.scoreBoard.earnPoint();
            }
        } else if (this.getKingMole() != null) {
            if (this.getKingMole().getX() < touch_x
                    && touch_x < this.getKingMole().getX() + this.getKingMole().getSize()
                    && this.getKingMole().getY() < touch_y
                    && touch_y < this.getKingMole().getY() + this.getKingMole().getSize()) {
                this.scoreBoard.earnFivePoints();
            }
        } else if (this.getBombMole() != null)
            if (this.getBombMole().getX() < touch_x
                    && touch_x < this.getBombMole().getX() + this.getBombMole().getSize()
                    && this.getBombMole().getY() < touch_y
                    && touch_y < this.getBombMole().getY() + this.getBombMole().getSize()) {
                this.scoreBoard.losePoint();
            }
    }
}

