package cdm8pf.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by colettemerrill on 4/5/15.
 */
public class Hard extends Activity {
    MediaPlayer backgroundMusic;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backgroundMusic = MediaPlayer.create(this, R.raw.logo_song);
        backgroundMusic.start();


        setContentView(R.layout.hard);


    }

    @Override
    protected void onPause() {
        super.onPause();

        //"releases" or stops music on onPause() method
        backgroundMusic.release();
    }
}
