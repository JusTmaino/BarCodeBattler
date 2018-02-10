package com.example.justmaino.barcodebattler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by justmaino on 25/10/2017.
 */

public class GameOverActivity extends AppCompatActivity {

    TextView gameOverTxt;
    String monsterName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);


        gameOverTxt = (TextView)findViewById(R.id.gameOverTxt);
        if(getIntent().getExtras().getString("winner")!= null){
            gameOverTxt.setText("Winner is player "+getIntent().getExtras().getString("winner"));
        }
        else {
            gameOverTxt.setText("Sry an Error happened");
        }
        if(getIntent().getStringExtra("monsterName")!= null){
            monsterName = getIntent().getStringExtra("monsterName");
        }


    }

    public void restartGame(View view){

        Intent game = new Intent(GameOverActivity.this,BattleActivity.class);
        if (monsterName != null) {
            game.putExtra("monsterName", monsterName);
        }
        startActivity(game);
        finish();

    }

    public void menu(View view){

        Intent menu = new Intent(GameOverActivity.this,MainActivity.class);
        startActivity(menu);
        finish();

    }
}
