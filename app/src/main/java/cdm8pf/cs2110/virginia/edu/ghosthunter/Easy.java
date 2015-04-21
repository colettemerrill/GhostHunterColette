package cdm8pf.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.content.Context;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.graphics.Bitmap;

import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.*;

import java.lang.*;
import java.util.ArrayList;


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
    int counter;
    int coinTick;
    Paint p;
    Ghost gb;
    Ghost gg;
    Ghost gp;
    Bitmap ghostB;
    Bitmap ghostG;
    Bitmap ghostP;
    ArrayList<Rect> rects;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        counter = 0;

        backgroundMusic = MediaPlayer.create(this, R.raw.logo_song);
        backgroundMusic.start();
        v = new OurView(this);




        setContentView(v);

       v.setOnTouchListener(this);
        createBits();
        createMaze();




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


    public void createBits(){
        user = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);
        ghostB = BitmapFactory.decodeResource(getResources(), R.drawable.ghost);
        ghostG = BitmapFactory.decodeResource(getResources(), R.drawable.ghost3);
        ghostP = BitmapFactory.decodeResource(getResources(), R.drawable.ghost2);
        up = BitmapFactory.decodeResource(getResources(),  R.drawable.up);
        down = BitmapFactory.decodeResource(getResources(),  R.drawable.down);
        left = BitmapFactory.decodeResource(getResources(),  R.drawable.left);
        right = BitmapFactory.decodeResource(getResources(),  R.drawable.right);
        stop = BitmapFactory.decodeResource(getResources(), R.drawable.stop);
        coin = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
    }
    public void createMaze(){
        rects = new ArrayList<Rect>();
        rects.add(new Rect(50, 100, 750, 105));
        rects.add(new Rect(50, 100, 55, 860));
        rects.add(new Rect(50, 860, 750, 865));
        rects.add(new Rect(750, 100, 755, 865));
        //1
        rects.add(new Rect(120, 175, 125, 250));
        rects.add(new Rect(120, 175, 325, 180));
        rects.add(new Rect(400, 100, 405, 250));
        rects.add(new Rect(210, 250, 405, 255));
        rects.add(new Rect(555, 175, 670, 180));
        //6
        rects.add(new Rect(285,255,290,705));
        rects.add(new Rect(120,325,195,330));
        rects.add(new Rect(50,405,130,410));
        rects.add(new Rect(195,325,200,480));
        rects.add(new Rect(130,480,285,485));
        //11
        rects.add(new Rect(130, 480, 135, 705));
        rects.add(new Rect(205, 560, 210, 780));
        rects.add(new Rect(130, 780, 365, 785));
        rects.add(new Rect(365, 705, 370, 860));
        rects.add(new Rect(290, 620, 455, 625));
        //16
        rects.add(new Rect(365,330 ,370, 545));
        rects.add(new Rect(365,705,530,710));
        rects.add(new Rect(445, 780, 600, 785));
        rects.add(new Rect(680,780,755,785));
        rects.add(new Rect(596, 255, 600, 785));
        //21
        rects.add(new Rect(675, 480,680, 705));
        rects.add(new Rect(600, 480,680,485));
        rects.add(new Rect(675, 405, 755 , 410));
        rects.add(new Rect(675, 250, 680, 410));
        rects.add(new Rect(475,175,480, 330));
        //26
        rects.add(new Rect(480, 250, 600, 255));
//        rects.add(new Rect(555, 250, 560, 330));
        rects.add(new Rect(365, 325, 480, 330));
        rects.add(new Rect(525, 405, 530, 710));
        rects.add(new Rect(445, 405, 530, 410));
        rects.add(new Rect(365, 545, 455, 550));



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
                if(event.getX() <= 302 && event.getX() >= 200 && event.getY() >= 904 && event.getY() <= 1004) {
                    sprite.setXSpeed(0);
                    sprite.setYSpeed(-5);
                }

                    //right movement
                    if (event.getX() <= 450 && event.getX() >= 350 && event.getY() >= 1050 && event.getY() <= 1152) {

                        sprite.setXSpeed(5);
                        sprite.setYSpeed(0);
//
                    }
                    //down movement

                    if (event.getX() <= 302 && event.getX() >= 200 && event.getY() >= 1050 && event.getY() <= 1152) {



                            sprite.setXSpeed(0);
                            sprite.setYSpeed(5);
                    }
                        //left movement

                        if (event.getX() <= 150 && event.getX() >= 50 && event.getY() >= 1050 && event.getY() <= 1152) {



                                sprite.setXSpeed(-5);
                                sprite.setYSpeed(0);
                        }
                            //stop movement
                            if (event.getX() <= 675 && event.getX() >= 575 && event.getY() >= 1050 && event.getY() <= 1152) {
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
                    public class OurView extends SurfaceView implements Runnable {

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
                            gb = new Ghost(OurView.this, ghostB);
                            gg = new Ghost(OurView.this, ghostG);
                            gp = new Ghost(OurView.this, ghostP);
                            p = new Paint();
                            while (ok == true) {

                                if (!holder.getSurface().isValid()) {
                                    continue;
                                }
                                Canvas c = holder.lockCanvas();
                                onDraw(c);
                                holder.unlockCanvasAndPost(c);


                            }


                        }

                        //What is being drawn each time
                        protected void onDraw(Canvas c) {
                            super.onDraw(c);
                            counter++;
                            //c.drawPicture(level_background.png);
                            c.drawARGB(150, 0, 0, 0);
                            drawMaze(c);

                            sprite.onDraw(c);
                            gb.onDraw(c);
                            gp.onDraw(c);
                            gp.setXSpeed(10);
                            gg.onDraw(c);
                            gg.setXSpeed(20);
                            score(c);

                            drawButtons(c);


//        if(counter % 100 == 0) {
//            if(counter - coinTick < 400) {
//                drawCoin(c, 300, 300);
//            }
//
//        }
                        }

                        //if game is paused
                        public void pause() {

                            ok = false;
                            while (true) {
                                try {
                                    t.join();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            t = null;

                            backgroundMusic.release();
                        }

                        //if game is resumed
                        public void resume() {
                            ok = true;
                            t = new Thread(this);
                            t.start();
                        }


                        //draws the buttons
                        public void drawButtons(Canvas c) {
                            c.drawBitmap(up, 200, 904, null);
                            c.drawBitmap(left, 50, 1050, null);
                            c.drawBitmap(right, 350, 1050, null);
                            c.drawBitmap(down, 200, 1050, null);
                            c.drawBitmap(stop, 575, 1050, null);

                        }

                        //draws the coin
                        public void drawCoin(Canvas c, int x, int y) {
                            coinTick = counter;
                            c.drawBitmap(coin, x, y, null);
                        }


public void score(Canvas c){
    Paint p = new Paint();
    p.setColor(Color.RED);
    p.setTextSize(30);
    c.drawText("Score: " + counter, 500, 100, p);
}

                        public void drawMaze(Canvas c){
                            Paint p = new Paint();
                            c.drawPaint(p);
                            p.setColor(Color.WHITE);
                            p.setStrokeWidth(20);

                                for(int i = 0; i < rects.size(); i++) {
                                    c.drawRect(rects.get(i), p);
                                }





                        }

                    }


                }
