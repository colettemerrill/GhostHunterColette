package cdm8pf.cs2110.virginia.edu.ghosthunter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.*;


 public class OurView extends SurfaceView implements Runnable{

     MediaPlayer backgroundMusic;
     OurView v;
     Sprite sprite;
     Bitmap user;
     Bitmap up;
     Bitmap down;
     Bitmap right;
     Bitmap left;

 Thread t = null;
 SurfaceHolder holder;
 boolean ok = true;


 public OurView(Context context) {
 super(context);
 holder = getHolder();

 LinearLayout l = new LinearLayout(context);
 Button u = new Button(context);
 u.setX(200);
 u.setY(300);
 l.addView(u);


 }

 @Override
 public void run() {
 sprite = new Sprite(this, user);
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
 }

 }