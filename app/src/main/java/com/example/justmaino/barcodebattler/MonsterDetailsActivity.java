package com.example.justmaino.barcodebattler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by justmaino on 07/02/2018.
 */

public class MonsterDetailsActivity extends AppCompatActivity {

    ImageView monsterIMG;
    TextView monsterName, monsterAttackStat, monsterDefenseStat , attackItemValue , defenseItemValue;
    String name, picture, attack, defense ;
    AttackItem attackItem;
    DefenseItem defenseItem;
    SqlLiteConnection cn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monster_details);

        cn = new SqlLiteConnection(this);

        monsterIMG = (ImageView)findViewById(R.id.monsterIMG);
        monsterName = (TextView)findViewById(R.id.monsterName);
        monsterAttackStat = (TextView)findViewById(R.id.monsterAttackStat);
        monsterDefenseStat = (TextView)findViewById(R.id.monsterDefenseStat);
        attackItemValue = (TextView)findViewById(R.id.attackItemValueTxt);
        defenseItemValue = (TextView)findViewById(R.id.defenseItemValueTxt);

        if(getIntent().getExtras().getString("monsterName")!= null){

            name = getIntent().getStringExtra("monsterName");
            picture = getIntent().getStringExtra("picture");
            attack = getIntent().getStringExtra("attack");
            defense = getIntent().getStringExtra("defense");
            Log.d("TAG", "onCreate MOnster details attack item name " +getIntent().getStringExtra("attackItemNam"));

            Monster monster = cn.getMonster(cn.getMonsterId(name));

            if (monster.getAttackItemName()!= null) {
                attackItem = cn.loadAttackItem(monster.getAttackItemName());
                attackItemValue.setText(Integer.toString(attackItem.attackValue));
            }

            if (monster.getDefenseItemName()!= null) {
                defenseItem = cn.loadDefenseItem(monster.getDefenseItemName());
                defenseItemValue.setText(Integer.toString(defenseItem.defenseValue));
            }

            monsterName.setText(name);
            monsterAttackStat.setText(attack);
            monsterDefenseStat.setText(defense);
            monsterIMG.setImageResource(this.getResources().getIdentifier(picture, "drawable", this.getPackageName()));
        }

    }

    public void scan(View view){

        Intent scan = new Intent(MonsterDetailsActivity.this,ScanAttackDefenseItemsActivity.class);
        scan.putExtra("monsterName",name);
        startActivity(scan);

    }


    public void play(View view) {
        Intent battle = new Intent(MonsterDetailsActivity.this, BattleActivity.class);
        battle.putExtra("monsterName",name);
        startActivity(battle);
    }

    public void delete(View view){
        String monsterID = cn.getMonsterId(name);
        boolean deleted = cn.deleteMonster(monsterID);
        if(deleted){
            Toast.makeText(MonsterDetailsActivity.this, "Monster "+monsterID+" Deleted", Toast.LENGTH_LONG).show();
        }
        finish();
    }
}
