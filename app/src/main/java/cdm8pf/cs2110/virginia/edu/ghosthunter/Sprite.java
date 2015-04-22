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
    View ov;
   Rect userHitbox;
    int direction;
    int currentFrame = 0;



    public Sprite(View ourView, Bitmap p) {
        sprite = p;
        ov = ourView;
        height = sprite.getHeight()/4;
        width = sprite.getWidth() /4;
        x = 600;
        y = 600;
        xSpeed = 0;
        ySpeed = 0;
        direction = 0;
        currentFrame = 0;
        userHitbox = new Rect(getX(), getY(), getX() + 20, getY() + 40);
    }

    private void update()  {

        //moving down
        if(xSpeed == 0 && ySpeed > 0){
            direction = 0;
        }
        //moving left
        if(ySpeed == 0 && xSpeed < 0){
            direction = 1;
        }
        //moving up
        if(xSpeed == 0 && ySpeed < 0){
            direction = 3;
        }
        //moving right
        if(ySpeed == 0 && xSpeed > 0){
            direction = 2;
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        currentFrame= ++currentFrame % 4;
        x += xSpeed;
        y +=  ySpeed;
        userHitbox= new Rect(getX(), getY(), getX() + 26, getY() + 51);

    }








    public void onDraw(Canvas c){
        update();
        int srcX = currentFrame * width;
        int srcY = direction * height;
        Rect src = new Rect(srcX, srcY, srcX+ width, srcY + height);
        Rect d = new Rect(x, y, x+width, y+height);
        c.drawBitmap(sprite, src, d, null);

    }

    public boolean collision(Rect r, Rect s){
        if(r.intersect(s)){
            return true;
        }
        else
            return false;
    }


    public Rect getUserHitbox() {return userHitbox;}

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
    public void setX(int nx) {
        x = nx;
    }
    public void setY(int nx) { y = nx; }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }


}
