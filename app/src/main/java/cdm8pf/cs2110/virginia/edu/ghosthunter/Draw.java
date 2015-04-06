package cdm8pf.cs2110.virginia.edu.ghosthunter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by colettemerrill on 4/6/15.
 */
public class Draw extends View {


    public Draw(Context context) {
        super(context);
    }

    @Override
protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Rect r = new Rect();
        r.set(30, 40, 60, 70);
    Paint p = new Paint();
    p.setColor(Color.BLUE);
    p.setStyle(Paint.Style.FILL);
canvas.drawRect(r, p);


    }
}
