package com.example.barrelrace;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RaceFieldActivity extends Activity{

		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_race_field_activity);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.race__field, menu);
		return true;
	}

}
