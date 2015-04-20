package cdm8pf.cs2110.virginia.edu.ghosthunter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.*;


 public class OurView extends SurfaceView{

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
     user = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);

 }
     public OurView(Context context, AttributeSet attrs){
         super(context, attrs);
         user = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);
         holder = getHolder();
     }
     public OurView(Context context, AttributeSet attrs, int defStyle){
         super(context, attrs, defStyle);
         user = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);
         holder = getHolder();

     }

 //What is being drawn each time
 protected void onDraw(Canvas c) {
  sprite = new Sprite(this, user);
 //c.drawPicture(level_background.png);
 c.drawARGB(150, 0, 0, 0);
 sprite.onDraw(c);
 }




 }