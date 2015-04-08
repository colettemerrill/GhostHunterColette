package cdm8pf.cs2110.virginia.edu.ghosthunter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by colettemerrill on 4/6/15.
 */
public class Sprite {
    int x, y;
    int xSpeed, ySpeed;
    int height, width;
    Bitmap sprite;
    Easy.OurView ov;



    public Sprite(Easy.OurView ourView, Bitmap p) {
        sprite = p;
        ov = ourView;
        height = sprite.getHeight()/4;
        width = sprite.getWidth() /4;
        x = y = 30;
        xSpeed = 5;
        ySpeed = 0;
    }

    private void update() {
        x+=xSpeed;
        y += ySpeed;

    }

    public void onDraw(Canvas c){
        Rect src = new Rect(0,0,width, height);
        Rect d = new Rect(x, y, x+width, y+height);
        c.drawBitmap(sprite, src, d, null);
    }



}
