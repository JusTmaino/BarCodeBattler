package com.example.justmaino.barcodebattler;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

/**
 * Created by justmaino on 26/10/2017.
 */

public class ScanBarCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {


    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private static int camId = Camera.CameraInfo.CAMERA_FACING_BACK;
    String scanType , monsterName;
    SqlLiteConnection cn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        int currentApiVersion = Build.VERSION.SDK_INT;

        if(currentApiVersion >=  Build.VERSION_CODES.M)
        {
            if(checkPermission())
            {
                Toast.makeText(getApplicationContext(), "Permission already granted!", Toast.LENGTH_LONG).show();
            }
            else
            {
                requestPermission();
            }
        }

        cn = new SqlLiteConnection(this);

        if (getIntent().getExtras().getString("monsterName")!= null){

            monsterName = getIntent().getStringExtra("monsterName");
        }

        if(getIntent().getExtras().getString("scanType")!= null){
            scanType = getIntent().getStringExtra("scanType");
        }
    }

    private boolean checkPermission()
    {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    @Override
    public void onResume() {
        super.onResume();
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if(scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }


    @Override
    public void handleResult(Result result) {
        int value = -1;
        Long code = Long.parseLong(result.getText().toString());
        Log.d("TAG", "SCAN: Monster "+scanType);
        if(scanType.equals("monster")){
            value = (int) (code % 30);
            boolean inserted = cn.insertData("Monster " + value, "monster" + value,value*1000,value*2000,null,null);
            if (inserted) {
                Log.d("TAG", "SCAN: Monster " + value + " created");
                Intent collection = new Intent(ScanBarCodeActivity.this,MonsterCollectionActivity.class);
                Toast.makeText(ScanBarCodeActivity.this, "Monster "+Integer.toString(value)+" Created", Toast.LENGTH_LONG).show();
                startActivity(collection);
                finish();
            }
        }
        else if(scanType.equals("attackItems")){
            value = (int) (code % 6);
            boolean inserted = cn.insertAttackItemsData("Attack Item " + value, "attack" + value,value*1000);
            if (inserted) {
                Log.d("TAG", "SCAN: Attack Item " + value + " created");
            }
            String MonsterID = cn.getMonsterId(monsterName);
            boolean updated = cn.updateMonsterAttackData(MonsterID,"Attack Item " + value);
            if (updated) {
                Log.d("TAG", "SCAN: Monster "+MonsterID+" attack Item " + value + " Updated");
                Toast.makeText(ScanBarCodeActivity.this, "Monster "+MonsterID+" got the attack Item " + value, Toast.LENGTH_LONG).show();
                finish();
            }

        }
        else if(scanType.equals("defenseItems")){
            value = (int) (code % 6);
            boolean inserted = cn.insertDefenseItemsData("Defense Item " + value, "shield" + value,value*2000);
            if (inserted) {
                Log.d("TAG", "SCAN: Defense Item " + value + " created");
            }
            String MonsterID = cn.getMonsterId(monsterName);
            boolean updated = cn.updateMonsterDefenseData(MonsterID,"Defense Item " + value);
            if (updated) {
                Log.d("TAG", "SCAN: Monster defense Item " + value + " Updated");
                Toast.makeText(ScanBarCodeActivity.this, "Monster "+MonsterID+" got the defense Item " + value, Toast.LENGTH_LONG).show();
                finish();

            }
        }


        AlertDialog alertDialog = new AlertDialog.Builder(ScanBarCodeActivity.this).create();
        alertDialog.setTitle("QRCode Info");
        alertDialog.setMessage(Long.toString(code)+" --->  "+Integer.toString(value));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();

                    }
                });
        alertDialog.show();

    }
}
