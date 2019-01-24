package com.example.projecttfacelerometergyroscope;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    //Declaración de variables: 3 Acelerómetro, 3 Gyroscopio, 1 Sensor Manager, 2 sensores
    TextView acc_x;
    TextView acc_y;
    TextView acc_z;
    TextView gyro_x;
    TextView gyro_y;
    TextView gyro_z;
    SensorManager sManager;
    private Sensor sAccel;
    private Sensor sGyro;

    //Código que se ejecuta al iniciar la pantalla
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Enlazamos las variables con los Id's de los campos de texto creados en la pantalla
        acc_x = (TextView) findViewById(R.id.acc_x);
        acc_y = (TextView) findViewById(R.id.acc_y);
        acc_z = (TextView) findViewById(R.id.acc_z);
        gyro_x = (TextView) findViewById(R.id.gyro_x);
        gyro_y = (TextView) findViewById(R.id.gyro_y);
        gyro_z = (TextView) findViewById(R.id.gyro_z);

        //Enlazamos las variables de los sensores con las propiedas del mismo dispositivo
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sAccel = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sGyro = sManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

    }

    //En cada muestreo de los sensores se ejecutará este método
    @Override
    public void onSensorChanged(SensorEvent event) {
        //Dependiendo de que sensor se active elegirá la acción (Ocurre simultáneamente también)
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            //Declaramos variables donde se almacenarán los datos numéricos del sensor
            double x,y,z;
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            //Declaramos los datos Strings para mostrar en texto
            String xs = String.format("%.2f", x);
            String ys = String.format("%.2f", y);
            String zs = String.format("%.2f", z);
            acc_x.setText("Accelerometer in x:"+xs);
            acc_y.setText("Accelerometer in y:"+ys);
            acc_z.setText("Accelerometer in z:"+zs);

            if(x>-0.5&&x<0.5) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("X-accel");
                myRef.setValue(xs);
                myRef = database.getReference("Y-accel");
                myRef.setValue(ys);
                myRef = database.getReference("Z-accel");
                myRef.setValue(zs);
            }
        }

        //Identico en el caso del giroscopio
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            double x,y,z;
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
            String xs = String.format("%.2f", x);
            String ys = String.format("%.2f", y);
            String zs = String.format("%.2f", z);
            acc_x.setText("Gyroscope in x:"+xs);
            acc_y.setText("Gyroscope in y:"+ys);
            acc_z.setText("Gyroscope in z:"+zs);
        }

    }

    //Sirve para especificar acción cuando cambie la precisión de los sensores, no es necesario
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //Estas dos funciones se encargan de que los sensores se activen solo en la app, puesto que consumen
    //mucha batería si la app se ejecuta en segundo plano
    @Override
    protected void onResume() {
        super.onResume();
        //Activa los sensores solo cuando estamos en la app
        sManager.registerListener(this, sAccel, sManager.SENSOR_DELAY_NORMAL);
        sManager.registerListener(this, sGyro, sManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Desactiva los sensores cuando la app se cierra
        sManager.unregisterListener(this);
    }
}
