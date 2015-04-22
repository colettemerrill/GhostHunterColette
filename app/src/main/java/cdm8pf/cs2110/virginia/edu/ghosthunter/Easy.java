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

import android.util.Log;
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
public class Easy extends Activity implements View.OnTouchListener {

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
    Bitmap boom;
    int boomTick;
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
    ArrayList<Ghost> ghosts;
    Rect intersection;
    Boolean bombRelease;
    Boolean deadGhost;
    int ghostx;
    int ghosty;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        counter = 0;
        ghosts = new ArrayList<Ghost>();

        bombRelease = false;


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


    public void createBits() {
        user = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);
        ghostB = BitmapFactory.decodeResource(getResources(), R.drawable.ghost);
        ghostG = BitmapFactory.decodeResource(getResources(), R.drawable.ghost3);
        ghostP = BitmapFactory.decodeResource(getResources(), R.drawable.ghost2);
        up = BitmapFactory.decodeResource(getResources(), R.drawable.up);
        down = BitmapFactory.decodeResource(getResources(), R.drawable.down);
        left = BitmapFactory.decodeResource(getResources(), R.drawable.left);
        right = BitmapFactory.decodeResource(getResources(), R.drawable.right);
        stop = BitmapFactory.decodeResource(getResources(), R.drawable.stop);
        coin = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
        boom = BitmapFactory.decodeResource(getResources(), R.drawable.boom);
    }

    public void createMaze() {
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
        rects.add(new Rect(285, 255, 290, 705));
        rects.add(new Rect(120, 325, 195, 330));
        rects.add(new Rect(50, 405, 130, 410));
        rects.add(new Rect(195, 325, 200, 480));
        rects.add(new Rect(130, 480, 285, 485));
        //11
        rects.add(new Rect(130, 480, 135, 705));
        rects.add(new Rect(205, 560, 210, 780));
        rects.add(new Rect(130, 780, 365, 785));
        rects.add(new Rect(365, 705, 370, 860));
        rects.add(new Rect(290, 620, 455, 625));
        //16
        rects.add(new Rect(365, 330, 370, 545));
        rects.add(new Rect(365, 705, 530, 710));
        rects.add(new Rect(445, 780, 600, 785));
        rects.add(new Rect(680, 780, 755, 785));
        rects.add(new Rect(596, 255, 600, 785));
        //21
        rects.add(new Rect(675, 480, 680, 705));
        rects.add(new Rect(600, 480, 680, 485));
        rects.add(new Rect(675, 405, 755, 410));
        rects.add(new Rect(675, 250, 680, 410));
        rects.add(new Rect(475, 175, 480, 330));
        //26
        rects.add(new Rect(480, 250, 600, 255));
//        rects.add(new Rect(555, 250, 560, 330));
        rects.add(new Rect(365, 325, 480, 330));
        rects.add(new Rect(525, 405, 530, 710));
        rects.add(new Rect(445, 405, 530, 410));
        rects.add(new Rect(365, 545, 455, 550));
    }

    public void createGhosts(){

        ghosts.add(gp);
        ghosts.add(gg);
        ghosts.add(gb);




    }


    //if button is pressed

    public boolean onTouch(View v, MotionEvent event) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //up movement
                if (event.getX() <= 302 && event.getX() >= 200 && event.getY() >= 904 && event.getY() <= 1004) {
                    sprite.setXSpeed(0);
                    sprite.setYSpeed(-5);
                }

                //right movement
                if (event.getX() <= 450 && event.getX() >= 350 && event.getY() >= 1050 && event.getY() <= 1152) {
                    sprite.setXSpeed(5);
                    sprite.setYSpeed(0);
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
                //bomb button
                if (event.getX() <= 675 && event.getX() >= 575 && event.getY() >= 900 && event.getY() <= 1000) {
                    bombRelease = true;
                    boomTick = 20;
                    coinTick = 40;
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
            gg.setXSpeed(5);
            gp = new Ghost(OurView.this, ghostP);
            gp.setXSpeed(10);
            ghosts.add(gb);
            ghosts.add(gg);
            ghosts.add(gp);

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


            for (int i = 0; i < rects.size(); i++) {
                if(sprite.collision(sprite.getUserHitbox(),rects.get(i))){

                    sprite.setXSpeed(0);
                    sprite.setYSpeed(0);
                }
            }

            if(bombRelease && boomTick > 0){
                boomTick--;
                    Log.d("boomTick", ""+boomTick);

                    drawBoom(c);
                    for(int i = 0; i <ghosts.size(); i++){
                        if(Math.abs(ghosts.get(i).getX()- sprite.getX()) < 50 && Math.abs(ghosts.get(i).getY() - sprite.getY()) < 50 ){
                            ghostx = ghosts.get(i).getX();
                            ghosty = ghosts.get(i).getY();
                            ghosts.remove(ghosts.get(i));
                            deadGhost = true;


                        }

                    }




                if(boomTick == 1) {
                    bombRelease = false;
                }






            }
            if(deadGhost = true && coinTick > 0) {
                coinTick--;
                drawCoin(c, ghostx, ghosty);
            }
            else{
                deadGhost = false;
            }
            if(!bombRelease) {
                for (int i = 0; i < ghosts.size(); i++) {
                    if (sprite.collision(sprite.getUserHitbox(), ghosts.get(i).getGhostHitbox())) {

                        sprite.setXSpeed(0);
                        sprite.setYSpeed(0);

                        ghosts.get(i).setXSpeed(0);
                        ghosts.get(i).setYSpeed(0);
                        Paint p = new Paint();
                        Paint w = new Paint();
                        w.setColor(Color.WHITE);
                        Rect h = new Rect(150, 350, 500, 700);
                        c.drawRect(h, w);
                        p.setColor(Color.RED);
                        p.setTextSize(100);
                        c.drawText("GAME OVER! ", 150, 400, p);
                        c.drawText("Final Score: " + counter / 10, 150, 500, p);
                        p.setTextSize(20);
                        c.drawText("To play again, hit back button and select level", 100, 600, p);

                        ok = false;
                    }
                }
            }


            for(int i = 0; i < ghosts.size(); i++){
                ghosts.get(i).onDraw(c);

            }


            score(c);

            drawButtons(c);



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
                            Paint p = new Paint();
                            p.setColor(Color.WHITE);
                            Rect r = new Rect(575, 900, 675, 1000 );
                            c.drawRect(r, p);
                            p.setColor(Color.RED);
                            p.setTextSize(35);
                            p.setFakeBoldText(true);
                            c.drawText("BOMB", 575, 960,  p);

                        }

                        //draws the coin
                        public void drawCoin(Canvas c, int x, int y) {
                            c.drawBitmap(coin, x, y, null);
                        }
        public void drawBoom(Canvas c) {

            c.drawBitmap(boom, sprite.getX()-20, sprite.getY()-20, null);

                bombRelease = true;



        }


public void score(Canvas c){
    Paint p = new Paint();
    p.setColor(Color.RED);
    p.setTextSize(50);
    c.drawText("Score: " + counter/10, 500, 50, p);
}


        public void drawMaze(Canvas c) {
            Paint p = new Paint();
            c.drawPaint(p);
            p.setColor(Color.WHITE);
            p.setStrokeWidth(20);

            for (int i = 0; i < rects.size(); i++) {
                c.drawRect(rects.get(i), p);
            }

        }


    }
}
