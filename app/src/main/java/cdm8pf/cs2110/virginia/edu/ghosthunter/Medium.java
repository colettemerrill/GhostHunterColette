package cdm8pf.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by colettemerrill on 4/5/15.
 */
public class Medium extends Activity {

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
    int counter;
    int coinTick;
    Paint p;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        counter = 0;

        backgroundMusic = MediaPlayer.create(this, R.raw.logo_song);
        backgroundMusic.start();
        setContentView(R.layout.medium);


        user = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);
        up = BitmapFactory.decodeResource(getResources(),  R.drawable.up);
        down = BitmapFactory.decodeResource(getResources(),  R.drawable.down);
        left = BitmapFactory.decodeResource(getResources(),  R.drawable.left);
        right = BitmapFactory.decodeResource(getResources(),  R.drawable.right);
        stop = BitmapFactory.decodeResource(getResources(), R.drawable.stop);
        coin = BitmapFactory.decodeResource(getResources(), R.drawable.coin);


    }


//    public void run() {
//        while(ok == true){
//
//            if(!holder.getSurface().isValid()) {
//                continue;
//            }
//            Canvas c = holder.lockCanvas();
//            onDraw(c);
//            holder.unlockCanvasAndPost(c);
//
//
//
//
//        }
//
//
//
//    }
//
//    protected void onPause() {
//        super.onPause();
//        backgroundMusic.release();
//        v.pause();
//    }
//
//    protected void onResume() {
//        super.onResume();
//        v.resume();
//    }

}
