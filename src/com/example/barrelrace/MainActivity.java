package com.example.barrelrace;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/**----------------------------------------------------------------- */
		/** Create animation for PLAY button */
		AnimationDrawable rocketAnimation;
		ImageButton rocketImage = (ImageButton) findViewById(R.id.button_play);
	    rocketImage.setBackgroundResource(R.drawable.horse_dance);
	    rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
	    rocketAnimation.start();
	    /**----------------------------------------------------------------- */
		/** Create animation for BARREL RACE title */
		ImageView imgViewBarrelTitle = (ImageView) findViewById(R.id.image_barrel_race); 
	    Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.drawable.barrel_race_title);
	    imgViewBarrelTitle.startAnimation(myFadeInAnimation);
	}
	
	
	/**----------------------------------------------------------------- */	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**----------------------------------------------------------------- */
	
	
	
	/**----------------------------------------------------------------- */
    /** Called when the PLAY BUTTON on the 1st screen is pressed. */
    public void playButtonPressed(View view) {
    	Intent i = new Intent(this, PlayGame.class);
		//i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
    /**----------------------------------------------------------------- */
    /** Called when the QUIT BUTTON on the 1st screen is pressed. */
    public void quitButtonPressed(View view){
    	finish();
    }
    
    /**----------------------------------------------------------------- */
    /** Called when the VIEW SCORE on the 1st screen is pressed. */
    public void viewScoreButtonPressed(View view){
    	Intent i = new Intent(this, RaceFieldActivity.class);
		//i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
