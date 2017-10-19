package com.enrico.heroshell;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mikepenz.materialdrawer.DrawerBuilder;

public class ContainerActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        DrawerBuilder drawerBuilder = new DrawerBuilder().withActivity(this);
    }
}
