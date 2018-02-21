package com.example.justmaino.barcodebattler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scanBarCode(View view){

        Intent scan = new Intent(MainActivity.this,ScanBarCodeActivity.class);
        scan.putExtra("scanType","monster");
        startActivity(scan);
    }

    public void collection(View view){

        Intent collection = new Intent(MainActivity.this,MonsterCollectionActivity.class);
        startActivity(collection);
    }

    public void playLocal(View view){

        Intent battle = new Intent(MainActivity.this,MonsterCollectionActivity.class);
        startActivity(battle);
    }

    public void playNetwork(View view){

        Toast.makeText(MainActivity.this, "Sry ,Network battle is not supported in this version ", Toast.LENGTH_LONG).show();

        //Intent battle = new Intent(MainActivity.this,MonsterCollectionActivity.class);
        //startActivity(battle);
    }


    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        System.out.println("requestCode : "+requestCode);
            if (resultCode == 1) {
                helloTxt.setText("Yes ,Bien Reçu");
            }
            else {
                helloTxt.setText("No");
            }
    }*/


}
