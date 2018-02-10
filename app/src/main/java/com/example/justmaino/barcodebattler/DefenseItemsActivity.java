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

public class DefenseItemsActivity extends AppCompatActivity {

    ImageView defenseItemIMG;
    TextView defenseItemName, defenseItemValue;
    String name, picture, defense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.defense_item_details);

        defenseItemIMG = (ImageView)findViewById(R.id.defenseItemIMG);
        defenseItemName = (TextView)findViewById(R.id.defenseItemName);
        defenseItemValue = (TextView)findViewById(R.id.defenseItemValue);

        if(getIntent().getExtras().getString("defenseItemName")!= null){

            name = getIntent().getStringExtra("defenseItemName");
            picture = getIntent().getStringExtra("defenseItemPicture");
            defense = getIntent().getStringExtra("defenseItemValue");

            defenseItemName.setText(name);
            defenseItemValue.setText(defense);
            /*File imgFile = new File(picture);

            if(imgFile.exists()){

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                defenseItemIMG.setImageBitmap(myBitmap);

            }*/
        }

    }

    public void scan(View view){

        Intent scan = new Intent(DefenseItemsActivity.this,ScanBarCodeActivity.class);
        startActivity(scan);
        finish();

    }

    public void delete(View view){

    }
}
