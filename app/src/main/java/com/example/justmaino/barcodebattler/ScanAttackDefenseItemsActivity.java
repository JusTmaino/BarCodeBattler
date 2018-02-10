package com.example.justmaino.barcodebattler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by justmaino on 10/02/2018.
 */

public class ScanAttackDefenseItemsActivity extends AppCompatActivity {
    String monsterName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attack_defense_items);

        monsterName = getIntent().getStringExtra("monsterName");
    }

    public void scanAttackItems(View view){

        Intent scan = new Intent(ScanAttackDefenseItemsActivity.this,ScanBarCodeActivity.class);
        scan.putExtra("scanType","attackItems");
        scan.putExtra("monsterName",monsterName);
        startActivity(scan);
        finish();

    }

    public void scanDefenseItems(View view){

        Intent scan = new Intent(ScanAttackDefenseItemsActivity.this,ScanBarCodeActivity.class);
        scan.putExtra("scanType","defenseItems");
        scan.putExtra("monsterName",monsterName);
        startActivity(scan);
        finish();

    }
}
