package cdm8pf.cs2110.virginia.edu.ghosthunter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.graphics.Bitmap;

import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.*;
import java.lang.*;
import android.widget.RadioGroup.*;


/**
 * Created by colettemerrill on 4/5/15.
 */
public class Easy extends Activity implements View.OnTouchListener{

    MediaPlayer backgroundMusic;

    OurView v;
    Sprite sprite;
    Bitmap user;
    Bitmap up;
    Bitmap down;
    Bitmap right;
    Bitmap left;
    Bitmap stop;
    Bitmap coin;
    Bitmap square;
    int counter;
    int coinTick;
    Paint p;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        counter = 0;

        backgroundMusic = MediaPlayer.create(this, R.raw.logo_song);
        backgroundMusic.start();
        v = new OurView(this);



        setContentView(v);

       v.setOnTouchListener(this);
        user = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);
        //up = BitmapFactory.decodeResource(getResources(), R.drawable.up);
        up = BitmapFactory.decodeResource(getResources(),  R.drawable.up);
        down = BitmapFactory.decodeResource(getResources(),  R.drawable.down);
        left = BitmapFactory.decodeResource(getResources(),  R.drawable.left);
        right = BitmapFactory.decodeResource(getResources(),  R.drawable.right);
        stop = BitmapFactory.decodeResource(getResources(), R.drawable.stop);
        coin = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
    }

//if program is paused

    protected void onPause() {
        super.onPause();
        backgroundMusic.release();
        v.pause();
    }

    protected void onResume() {
        super.onResume();
        v.resume();
    }

    //if button is pressed

    public boolean onTouch(View v, MotionEvent event) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:


                //up movement
<<<<<<< HEAD

                if(event.getX() <= 302 && event.getX() >= 200 && event.getY() >= 904 && event.getY() <= 1004){
                    sprite.setXSpeed(0);
                    sprite.setYSpeed(-5);
                    //Toast toast = Toast.makeText(getApplicationContext(), "pressed", Toast.LENGTH_SHORT);
                    //toast.show();
=======
                if(event.getX() < v.getWidth()/2 + up.getWidth()/2 && event.getX() > v.getWidth()/2 - up.getWidth()/2 && event.getY() > v.getHeight()-down.getHeight() && event.getY() < v.getHeight()-3*up.getHeight()){
                    sprite.setXSpeed(0);
                    sprite.setYSpeed(-5);

>>>>>>> sprite-images
                }

                //right movement
                if(event.getX() <= 450 && event.getX() >= 350 && event.getY() >= 1050 && event.getY() <= 1152){

                   sprite.setXSpeed(5);
                   sprite.setYSpeed(0);
//
                }
                //down movement
<<<<<<< HEAD
                if(event.getX() <= 302 && event.getX() >= 200 && event.getY() >= 1050 && event.getY() <= 1152){
=======
                if(event.getX() > 200 && event.getX() < 400){
>>>>>>> sprite-images
                    sprite.setXSpeed(0);
                    sprite.setYSpeed(5);
                }
                //left movement
<<<<<<< HEAD
                if(event.getX() <= 150 && event.getX() >= 50 && event.getY() >= 1050 && event.getY() <= 1152){
=======
                if(event.getX() > 400 && event.getX() < 600){
>>>>>>> sprite-images
                    sprite.setXSpeed(-5);
                    sprite.setYSpeed(0);
                }
                //stop movement
                if(event.getX() <= 675 && event.getX() >= 575 && event.getY() >= 1050 && event.getY() <= 1152){
                    sprite.setXSpeed(0);
                    sprite.setYSpeed(0);
                }


                break;
            case MotionEvent.ACTION_UP:
                    sprite.setXSpeed(0);
                    sprite.setYSpeed(0);
                Toast toast2 = Toast.makeText(getApplicationContext(), "up", Toast.LENGTH_SHORT);
                toast2.show();

                break;
        }
        return false;
    }


    //The gameplay view
    public class OurView extends SurfaceView implements Runnable{

    Thread t = null;
    SurfaceHolder holder;
    boolean ok = true;


    public OurView(Context context) {
        super(context);
        holder = getHolder();
    }

    @Override
    public void run() {
        sprite = new Sprite(OurView.this, user);
        p = new Paint();
        while(ok == true){

            if(!holder.getSurface().isValid()) {
                continue;
            }
            Canvas c = holder.lockCanvas();
            onDraw(c);
            holder.unlockCanvasAndPost(c);




        }



    }

//What is being drawn each time
    protected void onDraw(Canvas c) {
        counter++;
        //c.drawPicture(level_background.png);
        c.drawARGB(150, 0, 0, 0);
        sprite.onDraw(c);
        drawButtons(c);
        drawScore(c);




//        if(counter % 100 == 0) {
//            if(counter - coinTick < 400) {
//                drawCoin(c, 300, 300);
//            }
//
//        }
    }

//if game is paused
    public void pause(){

        ok = false;
        while(true){
            try{
                t.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            break;
        }
        t = null;

        backgroundMusic.release();
    }
 //if game is resumed
    public void resume(){
        ok = true;
        t = new Thread(this);
        t.start();
    }


//draws the buttons
public void drawButtons(Canvas c){

    /*
    c.drawBitmap(up, 350,v.getHeight()-right.getHeight()-300 , null);
    c.drawBitmap(right, 500, v.getHeight()-right.getHeight()-150,null  );
    c.drawBitmap(down,350,v.getHeight() - down.getHeight(), null);
    c.drawBitmap(left, 200, v.getHeight() - left.getHeight()-150, null );
    c.drawBitmap(stop, 350, v.getHeight()-stop.getHeight()-150, null);
    */

    c.drawBitmap(up, 200,904, null);
    c.drawBitmap(left, 50, 1050, null );
    c.drawBitmap(right, 350, 1050,null  );
    c.drawBitmap(down,200,1050, null);
    c.drawBitmap(stop, 575, 1050, null);

}

//draws the coin
public void drawCoin(Canvas c, int x, int y){
            coinTick = counter;
            c.drawBitmap(coin,x, y, null);
        }

}
public void drawScore(Canvas c){
    Bitmap b = Bitmap.createBitmap(200, 200, Bitmap.Config.ALPHA_8);
    Canvas ca = new Canvas(b);
    p.setTextSize(30);
    p.setColor(R.color.red);
    p.setAntiAlias(true);
    ca.drawText("hello", 30, 40, p);
    c.drawBitmap(b, 300, 300, p);


}




}

