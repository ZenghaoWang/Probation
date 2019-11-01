package com.teamacademicprobation.probation.game.implementations.tappinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class BlueObject extends TapObject {
  private Paint paint = new Paint();

  BlueObject(int x, int y) {
    super(x, y);
    paint.setColor(Color.BLUE);
    paint.setStyle(Paint.Style.FILL);
  }

  void draw(Canvas canvas) {
    canvas.drawCircle(getX(), getY(), radius, paint);
  }
}
