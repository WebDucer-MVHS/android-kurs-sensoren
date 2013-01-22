package de.mvhs.android.androidsensoren;

import android.app.Activity;
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
    switch (type) {
      case ACCELERATION:

        break;
      case PROXIMITY:

        break;
      case COMPAS:

        break;
      default:
        break;
    }
  }

  private enum buttonType {
    ACCELERATION, PROXIMITY, COMPAS
  }
}
