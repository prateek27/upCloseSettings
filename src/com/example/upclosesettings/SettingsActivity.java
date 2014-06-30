package com.example.upclosesettings;

import com.example.upclosesettings.RangeSeekBar.OnRangeSeekBarChangeListener;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SettingsActivity extends Activity {

	protected static final String TAG = null;
	Button f, m;
	Button km, mi;
	LinearLayout ageSeekBar;
	TextView genderTV, showMeValueTV, limitSearchValueTV, ageRangeTV;
	SeekBar distanceSB;
	CheckBox menCB, womenCB;
	int distance = 100;
	boolean anyChanges;
	String showMe = "";
	SharedPreferences sharedPref;
	Editor editor;
	String interestedInText = "";
	boolean menCheck, womenCheck;
	RangeSeekBar<Integer> seekBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		initialise();
		seekBarChange();
		ageSeekBar();
		setSavedValues();
		
		if(anyChanges)
			Log.i("Hi","Update It");
		else
			Log.i("Bi","Dont Update");
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
		ageRangeTV = (TextView) findViewById(R.id.ageRangeTV);
		ageSeekBar = (LinearLayout) findViewById(R.id.ageSeekBar);
		seekBar = new RangeSeekBar<Integer>(18, 80, getBaseContext());
		
		anyChanges=false;
		sharedPref = SettingsActivity.this.getPreferences(MODE_PRIVATE);
		editor = sharedPref.edit();

	}

	public void setSavedValues() {

		// Set the gender Text View and Button
		String gender = sharedPref.getString("Gender", "__");
		genderTV.setText(gender);
		if (gender.equals("Female"))
			fillThisButton(f, m);
		else
			fillThisButton(m, f);

		// Set the Distance Text View and Button
		String unit = sharedPref.getString("DistUnit", "Km");

		if (unit.equals("Km"))
			fillThisButton(km, mi);
		else
			fillThisButton(mi, km);

		// Set The SeekBar Distance and Distance Value
		int distance = sharedPref.getInt("distance", 100);
		distanceSB.setProgress(distance);

		// Set the interested in text value
		String text = sharedPref.getString("InterestedIn", "");
		showMeValueTV.setText(text);

		if (text.contains("Men"))
			menCB.setChecked(true);
		if (text.contains("Women"))
			womenCB.setChecked(true);

		// Set the ages in age seekBar
		int currentMin = Integer.parseInt(sharedPref.getString("MinAge", "18"));
		int currentMax = Integer.parseInt(sharedPref.getString("MaxAge", "40"));
		seekBar.setSelectedMinValue(currentMin);
		seekBar.setSelectedMaxValue(currentMax);
		ageRangeTV.setText(String.format("%d-%d", currentMin, currentMax));
		
	}

	public void onClick(View v) {
		anyChanges=true;
		switch (v.getId()) {
		
		case R.id.female_button:
			setFemaleButton();
			break;
		case R.id.male_button:
			setMaleButton();
			break;
		case R.id.km_button:
			setKmButton();
			break;

		case R.id.miles_button:
			setMilesButton();
			break;
		case R.id.menCB:
			if (menCB.isChecked())
				checkMen();
			else
				uncheckMen();

			break;
		case R.id.womenCB:
			if (womenCB.isChecked())
				checkWomen();
			else
				uncheckWomen();
			break;
		}
		
	}

	public void ageSeekBar() {
		
		seekBar.setOnRangeSeekBarChangeListener(new OnRangeSeekBarChangeListener<Integer>() {

			@Override
			public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar,
					Integer minValue, Integer maxValue) {
				// handle changed range values
				// Log.i(TAG, "User selected new range values: MIN=" + minValue
				// + ", MAX=" + maxValue);
				ageRangeTV.setText("" + minValue + "-" + maxValue);
				editor.putString("MinAge", "" + minValue);
				editor.putString("MaxAge", "" + maxValue);
				editor.commit();
				anyChanges=true;
			}
		});

		// add RangeSeekBar to pre-defined layout
		LinearLayout layout = (LinearLayout) findViewById(R.id.ageSeekBar);
		layout.addView(seekBar);
	}

	private void checkMen() {
		womenCheck = sharedPref.getBoolean("womenCheck", true);
		interestedInText = "Men";
		if (womenCheck)
			interestedInText = "Men,Women";
		showMeValueTV.setText(interestedInText);
		editor.putBoolean("menCheck", true);
		editor.putString("InterestedIn", interestedInText);
		editor.commit();
	}

	private void checkWomen() {
		menCheck = sharedPref.getBoolean("menCheck", true);
		interestedInText = "Women";
		if (menCheck)
			interestedInText = "Men,Women";
		showMeValueTV.setText(interestedInText);
		editor.putBoolean("womenCheck", true);
		editor.putString("InterestedIn", interestedInText);
		editor.commit();
	}

	private void uncheckWomen() {
		menCheck = sharedPref.getBoolean("menCheck", false);
		if (menCheck)
			interestedInText = "Men";
		else
			interestedInText = "";
		showMeValueTV.setText(interestedInText);
		editor.putBoolean("womenCheck", false);
		editor.putString("InterestedIn", interestedInText);
		editor.commit();
	}

	private void uncheckMen() {
		womenCheck = sharedPref.getBoolean("womenCheck", false);
		if (womenCheck)
			interestedInText = "Women";
		else
			interestedInText = "";
		showMeValueTV.setText(interestedInText);
		editor.putBoolean("menCheck", false);
		editor.putString("InterestedIn", interestedInText);
		editor.commit();
	}

	private void seekBarChange() {
		distanceSB.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (sharedPref.getString("DistUnit", "Km").equals("Km")) {
					limitSearchValueTV.setText(String.format("%d Km", progress));

				} else
					limitSearchValueTV.setText(String.format("%d Mi", progress));

				editor = sharedPref.edit();
				editor.putInt("distance", progress);
				editor.commit();
				anyChanges=true;
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

		});
	}

	private void setFemaleButton() {
		editor.putString("Gender", "Female");
		editor.commit();
		fillThisButton(f, m);
		genderTV.setText("Female");

	}

	private void setMaleButton() {
		editor.putString("Gender", "Male");
		editor.commit();
		fillThisButton(m, f);
		genderTV.setText("Male");

	}

	private void setKmButton() {

		fillThisButton(km, mi);
		int distance = distanceSB.getProgress();
		// If Miles was set initally
		if (sharedPref.getString("DistUnit", "Km").equals("Mi")) {
			distance = (int) (distance / 1.609) + 1;
		}
		distanceSB.setProgress(distance);
		limitSearchValueTV.setText(String.format("%d Km", distance));
		editor.putString("DistUnit", "Km");
		editor.commit();
	}

	private void setMilesButton() {

		fillThisButton(mi, km);
		// If Km was set initially
		int distance = distanceSB.getProgress();
		if (sharedPref.getString("DistUnit", "Km").equals("Km")) {
			distance = Math.min(100, (int) (distance * 1.609));
		}
		distanceSB.setProgress(distance);
		limitSearchValueTV.setText(String.format("%d Mi",
				distanceSB.getProgress()));
		editor.putString("DistUnit", "Mi");
		editor.commit();
	}

	private void fillThisButton(Button b1, Button b2) {
		b1.setBackgroundResource(R.drawable.round_corners_blue);
		b1.setTextColor(Color.WHITE);
		b2.setBackgroundResource(R.drawable.round_corners);
		b2.setTextColor(Color.BLACK);

	}

}
