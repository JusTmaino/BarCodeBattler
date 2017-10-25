package com.example.justmaino.barcodebattler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by justmaino on 24/10/2017.
 */

public class BattleActivity extends AppCompatActivity {

    Player player1,player2;
    ImageView imagePlayer1;
    ImageView imagePlayer2;
    TextView attackStat1;
    TextView attackStat2;
    TextView defenseStat1;
    TextView defenseStat2;
    Button battleBtn1;
    Button battleBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battlefield);

        player1 = new Player(11000,21000);
        player2 = new Player(9000,26000);
        attackStat1 = (TextView)findViewById(R.id.attackStat1);
        attackStat2 = (TextView)findViewById(R.id.attackStat2);
        defenseStat1 = (TextView)findViewById(R.id.defenseStat1);
        defenseStat2 = (TextView)findViewById(R.id.defenseStat2);
        battleBtn1 = (Button)findViewById(R.id.battleBtn1);
        battleBtn2 = (Button)findViewById(R.id.battleBtn2);
        imagePlayer1 = (ImageView)findViewById(R.id.player1);
        imagePlayer2 = (ImageView)findViewById(R.id.player2);

        attackStat1.setText(Integer.toString(player1.getAttack() ));
        defenseStat1.setText(Integer.toString(player1.getDefense() ));
        attackStat2.setText(Integer.toString(player2.getAttack() ));
        defenseStat2.setText(Integer.toString(player2.getDefense() ));


    }

    public void startBattlePlayer1(View view){

        Animation animationAttack = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_attack1);
        imagePlayer1.startAnimation(animationAttack);
        if(player2.getDefense() > player1.getAttack()){

            player2.setDefense(player2.getDefense() - player1.getAttack());
            defenseStat2.setText(Integer.toString(player2.getDefense()));
        }
        else{
            defenseStat2.setText("0");
            Toast.makeText(BattleActivity.this, "Game Over Player 2", Toast.LENGTH_LONG).show();
        }

    }

    public void startBattlePlayer2(View view){

        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_attack2);
        imagePlayer2.startAnimation(animation1);
        if(player1.getDefense() > player2.getAttack()){

            player1.setDefense(player1.getDefense() - player2.getAttack());
            defenseStat1.setText(Integer.toString(player1.getDefense()));
        }
        else{
            defenseStat1.setText("0");
            Toast.makeText(BattleActivity.this, "Game Over Player 1", Toast.LENGTH_LONG).show();
        }

    }
}
