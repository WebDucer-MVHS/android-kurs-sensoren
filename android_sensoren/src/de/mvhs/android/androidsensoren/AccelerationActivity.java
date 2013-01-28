package de.mvhs.android.androidsensoren;

import org.openintents.sensorsimulator.hardware.Sensor;
import org.openintents.sensorsimulator.hardware.SensorEvent;
import org.openintents.sensorsimulator.hardware.SensorEventListener;
import org.openintents.sensorsimulator.hardware.SensorManagerSimulator;

import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class AccelerationActivity extends Activity implements SensorEventListener {
  private Sensor                 _Sensor   = null;
  private SensorManagerSimulator _SManager = null;
  private TextView               _XAxis    = null;
  private TextView               _YAxis    = null;
  private TextView               _ZAxis    = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_acceleration);

    // Einsatz mit Simulator
    _SManager = SensorManagerSimulator.getSystemService(this, Context.SENSOR_SERVICE);
    _SManager.connectSimulator();
    _Sensor = _SManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    _XAxis = (TextView) findViewById(R.id.txt_x_axis);
    _YAxis = (TextView) findViewById(R.id.txt_y_axis);
    _ZAxis = (TextView) findViewById(R.id.txt_z_axis);
  }

  @Override
  protected void onResume() {
    super.onResume();

    if (_Sensor != null) {
      _SManager.registerListener(this, _Sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
  }

  @Override
  protected void onPause() {
    super.onPause();
    if (_Sensor != null) {
      _SManager.unregisterListener(this);
    }
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {

  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    if (event.values.length >= 3) {
      _XAxis.setText(String.valueOf(event.values[0]));
      _YAxis.setText(String.valueOf(event.values[1]));
      _ZAxis.setText(String.valueOf(event.values[2]));
    }
  }
}
