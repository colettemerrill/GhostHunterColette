package cdm8pf.cs2110.virginia.edu.ghosthunter;

import android.app.ActionBar;
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

import java.io.File;
import java.lang.*;
import java.util.Scanner;

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
    Ghost g1;
    Bitmap ghost;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        counter = 0;

        backgroundMusic = MediaPlayer.create(this, R.raw.logo_song);
        backgroundMusic.start();
        v = new OurView(this);



        setContentView(v);

       v.setOnTouchListener(this);
        user = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);
        ghost = BitmapFactory.decodeResource(getResources(), R.drawable.ghost);
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
                            g1 = new Ghost(OurView.this, ghost);
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
                            g1.onDraw(c);
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
                            p.setStrokeWidth(100);

                            c.drawRect(50, 50, 100, 100, p);

                        }

//                        public void loadMaze(String filename) throws Exception {
//                            // TODO: fill in this method to read the csv file and
//                            // populate a list of obstacle Rectangles
//                            Scanner inputFile = new Scanner(new File(filename));
//                            while (inputFile.hasNextLine()) {
//                                String[] course = inputFile.nextLine().split(",");
//                                Rect line = new Rect();
//                                obs.setLocation(Integer.parseInt(course[0]),
//                                        Integer.parseInt(course[1]));
//                                obs.setSize(Integer.parseInt(course[2]),
//                                        Integer.parseInt(course[3]));
//                                obstacles.add(obs);
//                            }
//                            obs1 = obstacles.get(0);
//                            obs2 = obstacles.get(1);
//                            obs3 = obstacles.get(2);
//
//                        }





                    }


                }