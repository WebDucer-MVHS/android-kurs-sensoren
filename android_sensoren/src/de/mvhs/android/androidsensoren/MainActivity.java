package de.mvhs.android.androidsensoren;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
  // Variablen
  Button _Acceleration = null;
  Button _Proximity    = null;
  Button _Compas       = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Initialisierung der benötigten Views
    _Acceleration = (Button) findViewById(R.id.cmd_acceleration);
    _Proximity = (Button) findViewById(R.id.cmd_proximity);
    _Compas = (Button) findViewById(R.id.cmd_compas);

    _Acceleration.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        onClicked(buttonType.ACCELERATION);
      }
    });

    _Proximity.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        onClicked(buttonType.PROXIMITY);
      }
    });

    _Compas.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        onClicked(buttonType.COMPAS);
      }
    });
  }

  private void onClicked(buttonType type) {
    Intent startIntent = null;

    switch (type) {
      case ACCELERATION:
        startIntent = new Intent(this, AccelerationActivity.class);

        break;
      case PROXIMITY:
        startIntent = new Intent(this, ProximityActivity.class);

        break;
      case COMPAS:
        startIntent = new Intent(this, CompasActivity.class);

        break;
      default:
        break;
    }

    if (startIntent != null) {
      startIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      startActivity(startIntent);
    }
  }

  private enum buttonType {
    ACCELERATION, PROXIMITY, COMPAS
  }
}
