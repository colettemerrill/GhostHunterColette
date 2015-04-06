package cdm8pf.cs2110.virginia.edu.ghosthunter;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by colettemerrill on 4/5/15.
 */
public class Easy extends Activity {

    MediaPlayer backgroundMusic;
    Draw maze;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backgroundMusic = MediaPlayer.create(this, R.raw.logo_song);
        backgroundMusic.start();


        maze = new Draw(this);


        setContentView(maze);





    }
}
