package com.example.upclosesettings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class settingsNew extends Activity {

	private static final String MyPreferences = "MyPref";
	Button f, m;
	Button km, mi;
	TextView genderTV, showMeValueTV, limitSearchValueTV;
	SeekBar distanceSB;
	CheckBox menCB, womenCB;
	int distance = 100;
	float distanceInMiles;
	boolean distance_in_km = true, distance_in_miles = false;
	String showMe = "";
	SharedPreferences sharedPref;

	public static final String Gender = "genderKey";
	public static final String Dist = "distanceKey";
	public static final String Interested = "interestedInKey";
	public static final String DistUnit = "distanceUnitKey";
	public static final String Vibrate = "vibrateKey";
	public static final String Sound = "soundKey";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		initialise();
	}

	public void initialise() {
		f = (Button) findViewById(R.id.female_button);
		m = (Button) findViewById(R.id.male_button);
		km = (Button) findViewById(R.id.km_button);
		mi = (Button) findViewById(R.id.miles_button);
		genderTV = (TextView) findViewById(R.id.genderTV);
		limitSearchValueTV = (TextView) findViewById(R.id.limitSearchValueTV);
		distanceSB = (SeekBar) findViewById(R.id.distanceSB);
		distanceSB.setProgress(distance);
		menCB = (CheckBox) findViewById(R.id.menCB);
		womenCB = (CheckBox) findViewById(R.id.womenCB);
		showMeValueTV = (TextView) findViewById(R.id.showMeValueTV);
		sharedPref = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

	}
/*
	private void setSavedValues() {

			String savedGender = sharedPref.getString(Gender,"");
			genderTV.setText(savedGender);
			if (savedGender == "female")
				setFemaleButton();
			else
				setMaleButton();
			
			Set the Distance Unit
				String unit = sharedPref.getString(DistUnit, "Km");
				if(unit=="Km")
			}
		
		}

		if (sharedPref.contains(Dist)) {
			distance = Integer.parseInt(sharedPref.getString(Dist, ""));
			limitSearchValueTV.setText("" + distance);
		}

		if (sharedPref.contains(DistUnit)) {
			String temp = sharedPref.getString(DistUnit, "Km");
			if (temp == "Km") {
				distance_in_km = true;
				distance_in_miles = false;

			} else {
				distance_in_km = false;
				distance_in_miles = true;
			}
		}

	}

	public void onClick(View v) {
		SharedPreferences sharedPref=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
		Editor editor = sharedPref.edit();
		switch (v.getId()) {
		case R.id.female_button:
			editor.putString(Gender, "Female");
			setFemaleButton();
			break;
		case R.id.male_button:
			editor.putString(Gender, "Male");
			setMaleButton();
			break;

		case R.id.km_button:
			editor.putString(DistUnit, "Km");
			setKmButton();
			break;

		case R.id.miles_button:
			editor.putString(DistUnit, "Mi");
			setMilesButton();
			break;

		case R.id.menCB:
			if (menCB.isChecked()) {
				showMe = "Men";
			}
			if (menCB.isChecked() && womenCB.isChecked()) {
				showMe = "Men,Women";
			} else {
				showMe = "";
			}
			showMeValueTV.setText(showMe);
			break;

		case R.id.womenCB:
			if (womenCB.isChecked()) {
				showMe = "Women";

			}
			if (menCB.isChecked() && womenCB.isChecked()) {
				showMe = "Men,Women";
			} else
				showMe = "";
			showMeValueTV.setText(showMe);
			break;

		}
		
		editor.commit();

		distanceSB.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				// TODO Auto-generated method stub

				if (distance_in_km)
					limitSearchValueTV.setText(String.format("%d Km",
							distanceSB.getProgress()));
				else
					limitSearchValueTV.setText(String.format("%d Mi",
							distanceSB.getProgress()));

			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	private void setMaleButton() {
		m.setBackgroundResource(R.drawable.round_corners_blue);
		m.setTextColor(Color.WHITE);
		f.setBackgroundResource(R.drawable.round_corners);
		f.setTextColor(Color.BLACK);
		genderTV.setText("Male");

	}

	private void setFemaleButton() {
		f.setBackgroundResource(R.drawable.round_corners_blue);
		f.setTextColor(Color.WHITE);
		m.setBackgroundResource(R.drawable.round_corners);
		m.setTextColor(Color.BLACK);
		genderTV.setText("Female");

	}

	private void setMilesButton() {
		mi.setBackgroundResource(R.drawable.round_corners_blue);
		mi.setTextColor(Color.WHITE);
		km.setBackgroundResource(R.drawable.round_corners);
		km.setTextColor(Color.BLACK);
		distance = distanceSB.getProgress();

		if (distance_in_km) {
			distance = (int) ((distance) * 1.609);
			distance_in_km = false;
			distance_in_miles = true;
		}
		distance = Math.min(distance, 100);
		distanceSB.setProgress((int) distance);
		limitSearchValueTV.setText(String.format("%d Mi", (int) distance));

	}

	private void setKmButton() {
		km.setBackgroundResource(R.drawable.round_corners_blue);
		km.setTextColor(Color.WHITE);
		mi.setBackgroundResource(R.drawable.round_corners);
		mi.setTextColor(Color.BLACK);
		distance = distanceSB.getProgress();

		if (distance_in_miles) {
			distance = (int) (distance / 1.609);
			distance_in_miles = false;
			distance_in_km = true;
		}
		limitSearchValueTV.setText(String.format("%d Km", distance));
		distanceSB.setProgress(distance);

	}
}*/
}	