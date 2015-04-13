package cdm8pf.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

/**
 * Created by colettemerrill on 4/5/15.
 */
public class Easy extends Activity implements View.OnTouchListener{

    MediaPlayer backgroundMusic;

    Draw maze;
    OurView v;
    Sprite sprite;
    Bitmap user;
    Button up;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backgroundMusic = MediaPlayer.create(this, R.raw.logo_song);
        backgroundMusic.start();
        v = new OurView(this);
        up = new Button(this);
        up.setText("up");
        up.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
        up.setVisibility(View.VISIBLE);
        v.addView(up);
        setContentView(v);

        user = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);
    }


    protected void onPause() {
        super.onPause();
        v.pause();
    }

    protected void onResume() {
        super.onResume();
        v.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try{

            Thread.sleep(50); //20 times per seconds
        }
        catch ( InterruptedException e){
            e.printStackTrace();
        }
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                sprite.setXSpeed(10);
                break;

            case MotionEvent.ACTION_UP:
                sprite.setYSpeed(1);
                break;
        }

        return true;
    }


    public class OurView extends SurfaceView implements Runnable{

    Thread t = null;
    SurfaceHolder holder;
    boolean ok = true;


    public OurView(Context context) {
        super(context);
        holder = getHolder();
    }
    public void addView(Button up) {

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

    protected void onDraw(Canvas c) {
        //c.drawPicture(level_background.png);
        sprite.onDraw(c);

    }


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

