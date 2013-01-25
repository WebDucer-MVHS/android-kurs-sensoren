package de.mvhs.android.androidsensoren;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class ProximityActivity extends Activity implements SensorEventListener {
  private Sensor        _Sensor    = null;
  private SensorManager _SManager  = null;
  private TextView      _Proximity = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_proximity);

    _SManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    _Sensor = _SManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    _Proximity = (TextView) findViewById(R.id.txt_priximity);
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
    if (event.values.length >= 1) {
      _Proximity.setText(String.valueOf(event.values[0]));
    }
  }
}
