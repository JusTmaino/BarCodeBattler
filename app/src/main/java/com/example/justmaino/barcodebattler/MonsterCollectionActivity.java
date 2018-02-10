package com.example.justmaino.barcodebattler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.justmaino.barcodebattler.SqlLiteConnection.picture;

/**
 * Created by justmaino on 07/02/2018.
 */

public class MonsterCollectionActivity extends AppCompatActivity {


    ArrayList<String> monsterDetails ;
    ArrayList<Monster> monsters ;
    ListView listView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monster_collection);

        monsterDetails = new ArrayList<String>();
        this.getMonsterData();

        // Getting reference to listview
        listView = (ListView) findViewById(R.id.monsterCollectionListView);
        customAdapter = new CustomAdapter(this);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                Intent intent = new Intent(MonsterCollectionActivity.this,MonsterDetailsActivity.class);
                intent.putExtra("monsterName", monsters.get(position).getName());
                intent.putExtra("picture", monsters.get(position).getPicture());
                intent.putExtra("attack", Integer.toString(monsters.get(position).getAttack()));
                intent.putExtra("defense", Integer.toString(monsters.get(position).getDefense()));
                intent.putExtra("attackItemNam", monsters.get(position).getAttackItemName());
                intent.putExtra("defenseItemNam", monsters.get(position).getDefenseItemName());
                startActivity(intent);
                finish();

            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        //this.getMonsterData();

    }

    public void getMonsterData() {
        SqlLiteConnection cn = new SqlLiteConnection(this.getApplicationContext());
        monsters = cn.getMonsters();
    }

    class CustomAdapter extends BaseAdapter{

        Context context;

        public CustomAdapter(Context ctx){
            this.context = ctx;
        }

        @Override
        public int getCount() {
            return monsters.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.custom_list_layout,null);

            ImageView monsterIV = (ImageView) convertView.findViewById(R.id.monsterIV);
            TextView monsterNameTxt = (TextView) convertView.findViewById(R.id.monsterNameTxt);
            TextView monsterAttackvalue = (TextView) convertView.findViewById(R.id.monsterAttackValue);
            TextView monsterDefensevalue = (TextView) convertView.findViewById(R.id.monsterDefenseValue);

            monsterIV.setImageResource(context.getResources().getIdentifier(monsters.get(position).getPicture(), "drawable", context.getPackageName()));
            monsterNameTxt.setText(monsters.get(position).getName());
            monsterAttackvalue.setText(Integer.toString(monsters.get(position).getAttack()));
            monsterDefensevalue.setText(Integer.toString(monsters.get(position).getDefense()));

            return convertView;
        }
    }
}
