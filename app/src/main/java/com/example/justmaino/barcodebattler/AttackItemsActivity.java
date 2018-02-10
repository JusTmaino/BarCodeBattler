package com.example.justmaino.barcodebattler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by justmaino on 09/02/2018.
 */

public class AttackItemsActivity extends AppCompatActivity {

    ImageView attackItemIMG;
    TextView attackItemName, attackItemValue;
    String name, picture, attack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attack_item_details);

        attackItemIMG = (ImageView)findViewById(R.id.attackItemIMG);
        attackItemName = (TextView)findViewById(R.id.attackItemName);
        attackItemValue = (TextView)findViewById(R.id.attackItemValue);

        if(getIntent().getExtras().getString("attackItemName")!= null){

            name = getIntent().getStringExtra("attackItemName");
            picture = getIntent().getStringExtra("attackItemPicture");
            attack = getIntent().getStringExtra("attackItemValue");

            attackItemName.setText(name);
            attackItemValue.setText(attack);
            /*File imgFile = new File(picture);

            if(imgFile.exists()){

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                attackItemIMG.setImageBitmap(myBitmap);

            }*/
        }

    }

    public void scan(View view){

        Intent scan = new Intent(AttackItemsActivity.this,ScanBarCodeActivity.class);
        startActivity(scan);
        finish();

    }

    public void delete(View view){

    }
}
