package cdm8pf.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.content.Context;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
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
import java.util.Random;


/**
 * Created by colettemerrill on 4/5/15.
 */
public class Easy extends Activity implements View.OnTouchListener {

    MediaPlayer backgroundMusic;
    MediaPlayer soundEffect;

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
    Bitmap bomb;
    Bitmap shoe;
    int money;
    int boomTick;
    int speedTick;
    int score;
    int bombX;
    int bombY;
    int shoeX;
    int shoeY;
    int rghostX;
    int rghostY;
    boolean bombRelease;
    int counter;
    int coinTick;
    int shoeTick;
    int collectibleTick;

    int x1;
    int y1;
    int x2;
    int y2;
    int x3;
    int y3;
    int x4;
    int y4;





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
    Boolean bombCollectible;
    Boolean shoeCollectible;
    Boolean deadGhost;
    int ghostx;
    int ghosty;
    int currentFrame = 0;
    ArrayList<Point> points;
    ArrayList<Point> coins;

    //what happens when game is started
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        counter = 0;
        money = 0;

        ghosts = new ArrayList<Ghost>();
        points = new ArrayList<Point>();
        coins = new ArrayList<Point>();
        coinTick = 70;

        bombCollectible = false;
        bombRelease = false;
        shoeCollectible = true;




        backgroundMusic = MediaPlayer.create(this, R.raw.imagine_dragons);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();
        v = new OurView(this);


        setContentView(v);

        v.setOnTouchListener(this);
        createBits();
        createMaze();

         x1 = 0;
         y1 = -5;
         x2 = 5;
         y2 = 0;
         x3 = 0;
         y3 = 5;
         x4 = -5;
         y4 = 0;


    }

//if program is paused

    protected void onPause() {
        super.onPause();
        backgroundMusic.release();
        v.pause();
    }

//if program is resumed
    protected void onResume() {
        super.onResume();
        v.resume();
    }


