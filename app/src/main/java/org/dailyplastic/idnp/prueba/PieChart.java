package org.dailyplastic.idnp.prueba;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class PieChart extends View {

    private static final int[] DEFAULT_COLORS = new int[]{
            Color.parseColor("#001F10"),
            Color.parseColor("#002F18"),
            Color.parseColor("#003E20"),
            Color.parseColor("#3F7457"),
            Color.parseColor("#7EA58F"),
            Color.parseColor("#BDD3C6")};
    private static int[] values;
    private static String[] data;
    private static int[] colors;

    public PieChart(Context context , int[] values, String[] data, int[] colors) {
        super(context);
        this.values = values;
        this.data = data;
        this.colors = colors;
    }

    public PieChart(Context context , int[] values, String[] data) {
        super(context);
        this.values = values;
        this.data = data;
        this.colors = DEFAULT_COLORS;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPieChart(canvas);
        drawLeyend(canvas);
    }

    private void drawPieChart(Canvas canvas) {
        float start = 0;
        Paint paint = new Paint();
        float[] valuesDegrees = valuesDegrees();

        RectF rectF = new RectF(0,0,getWidth(),getWidth());

        for(int i = 0; i < values.length; i++){
            paint.setColor(colors[i]);
            canvas.drawArc(rectF, start, valuesDegrees[i],true, paint);
            start = start + valuesDegrees[i];
        }
    }

    private void drawLeyend(Canvas canvas) {

        Paint legend = new Paint();
        Paint circleLegend = new Paint();
        legend.setColor(Color.BLACK);
        legend.setTextSize(35);

        int x = 60;
        int y = getHeight();

        for (int i = 0; i < data.length ; i++) {
            circleLegend.setColor(colors[i]);
            canvas.drawText( data[i] , x, y, legend);
            canvas.drawCircle(25, y - 15 , 15, circleLegend);
            y -= 50;
        }
    }

    private float[] valuesDegrees() {
        float[] valuesDegrees = new float[this.values.length];
        float total = getTotal();
        for (int i = 0; i < this.values.length; i++) {
            valuesDegrees[i] = (this.values[i] / total) * 360;
        }
        return valuesDegrees;
    }

    private float getTotal() {
        float total = 0;
        for (float value : this.values)
            total += value;
        return total;
    }
}
