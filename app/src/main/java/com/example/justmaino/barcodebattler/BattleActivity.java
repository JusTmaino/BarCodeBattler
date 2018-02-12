package com.example.justmaino.barcodebattler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
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

    Monster monster1,monster2;
    ImageView imagePlayer1,imagePlayer2,attackItem1,attackItem2,defenseItem1,defenseItem2;
    TextView attackStat1,attackStat2,defenseStat1,defenseStat2;
    Button battleBtn1,battleBtn2;
    String name;
    Boolean player1Turn,player2Turn;
    SqlLiteConnection cn;
    AttackItem attackItemMonster1;
    DefenseItem defenseItemMonster1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.battlefield);

        cn = new SqlLiteConnection(this);

        attackStat1 = (TextView)findViewById(R.id.attackStat1);
        attackStat2 = (TextView)findViewById(R.id.attackStat2);
        defenseStat1 = (TextView)findViewById(R.id.defenseStat1);
        defenseStat2 = (TextView)findViewById(R.id.defenseStat2);
        battleBtn1 = (Button)findViewById(R.id.battleBtn1);
        battleBtn2 = (Button)findViewById(R.id.battleBtn2);
        imagePlayer1 = (ImageView)findViewById(R.id.player1);
        imagePlayer2 = (ImageView)findViewById(R.id.player2);
        attackItem1 = (ImageView)findViewById(R.id.attackItem1);
        defenseItem1 = (ImageView)findViewById(R.id.defenseItem1);
        attackItem2 = (ImageView)findViewById(R.id.attackItem2);
        defenseItem2 = (ImageView)findViewById(R.id.defenseItem2);

        if(getIntent().getStringExtra("monsterName")!= null) {
            name = getIntent().getStringExtra("monsterName");
            monster1 = cn.getMonster(cn.getMonsterId(name));
            imagePlayer1.setImageResource(this.getResources().getIdentifier(monster1.getPicture(), "drawable", this.getPackageName()));
            attackStat1.setText(Integer.toString(monster1.getAttack() ));
            defenseStat1.setText(Integer.toString(monster1.getDefense() ));

            if (monster1.getAttackItemName() != null){
                attackItemMonster1 = cn.loadAttackItem(monster1.getAttackItemName());
                attackItem1.setImageResource(this.getResources().getIdentifier(attackItemMonster1.getPicture(), "drawable", this.getPackageName()));
                attackStat1.setText(Integer.toString(monster1.getAttack() + attackItemMonster1.getAttackValue() ));
            }

            if (monster1.getDefenseItemName() != null){
                defenseItemMonster1 = cn.loadDefenseItem(monster1.getDefenseItemName());
                defenseItem1.setImageResource(this.getResources().getIdentifier(defenseItemMonster1.getPicture(), "drawable", this.getPackageName()));
                defenseStat1.setText(Integer.toString(monster1.getDefense()+ defenseItemMonster1.getDefenseValue()));
            }
        }
        else {
            monster1 = new Monster("Monster 1","monster1",11000,21000,null,null);
            imagePlayer1.setImageResource(this.getResources().getIdentifier("monster1", "drawable", this.getPackageName()));
            attackStat1.setText(Integer.toString(monster1.getAttack() ));
            defenseStat1.setText(Integer.toString(monster1.getDefense() ));
        }


        monster2 = new Monster("Monster 2","monster2",9000,26000,null,null);
        attackStat2.setText(Integer.toString(monster2.getAttack() ));
        defenseStat2.setText(Integer.toString(monster2.getDefense() ));
        imagePlayer2.setImageResource(this.getResources().getIdentifier("monster2", "drawable", this.getPackageName()));

        player1Turn = true;
        player2Turn = false;


    }

    public void startBattlePlayer1(View view){

        if(player1Turn) {
            //Animation animationAttack = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_attack1);
            TranslateAnimation translateAnimation = new TranslateAnimation(0,700,0,-60);
            translateAnimation.setDuration(400);
            translateAnimation.setFillAfter(false);

            imagePlayer1.startAnimation(translateAnimation);
            if (monster2.getDefense() > monster1.getAttack()) {

                monster2.setDefense(monster2.getDefense() - monster1.getAttack());
                defenseStat2.setText(Integer.toString(monster2.getDefense()));
            } else {
                defenseStat2.setText("0");
                Toast.makeText(BattleActivity.this, "Game Over Player 2", Toast.LENGTH_LONG).show();
                Intent gameOver = new Intent(BattleActivity.this, GameOverActivity.class);
                gameOver.putExtra("winner", "1");
                if(monster1.getAttack()!=11000) {
                    gameOver.putExtra("monsterName", monster1.getName());
                }
                startActivity(gameOver);
                finish();
            }
            player1Turn = false;
            player2Turn = true;
        }
        else {
            Toast.makeText(BattleActivity.this, "Player 2 must Play First", Toast.LENGTH_LONG).show();
        }

    }

    public void startBattlePlayer2(View view){

        if(player2Turn) {
            //Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_attack2);
            TranslateAnimation translateAnimation = new TranslateAnimation(0,-700,0,-60);
            translateAnimation.setDuration(400);
            translateAnimation.setFillAfter(false);

            imagePlayer2.startAnimation(translateAnimation);
            if (monster1.getDefense() > monster2.getAttack()) {

                monster1.setDefense(monster1.getDefense() - monster2.getAttack());
                defenseStat1.setText(Integer.toString(monster1.getDefense()));
            } else {
                defenseStat1.setText("0");
                Toast.makeText(BattleActivity.this, "Game Over Player 1", Toast.LENGTH_LONG).show();
                Intent gameOver = new Intent(BattleActivity.this, GameOverActivity.class);
                gameOver.putExtra("winner", "2");
                if(monster1.getAttack()!=11000) {
                    gameOver.putExtra("monsterName", monster1.getName());
                }
                startActivity(gameOver);
                finish();
            }
            player2Turn = false;
            player1Turn = true;
        }
        else {
            Toast.makeText(BattleActivity.this, "It is Player 1 Turn", Toast.LENGTH_LONG).show();
        }

    }
}