//creates all the bitmaps used
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
        bomb = BitmapFactory.decodeResource(getResources(), R.drawable.bomb);
        shoe = BitmapFactory.decodeResource(getResources(), R.drawable.shoe);

    }

    //creates the maze rectangle parts
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
                    sprite.setXSpeed(x1);
                    sprite.setYSpeed(y1);
                }

                //right movement
                if (event.getX() <= 450 && event.getX() >= 350 && event.getY() >= 1050 && event.getY() <= 1152) {
                    sprite.setXSpeed(x2);
                    sprite.setYSpeed(y2);
                }
                //down movement

                if (event.getX() <= 302 && event.getX() >= 200 && event.getY() >= 1050 && event.getY() <= 1152) {
                    sprite.setXSpeed(x3);
                    sprite.setYSpeed(y3);
                }

                //left movement
                if (event.getX() <= 150 && event.getX() >= 50 && event.getY() >= 1050 && event.getY() <= 1152) {
                    sprite.setXSpeed(x4);
                    sprite.setYSpeed(y4);
                }

                //stop movement
                if (event.getX() <= 675 && event.getX() >= 575 && event.getY() >= 1050 && event.getY() <= 1152) {
                    sprite.setXSpeed(0);
                    sprite.setYSpeed(0);
                }
                //bomb button
                if (event.getX() <= 675 && event.getX() >= 575 && event.getY() >= 900 && event.getY() <= 1000) {
                    if(sprite.getNumBombs() > 0) {
                        bombRelease = true;

                    }
                    bombCollectible= false;
                    boomTick = 20;


                }
                if (event.getX() <= 150 && event.getX() >= 50 && event.getY() >= 25 && event.getY() <= 70) {
                   if(money >=50){
                       money = money - 50;
                       sprite.setNumBombs(sprite.getNumBombs()+1);
                   }

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
            currentFrame=++currentFrame % 8;
            counter++;
            score++;
            //c.drawPicture(level_background.png);
            c.drawARGB(150, 0, 0, 0);
            drawMaze(c);

            //draws sprite and ghosts to screen
            sprite.onDraw(c);
            for(int i = 0; i < ghosts.size(); i++){
                ghosts.get(i).onDraw(c);
            }


            //sprite maze collision
            for (int i = 0; i < rects.size(); i++) {
                if(sprite.collision(sprite.getUserHitbox(),rects.get(i))){

                    sprite.setXSpeed(0);
                    sprite.setYSpeed(0);
                }
            }

            //draws boom if a bomb is released, and removes any ghosts hit
            if(bombRelease && boomTick > 0){
                boomTick--;
                 drawBoom(c);
                    for(int i = 0; i <ghosts.size(); i++){
                        if(Math.abs(ghosts.get(i).getX()- sprite.getX()) < 50 && Math.abs(ghosts.get(i).getY() - sprite.getY()) < 50 ){
                            ghostx = ghosts.get(i).getX();
                            ghosty = ghosts.get(i).getY();
                            coins.add(new Point(ghostx, ghosty));
                            ghosts.remove(ghosts.get(i));
                            score = score + 200;
                            deadGhost = true;
                            coinTick = 70;
                        }
                    }
                if(boomTick == 1) {
                    sprite.setNumBombs(sprite.getNumBombs()-1);
                    bombRelease = false;
                }
            }

            //draws a coin where a ghost was removed
            if(deadGhost = true && coinTick > 0) {
                coinTick--;
                for(int i = 0; i < coins.size(); i++) {
                    drawCoin(c, coins.get(i).x, coins.get(i).y);
                    if(Math.abs(sprite.getX()-coins.get(i).x) < 10 && Math.abs(sprite.getY() - coins.get(i).y) < 10){
                      money = money + 10;
                        Paint p = new Paint();
                        p.setColor(Color.RED);
                        p.setTextSize(40);
                        c.drawText("+ 10", coins.get(i).x, coins.get(i).y, p);
                        coins.remove(i);
                        i = i-1;
                    }
                }

            }
            else{
                deadGhost = false;
            }

            for(int i = 0; i < ghosts.size(); i++){
                if(Math.abs(sprite.getX() - ghosts.get(i).getX()) < 40 && Math.abs(sprite.getY() - ghosts.get(i).getY()) < 40 ){
                    Toast t = Toast.makeText(getApplicationContext(), "Ghost Near!", Toast.LENGTH_SHORT);
                    t.show();
                }
            }


            //ghost sprite collisions and ending the game
            if(!bombRelease) {
                for (int i = 0; i < ghosts.size(); i++) {
                    if (sprite.collision(sprite.getUserHitbox(), ghosts.get(i).getGhostHitbox())) {
                        sprite.setXSpeed(0);
                        sprite.setYSpeed(0);
                        ghosts.remove(i);
                        Paint p = new Paint();
                        Paint w = new Paint();
                        w.setColor(Color.WHITE);
                        Rect h = new Rect(50, 150, 750, 700);
                        c.drawRect(h, w);
                        p.setColor(Color.RED);
                        p.setTextSize(100);
                        c.drawText("GAME OVER! ", 100, 400, p);
                        c.drawText("Final Score: " + score/10, 100, 500, p);
                        p.setTextSize(20);
                        c.drawText("To play again, hit back button and select level", 200, 600, p);

                        ok = false;
                    }
                }
            }






            //puts a bomb out every certain amount time
            if(counter % 1000 == 0) {
                bombCollectible = true;
                collectibleTick = 300;
                bombX = randomBomb().x;
                bombY= randomBomb().y;
            }
            if(bombCollectible == true && collectibleTick > 0) {
                collectibleTick--;
                c.drawBitmap(bomb, bombX, bombY, null);
                if(Math.abs(sprite.getX()-bombX) < 10 && Math.abs(sprite.getY() -bombY) < 10){
                    sprite.setNumBombs(sprite.getNumBombs()+1);
                    bombCollectible = false;
                }
            }
            else{
                bombCollectible = false;
            }


            //puts a shoe out
            if(counter == 50) {
                shoeCollectible = true;
                shoeTick = 500;
                shoeX = randomShoe().x;
                shoeY= randomShoe().y;
            }
            if(shoeCollectible == true && shoeTick > 0) {
                shoeTick--;
                c.drawBitmap(shoe, shoeX, shoeY, null);
                if (Math.abs(sprite.getX() - (shoeX+10)) < 20 && Math.abs(sprite.getY() - (shoeY+10)) < 20) {
                    shoeCollectible = false;
                    speedTick = 100;
                }
            }
            else if(speedTick > 0){
                    speedTick--;
                    x1 = 0;
                    y1 = -10;
                    x2 = 10;
                    y2 = 0;
                    x3 = 0;
                    y3 = 10;
                    x4 = -10;
                    y4 = 0;
            }
            else{
                shoeCollectible = false;
                x1 = 0;
                y1 = -5;
                x2 = 5;
                y2 = 0;
                x3 = 0;
                y3 = 5;
                x4 = -5;
                y4 = 0;
            }

            //displays the score, number of buttons, and draws buttons to screen
                score(c);
                displayBombNum(c);
                drawButtons(c);
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
                            int width = coin.getWidth()/8;
                            int direction = 0;
                            int height = coin.getHeight();
                            int srcX = currentFrame * width;
                            int srcY = direction * height;
                            Rect src = new Rect(srcX, srcY, srcX+ width, srcY + height);
                            Rect d = new Rect(x, y, x+width, y+height);

                           c.drawBitmap(coin, src, d, null);
                        }


       //draws the boom image for when a bomb is released
        public void drawBoom(Canvas c) {
            c.drawBitmap(boom, sprite.getX()-20, sprite.getY()-20, null);
                bombCollectible = true;
            

        }

