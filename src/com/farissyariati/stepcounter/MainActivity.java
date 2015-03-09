package com.farissyariati.stepcounter;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {

	// Region Sensor Utility
	private SensorManager mSensorManager;
	private Sensor mStepCounterSensor;
	private Sensor mStepDetectorSensor;
	//private Sensor mAccelerometer;
	// End Region Sensor Utility

	// Region View Component
	private TextView mStepCounterTextView;
	// End Region View Component

	// Region static final string
	private static final String mStepCounterInformation = "User Step: ";
	private static final String mStepDetectorInformation = "Step Detector Detected: ";

	// End Region static final string

	/**
	 * On Create Method
	 * 
	 * @param Saved
	 *            Instance State
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.initView();
		setContentView(R.layout.activity_main);

		// Initialize Sensor
		this.initSensor();
	}

	/**
	 * Execute when sensor has changed
	 * 
	 * @param event
	 */
	@Override
	public void onSensorChanged(SensorEvent event) {
		Sensor sensor = event.sensor;
		float[] values = event.values;
		int value = -1;

		if (values.length > 0) {
			value = (int) values[0];
		}

		if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
			// this.mStepCounterTextView.setText(mStepCounterInformation +
			// value);
			this.createToast(mStepCounterInformation + value);
		} else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
			// this.mStepCounterTextView.setText(mStepDetectorInformation +
			// value);
			this.createToast(mStepDetectorInformation + value);
		} else if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			Toast.makeText(getBaseContext(), "Accelerometer Active: " + value,
					Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * Executed when sensor accuracy change
	 * 
	 * @param sensor
	 * @param accuracy
	 */
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	/**
	 * On Activity Resume
	 */
	protected void onResume() {
		super.onResume();
		this.registerSensorListener();
	}

	/**
	 * On Activity Stop
	 */
	protected void onStop() {
		super.onStop();
		this.unRegisterSensorListener();
	}

	/**
	 * Initialize Sensor Manager and Sensors
	 */
	private void initSensor() {
		this.mSensorManager = (SensorManager) this
				.getSystemService(Context.SENSOR_SERVICE);
		this.mStepCounterSensor = this.mSensorManager
				.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
		this.mStepDetectorSensor = this.mSensorManager
				.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

		// this.mAccelerometer = this.mSensorManager
		// .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}

	/**
	 * Register Each Sensor
	 */
	private void registerSensorListener() {
		this.mSensorManager.registerListener(this, this.mStepCounterSensor,
				SensorManager.SENSOR_DELAY_NORMAL);
		this.mSensorManager.registerListener(this, this.mStepDetectorSensor,
				SensorManager.SENSOR_DELAY_NORMAL);
		// this.mSensorManager.registerListener(this, this.mAccelerometer,
		// SensorManager.SENSOR_DELAY_NORMAL);
	}

	/**
	 * Unregister Each Sensor
	 */
	private void unRegisterSensorListener() {
		this.mSensorManager.unregisterListener(this, this.mStepCounterSensor);
		this.mSensorManager.unregisterListener(this, this.mStepDetectorSensor);
		// this.mSensorManager.unregisterListener(this, this.mAccelerometer);
	}

	/**
	 * Initialize View Component
	 */
	private void initView() {
		this.mStepCounterTextView = (TextView) findViewById(R.id.tv_stepcounter);
	}

	/**
	 * Create toast - Length Short
	 * 
	 * @param message
	 */
	private void createToast(String message) {
		Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
	}
}
