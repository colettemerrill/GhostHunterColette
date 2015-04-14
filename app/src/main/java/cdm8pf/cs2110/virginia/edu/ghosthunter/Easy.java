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
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(event.getX() < 420 && event.getY() > 380){
                    sprite.setXSpeed(0);
                    sprite.setYSpeed(-5);
                    Toast toast = Toast.makeText(getApplicationContext(), "pressed", Toast.LENGTH_LONG);
                    toast.show();
                }

                break;
            case MotionEvent.ACTION_UP:



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
        c.drawBitmap(up, 400, 300, null);

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




}




}

