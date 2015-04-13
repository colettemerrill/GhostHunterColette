package cdm8pf.cs2110.virginia.edu.ghosthunter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.widget.Button;

/**
 * Created by colettemerrill on 4/6/15.
 */
public class Sprite {
    int x, y;
    int xSpeed, ySpeed;
    int height, width;
    Bitmap sprite;
    Easy.OurView ov;
   // Rect hitbox;
    int direction;



    public Sprite(Easy.OurView ourView, Bitmap p) {
        sprite = p;
        ov = ourView;
        height = sprite.getHeight()/4;
        width = sprite.getWidth() /4;
        x = 100;
        y = 200;
        xSpeed = 1;
        ySpeed = 0;
        //hitbox = new Rect(x,y, sprite.getHeight()/4, sprite.getWidth()/4);
    }

    private void update() {

        x += xSpeed;
        y +=  ySpeed;

    }








    public void onDraw(Canvas c){
        update();
        //int srcY = direction * height;
        Rect src = new Rect(0,0,width, height);
        Rect d = new Rect(x, y, x+width, y+height);
        c.drawBitmap(sprite, src, d, null);
    }


    public int getXSpeed(){
        return xSpeed;
    }
    public int getYSpeed(){
        return ySpeed;
    }
    public void setXSpeed(int nX){
        xSpeed = nX;
    }
    public void setYSpeed(int nY) {
        ySpeed = nY;
    }


}
