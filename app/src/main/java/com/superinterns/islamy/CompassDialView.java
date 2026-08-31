package com.superinterns.islamy;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Canvas;

public class CompassDialView extends View {

    private Paint tickPaint;
    private Paint majorTickPaint;
    private Paint textPaint;

    public CompassDialView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        tickPaint = new Paint();
        tickPaint.setColor(Color.LTGRAY);
        tickPaint.setStrokeWidth(3f);
        tickPaint.setAntiAlias(true);

        majorTickPaint = new Paint();
        majorTickPaint.setColor(Color.parseColor("#37B898"));
        majorTickPaint.setStrokeWidth(6f);
        majorTickPaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#37B898"));
        textPaint.setTextSize(32f);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;
        float radius = Math.min(centerX, centerY) - 10f;

        // Loop around the full circle every 15 degrees
        for (int angle = 0; angle < 360; angle += 15) {
            boolean isMajor = (angle % 90 == 0);

            double radians = Math.toRadians(angle);

            float outerX = centerX + radius * (float) Math.sin(radians);
            float outerY = centerY - radius * (float) Math.cos(radians);

            float tickLength = isMajor ? 30f : 15f;
            float innerRadius = radius - tickLength;

            float innerX = centerX + innerRadius * (float) Math.sin(radians);
            float innerY = centerY - innerRadius * (float) Math.cos(radians);

            Paint paintToUse = isMajor ? majorTickPaint : tickPaint;
            canvas.drawLine(innerX, innerY, outerX, outerY, paintToUse);

            if (isMajor) {
                String label = getLabelForAngle(angle);
                float labelRadius = radius - 55f;
                float labelX = centerX + labelRadius * (float) Math.sin(radians);
                float labelY = centerY - labelRadius * (float) Math.cos(radians) + 10f; // +10 to vertically center text
                canvas.drawText(label, labelX, labelY, textPaint);
            }
        }
    }

    private String getLabelForAngle(int angle) {
        switch (angle) {
            case 0: return "N";
            case 90: return "E";
            case 180: return "S";
            case 270: return "W";
            default: return "";
        }
    }
}