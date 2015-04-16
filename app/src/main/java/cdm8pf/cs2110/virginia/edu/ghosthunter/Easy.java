package cdm8pf.cs2110.virginia.edu.ghosthunter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backgroundMusic = MediaPlayer.create(this, R.raw.logo_song);
        backgroundMusic.start();
        v = new OurView(this);



        setContentView(v);

       v.setOnTouchListener(this);
        v.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int width1 = v.getMeasuredWidth();
        Log.v("hello",Integer.toString(width1));

        user = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);
        //up = BitmapFactory.decodeResource(getResources(), R.drawable.up);
        up = BitmapFactory.decodeResource(getResources(),  R.drawable.up);
        down = BitmapFactory.decodeResource(getResources(),  R.drawable.down);
        left = BitmapFactory.decodeResource(getResources(),  R.drawable.left);
        right = BitmapFactory.decodeResource(getResources(),  R.drawable.right);
        stop = BitmapFactory.decodeResource(getResources(), R.drawable.stop);
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
                if(event.getX() < v.getWidth()/2 + up.getWidth()/2 && event.getX() > v.getWidth()/2 - up.getWidth()/2 && event.getY() > v.getHeight()-down.getHeight() && event.getY() < v.getHeight()-3*up.getHeight()){
                    //sprite.setXSpeed(0);
                    sprite.setYSpeed(-5);
                    Toast toast = Toast.makeText(getApplicationContext(), "pressed", Toast.LENGTH_SHORT);
                    toast.show();
                }
                //right movement
                if(event.getX() > 30 && event.getX() < 200){

                   sprite.setXSpeed(5);
                   sprite.setYSpeed(0);
//
                }
                //down movement
                if(event.getX() == 1){
                    sprite.setXSpeed(0);
                    sprite.setYSpeed(5);
                }
                //left movement
                if(event.getX() == 1){
                    sprite.setXSpeed(-5);
                    sprite.setYSpeed(0);
                }
                //stop movement
                if(event.getX() == 1){
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

        LinearLayout l = new LinearLayout(context);
        Button u = new Button(context);
        u.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        u.setX(200);
        u.setY(300);
        l.addView(u);



    }

    @Override
    public void run() {
        sprite = new Sprite(OurView.this, user);
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
        //c.drawPicture(level_background.png);
        c.drawARGB(150, 0, 0, 0);
        sprite.onDraw(c);
        drawButtons(c);
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
    public void resume(){
        ok = true;
        t = new Thread(this);
        t.start();
    }


//draws the buttons
public void drawButtons(Canvas c){
    c.drawBitmap(up, 603, 0, null);
    c.drawBitmap(right, v.getWidth()/2 + right.getWidth(), v.getHeight()-right.getHeight(),null  );
    c.drawBitmap(down, v.getWidth()/2, v.getHeight()-down.getHeight(), null);
    c.drawBitmap(left, v.getWidth()/2 - left.getWidth(), v.getHeight() - left.getHeight(), null );
    c.drawBitmap(stop, 50, v.getHeight()-stop.getHeight(), null);
}

}




}

