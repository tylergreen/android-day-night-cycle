package com.greent.nativeandroid;

import com.firebase.client.Firebase;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d("DEBUG:", "CREATED");
		
		Switch s = (Switch) findViewById(R.id.switch1);
		
		
		Firebase firebase_ref1 = new Firebase("https://greent.firebaseio.com/times");
		
		firebase_ref1.setValue("hello from android!");
		
		s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
		
		
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
			    
				Context c = getApplicationContext();
				CharSequence awake = "Rise and Shine!!";
				CharSequence asleep = "Good night.";
			
				Firebase firebase_ref = new Firebase("https://greent.firebaseio.com/times");
				Firebase pushRef = firebase_ref.push();
				
				Map<String, Object> state_change = new HashMap<String, Object>();
				Date time = new Date();
				state_change.put("time", time.getTime());
			
				if (isChecked){
					state_change.put("state", "awake");
										
					pushRef.setValue(state_change);
					Log.d("DEBUG:", "click on ");
					Toast.makeText(c, awake, Toast.LENGTH_SHORT).show();
					
				
				} else {
					state_change.put("state", "asleep");
					pushRef.setValue(state_change);
					Log.d("DEBUG:", "click off");
					Toast.makeText(c, asleep, Toast.LENGTH_SHORT).show();
				}
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	

}
