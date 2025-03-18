package com.example.signconnect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Custom extends View {
    private Paint paint;
    private float startX, startY, endX, endY;

    public Custom(Context context) {
        super(context);
        init();
    }

    public Custom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Custom(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.YELLOW); // Set the color for the line
        paint.setStrokeWidth(10); // Set the width of the line
        paint.setAntiAlias(true); // Smooth the line edges
    }

    public void setLinePositions(float startX, float startY, float endX, float endY) {
        this.startX = startX;
        this.startY = startY - 300;
        this.endX = endX - 50;
        this.endY = endY - 350;
        invalidate(); // Request to redraw the view
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(startX, startY, endX, endY, paint);
    }

}

