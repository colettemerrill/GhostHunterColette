package cdm8pf.cs2110.virginia.edu.ghosthunter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.widget.Button;


public class Ghost {
    int x, y;
    int xSpeed, ySpeed;
    int height, width;
    Bitmap ghost;
    View ov;
    // Rect hitbox;
    int direction;
    int currentFrame = 0;



    public Ghost(View ourView, Bitmap p) {
        ghost = p;
        ov = ourView;
        height = ghost.getHeight()/4;
        width = ghost.getWidth() /4;
        x = 100;
        y = 180;
        xSpeed = 1;
        ySpeed = 0;
        direction = 0;
        //hitbox = new Rect(x,y, sprite.getHeight()/4, sprite.getWidth()/4);
    }

    private void update() {

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


        x += xSpeed;
        y +=  ySpeed;

    }








    public void onDraw(Canvas c){
        update();

        int srcX = currentFrame * width;
        int srcY = direction * height;
        Rect src = new Rect(srcX, srcY, srcX+ width, srcY + height);
        Rect d = new Rect(x, y, x+width, y+height);
        c.drawBitmap(ghost, src, d, null);
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
