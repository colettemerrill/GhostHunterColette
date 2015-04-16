package cdm8pf.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.content.Context;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.graphics.Bitmap;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;


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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                    sprite.setXSpeed(0);
                    sprite.setYSpeed(-5);
                    Toast toast = Toast.makeText(getApplicationContext(), "pressed", Toast.LENGTH_SHORT);
                    toast.show();
                }
                //right movement
                if(event.getX() > 30 && event.getX() < 200){
                    sprite.setXSpeed(5);
                    sprite.setYSpeed(0);
                Toast toast = Toast.makeText(getApplicationContext(), "pressed", Toast.LENGTH_SHORT);
                    toast.show();
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

//    @Override
//    public void onClick(View v) {
//
//        if(v.getX() > 30 && v.getX() < 200){
//            sprite.setXSpeed(5);
//            sprite.setYSpeed(0);
//            Toast toast = Toast.makeText(getApplicationContext(), "pressed", Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }

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
    c.drawBitmap(up, v.getWidth()/2 , v.getHeight()-2*up.getHeight(), null);

    c.drawBitmap(right, v.getWidth()/2 + right.getWidth(), v.getHeight()-right.getHeight(),null  );
    c.drawBitmap(down, v.getWidth()/2, v.getHeight()-down.getHeight(), null);
    c.drawBitmap(left, v.getWidth()/2 - left.getWidth(), v.getHeight() - left.getHeight(), null );
}

}




}

