package com.example.justmaino.barcodebattler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView helloTxt;
    Button playBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloTxt = (TextView)findViewById(R.id.helloTxt);
        playBtn = (Button)findViewById(R.id.playBtn);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        System.out.println("requestCode : "+requestCode);
            if (resultCode == 1) {
                helloTxt.setText("Yes ,Bien Reçu");
            }
            else {
                helloTxt.setText("No");
            }
    }


}
