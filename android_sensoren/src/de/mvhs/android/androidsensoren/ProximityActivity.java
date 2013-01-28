package de.mvhs.android.androidsensoren;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProximityActivity extends Activity implements SensorEventListener {
  private Sensor           _Sensor         = null;
  private SensorManager    _SManager       = null;
  private TextView         _Proximity      = null;
  private Button           _Kamera         = null;
  private Button           _Galery         = null;
  private ImageView        _Image          = null;

  private final static int _KAMERA_REQUEST = 100;
  private final static int _GALERY_REQUEST = 200;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_proximity);

    // Einsatz auf dem realen Gerät
    _SManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
    _Sensor = _SManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    _Proximity = (TextView) findViewById(R.id.txt_priximity);
    _Kamera = (Button) findViewById(R.id.cmd_camera);
    _Galery = (Button) findViewById(R.id.cmd_galery);
    _Image = (ImageView) findViewById(R.id.img_bild);

    _Kamera.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent kameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(kameraIntent, _KAMERA_REQUEST);
      }
    });
    _Galery.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent galeryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galeryIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(galeryIntent, "Foto auswählen"), _GALERY_REQUEST);
      }
    });
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
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // Kamera
    if (requestCode == _KAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
      Bitmap image = (Bitmap) data.getExtras().get("data");
      _Image.setImageBitmap(image);
    }
    // Galerie
    else if (requestCode == _GALERY_REQUEST && resultCode == Activity.RESULT_OK) {
      Uri selectedImage = data.getData();
      String[] filePathColumn = new String[] { MediaStore.Images.Media.DATA };

      Cursor cursor = this.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
      if (cursor.moveToFirst()) {

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();

        Bitmap image = BitmapFactory.decodeFile(filePath);

        _Image.setImageBitmap(image);
      }
    }
    super.onActivityResult(requestCode, resultCode, data);
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
