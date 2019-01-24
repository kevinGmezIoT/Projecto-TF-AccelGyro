package com.example.projecttfacelerometergyroscope;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class ApplicationProject extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
