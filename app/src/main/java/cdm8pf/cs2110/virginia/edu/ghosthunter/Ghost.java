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
    Rect ghostHitbox;
    int direction;
    int currentFrame = 0;
    boolean bottomRepeat;
    boolean bottomRightRepeat;
    boolean bottomRight2Repeat;
    boolean topLeftRepeat;
    boolean topRightRepeat;
    boolean topRight2Repeat;
    boolean middleRepeat;



    public Ghost(View ourView, Bitmap p, int xCoord, int yCoord) {
        ghost = p;
        ov = ourView;
        height = ghost.getHeight()/4;
        width = ghost.getWidth() /4;
        x = xCoord;
        y = yCoord;

        xSpeed = 0;
        ySpeed = 0;
        direction = 0;
        ghostHitbox = new Rect(getX(), getY(), getX() + 34, getY() + 66);

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

        move1();

        x += xSpeed;
        y +=  ySpeed;
        ghostHitbox = new Rect(getX(), getY(), getX() + 26, getY() + 51);



    }

    public void move1(){
        if(getX()==75 && getY()==120){
            middleRepeat = false;
            bottomRepeat = false;
            bottomRightRepeat = false;
            bottomRight2Repeat = false;
            topLeftRepeat = false;
            topRightRepeat = false;
            topRight2Repeat = false;
            xSpeed = 0;
            ySpeed = 5;
        }
        if(getX()==75 && getY()==350 && middleRepeat == false){
            xSpeed = 5;
            ySpeed = 0;
        }

        if(getX()==150 && getY()==350 && middleRepeat == false){
            xSpeed = 0;
            ySpeed = 5;
        }

        if(getX()==150 && getY()==425 && middleRepeat == false){
            xSpeed = -5;
            ySpeed = 0;
        }

        if(getX()==75 && getY()==425 && bottomRepeat==false){
            xSpeed = 0;
            ySpeed = 5;
        }

        if(getX()==75 && getY()==805 && bottomRepeat==false){
            xSpeed = 5;
            ySpeed = 0;
        }

        if(getX()==320 && getY()==805){
            bottomRepeat = true;
            xSpeed = -5;
            ySpeed = 0;
        }
        if(getX() == 75 && getY() == 805 && bottomRepeat == true && middleRepeat == false){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 75 && getY() == 725 && bottomRepeat == true && middleRepeat == false){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 160 && getY() == 725 && bottomRepeat == true && middleRepeat == false){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 160 && getY() == 500 && middleRepeat == false){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 240 && getY() == 500 && middleRepeat == false){
            xSpeed = 0;
            ySpeed = 5;
        }
        if(getX()== 240 && getY() == 725 && middleRepeat == false){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 320 && getY() == 725 && middleRepeat == false){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 320 && getY() == 640 && middleRepeat == false){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 480 && getY() == 640 && middleRepeat == false){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 480 && getY() == 565&& middleRepeat == false){
            xSpeed = -5;
            ySpeed = 0;
        }
        if(getX()== 310 && getY() == 565 ){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 310 && getY() == 270 ){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 430 && getY() == 270 ){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 430 && getY() == 120 && topRightRepeat == false){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 705 && getY() == 120 && topRightRepeat == false){
            xSpeed = 0;
            ySpeed = 5;
        }
        if(getX()== 705 && getY() == 350){
            topRightRepeat = true;
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 705 && getY() == 195 && topRightRepeat == true){
            xSpeed = -5;
            ySpeed = 0;
        }
        if(getX()== 500 && getY() == 195 && topRightRepeat == true){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 500 && getY() == 120 && topRightRepeat == true){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 705 && getY() == 120 && topRightRepeat == true){
            topRight2Repeat = true;
            xSpeed = 0;
            ySpeed = 5;
        }
        if(getX()== 620 && getY() == 195 && topRightRepeat == true && topRight2Repeat == true){
            xSpeed = 0;
            ySpeed = 5;
        }
        if(getX()== 620 && getY() == 425){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 705 && getY() == 425){
            xSpeed = 0;
            ySpeed = 5;
        }
        if(getX()== 705 && getY() == 725){
            xSpeed = -5;
            ySpeed = 0;
        }
        if(getX()== 620 && getY() == 725 && bottomRightRepeat == false){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 620 && getY() == 500){
            bottomRightRepeat = true;
            xSpeed = 0;
            ySpeed = 5;
        }
        if(getX()== 620 && getY() == 805 && bottomRight2Repeat == false){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 705 && getY() == 805){
            bottomRight2Repeat = true;
            xSpeed = -5;
            ySpeed = 0;
        }
        if(getX()== 390 && getY() == 805){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 390 && getY() == 725){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 550 && getY() == 725){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 550 && getY() == 270){
            xSpeed = -5;
            ySpeed = 0;
        }
        if(getX()== 500 && getY() == 270){
            xSpeed = 0;
            ySpeed = 5;
        }
        if(getX()== 500 && getY() == 350){
            xSpeed = -5;
            ySpeed = 0;
        }
        if(getX()== 390 && getY() == 350 && middleRepeat == false){
            xSpeed = 0;
            ySpeed = 5;
        }
        if(getX()== 390 && getY() == 490 && middleRepeat == false){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 480 && getY() == 490 && middleRepeat == false){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 480 && getY() == 420){
            middleRepeat = true;
            xSpeed = -5;
            ySpeed = 0;
        }
        if(getX()== 390 && getY() == 420 && middleRepeat == true){
            xSpeed = 0;
            ySpeed = 5;
        }
        if(getX()== 390 && getY() == 490 && middleRepeat == true){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 480 && getY() == 490 && middleRepeat == true){
            xSpeed = 0;
            ySpeed = 5;
        }
        if(getX()== 480 && getY() == 640 && middleRepeat == true){
            xSpeed = -5;
            ySpeed = 0;
        }
        if(getX()== 320 && getY() == 640 && middleRepeat == true){
            xSpeed = 0;
            ySpeed = 5;
        }
        if(getX()== 320 && getY() == 725 && middleRepeat == true){
            xSpeed = -5;
            ySpeed = 0;
        }
        if(getX()== 240 && getY() == 725 && middleRepeat == true){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 240 && getY() == 500 && middleRepeat == true){
            xSpeed = -5;
            ySpeed = 0;
        }
        if(getX()== 160 && getY() == 500 && middleRepeat == true){
            xSpeed = 0;
            ySpeed = 5;
        }
        if(getX()== 160 && getY() == 725 && middleRepeat == true){
            xSpeed = -5;
            ySpeed = 0;
        }
        if(getX()== 75 && getY() == 725 && middleRepeat == true){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 75 && getY() == 425 && middleRepeat == true){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 150 && getY() == 425 && middleRepeat == true){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 150 && getY() == 350 && middleRepeat == true){
            xSpeed = -5;
            ySpeed = 0;
        }
        if(getX()== 75 && getY() == 350 && middleRepeat == true){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 75 && getY() == 270 && middleRepeat == true){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 240 && getY() == 270 && middleRepeat == true && topLeftRepeat == false){

            xSpeed = 0;
            ySpeed = 5;
        }
        if(getX()== 240 && getY() == 425 && middleRepeat == true){
            topLeftRepeat = true;
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 240 && getY() == 270 && middleRepeat == true && topLeftRepeat == true){
            xSpeed = -5;
            ySpeed = 0;
        }
        if(getX()== 150 && getY() == 270 && middleRepeat == true && topLeftRepeat == true){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 150 && getY() == 195){
            xSpeed = 5;
            ySpeed = 0;
        }
        if(getX()== 355 && getY() == 195){
            xSpeed = 0;
            ySpeed = -5;
        }
        if(getX()== 355 && getY() == 120){
            xSpeed = -5;
            ySpeed = 0;
        }
    }

    public void onDraw(Canvas c){
        update();

        int srcX = currentFrame * width;
        int srcY = direction * height;
        Rect src = new Rect(srcX, srcY, srcX+ width, srcY + height);
        Rect d = new Rect(x, y, x+width, y+height);
        c.drawBitmap(ghost, src, d, null);
    }


    public boolean collision(Rect r, Rect s){
        if(r.intersect(s)){
            return true;
        }
        else
            return false;
    }

    public Rect getGhostHitbox() {return ghostHitbox;}
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
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }


}