//draws the current score
public void score(Canvas c){
    Paint p = new Paint();
    p.setColor(Color.RED);
    p.setTextSize(50);
    c.drawText("Score: " + score/10, 500, 50, p);
}

        //displays the number of bombs a user has
        public void displayBombNum(Canvas c) {
            Paint p = new Paint();
            p.setColor(Color.RED);
            p.setTextSize(50);
            c.drawText("Bombs: " + sprite.getNumBombs(), 275, 50, p);


            p.setTextSize(30);
            drawCoin(c, 100, 30);
            c.drawText(""+money, 150, 50, p);


        }


        //draws maze to canvas
        public void drawMaze(Canvas c) {
            Paint p = new Paint();
            c.drawPaint(p);
            p.setColor(Color.WHITE);
            p.setStrokeWidth(20);

            for (int i = 0; i < rects.size(); i++) {
                c.drawRect(rects.get(i), p);
            }

        }

        //random location for bomb
        public Point randomBomb(){
            Point p = new Point(100, 200);
            Point p1 = new Point(200, 200);
            Point p2 = new Point(300, 500);
            Point p3 = new Point(400, 500);
            Point p4 = new Point(500, 700);
            points.add(p);
            points.add(p1);
            points.add(p2);
            points.add(p3);
            points.add(p4);

            Random rand = new Random();
            int random = rand.nextInt(4);
            Point generated = new Point (points.get(random).x,points.get(random).y);
            return generated;



        }

        //random location for a shoe
        public Point randomShoe(){
            Point p = new Point(100, 200);
            Point p1 = new Point(200, 200);
            Point p2 = new Point(300, 500);
            Point p3 = new Point(400, 500);
            Point p4 = new Point(500, 700);
            points.add(p);
            points.add(p1);
            points.add(p2);
            points.add(p3);
            points.add(p4);

            Random rand = new Random();
            int random = rand.nextInt(4);
            Point generated = new Point (points.get(random).x,points.get(random).y);
            return generated;



        }

        //random location for a ghost
        public Point randomGhost(){
            Point p = new Point(100, 200);
            Point p1 = new Point(200, 200);
            Point p2 = new Point(300, 500);
            Point p3 = new Point(400, 500);
            Point p4 = new Point(500, 700);
            points.add(p);
            points.add(p1);
            points.add(p2);
            points.add(p3);
            points.add(p4);

            Random rand = new Random();
            int random = rand.nextInt(4);
            Point generated = new Point (points.get(random).x,points.get(random).y);
            return generated;
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


    }
}
