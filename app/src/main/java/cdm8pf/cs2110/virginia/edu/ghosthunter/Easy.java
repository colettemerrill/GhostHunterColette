package cdm8pf.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

/**
 * Created by colettemerrill on 4/5/15.
 */
public class Easy extends Activity implements View.OnTouchListener {

    MediaPlayer backgroundMusic;

    Draw maze;
    Bitmap user;
    OurView v;
    Sprite sprite;
    Bitmap p;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backgroundMusic = MediaPlayer.create(this, R.raw.logo_song);
        backgroundMusic.start();
        v = new OurView(this);
        setContentView(v);
        p = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);
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
        return false;
    }



public class OurView extends SurfaceView implements Runnable {

    Thread t = null;
    SurfaceHolder holder;
    boolean ok = false;
    boolean spriteLoaded = false;

    public OurView(Context context) {
        super(context);
        holder = getHolder();
    }

    @Override
    public void run() {
        while(ok == true){
            if(!holder.getSurface().isValid()) {
                continue;
            }

            if(!spriteLoaded) {
                sprite = new Sprite(OurView.this, p);
                spriteLoaded = true;
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
        backgroundMusic.release();
    }
    public void resume(){
        ok = true;
        t = new Thread(this);
        t.start();
    }
}




}

