package com.example.justmaino.barcodebattler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView helloTxt;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helloTxt = (TextView)findViewById(R.id.helloTxt);
        nextBtn = (Button)findViewById(R.id.nextBtn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next= new Intent(MainActivity.this,YesNoActivity.class);
                next.putExtra("next","next");
                startActivityForResult(next,1);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (resultCode == 1) {
                helloTxt.setText("Yes ,Bien Reçu");
            }
            else {
                helloTxt.setText("!!!");
            }
    }


}
