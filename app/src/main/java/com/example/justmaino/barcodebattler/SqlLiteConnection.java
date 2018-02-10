package com.example.justmaino.barcodebattler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by justmaino on 07/02/2018.
 */

public final class SqlLiteConnection extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "BarCodeBattler.db";

    public static final String TABLE_PLAYER = "player";
    public static final String idPlayer = "ID";
    public static final String playerName = "NAME";

    public static final String TABLE_MONSTER = "monster";
    public static final String id = "ID";
    public static final String monsterName = "NAME";
    public static final String picture = "PICTURE";
    public static final String attack = "ATTACK";
    public static final String defense = "DEFENSE";
    public static final String attackItemNam = "ATTACKITEMNAME";
    public static final String defenseItemNam = "DEFNSEITEMNAME";

    public static final String TABLE_PLAYER_MONSTER = "playermonster";
    public static final String idPlayerMonster = "ID";
    public static final String playerID = "IDPLAYER";
    public static final String monsterID = "IDMONSTER";

    public static final String TABLE_ATTACK_ITEMS = "attack";
    public static final String idAttackItem = "ID";
    public static final String attackItemName = "NAME";
    public static final String attackItemPicture = "PICTURE";
    public static final String attackItemValue = "ATTACKVALUE";

    public static final String TABLE_DEFENSE_ITEMS = "defense";
    public static final String idDefenseItem = "ID";
    public static final String defenseItemName = "NAME";
    public static final String defenseItemPicture = "PICTURE";
    public static final String defenseItemValue = "DEFENSEVALUE";

    public SqlLiteConnection(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_PLAYER+" ("+idPlayer+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ playerName +" TEXT)");
        db.execSQL("CREATE TABLE "+TABLE_MONSTER+" ("+id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ monsterName +" TEXT,"+picture+" TEXT,"+attack+" INTEGER,"+defense+" INTEGER,"+attackItemNam+" TEXT,"+defenseItemNam+" TEXT)");
        db.execSQL("CREATE TABLE "+TABLE_PLAYER_MONSTER+" ("+idPlayerMonster+" INTEGER PRIMARY KEY AUTOINCREMENT,"+playerID+" INTEGER,"+monsterID+" INTEGER,  FOREIGN KEY ("+playerID+") REFERENCES "+TABLE_PLAYER+"("+idPlayer+") ,FOREIGN KEY ("+monsterID+") REFERENCES "+TABLE_MONSTER+"("+id+"))");
        db.execSQL("CREATE TABLE "+TABLE_ATTACK_ITEMS+" ("+idAttackItem+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ attackItemName +" TEXT,"+attackItemPicture+" TEXT,"+attackItemValue+" INTEGER)");
        db.execSQL("CREATE TABLE "+TABLE_DEFENSE_ITEMS+" ("+idDefenseItem+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ defenseItemName +" TEXT,"+defenseItemPicture+" TEXT,"+defenseItemValue+" INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PLAYER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MONSTER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PLAYER_MONSTER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ATTACK_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DEFENSE_ITEMS);
        onCreate(db);
    }

    public boolean insertData(String name1 ,String picture1, int attack1, int defense1, String attackItemNam1, String defenseItemNam1)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentVal = new ContentValues();
        contentVal.put(monsterName, name1);
        contentVal.put(picture , picture1);
        contentVal.put(attack , attack1);
        contentVal.put(defense , defense1);
        contentVal.put(attackItemNam , attackItemNam1);
        contentVal.put(defenseItemNam , defenseItemNam1);
        long result = db.insert(TABLE_MONSTER , null , contentVal);

        if(result== -1) {
            return false;
        }
        else
            return true;
    }

    public boolean insertAttackItemsData(String name1 ,String picture1, int attackValue1)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentVal = new ContentValues();
        contentVal.put(attackItemName, name1);
        contentVal.put(attackItemPicture , picture1);
        contentVal.put(attackItemValue, attackValue1);
        long result = db.insert(TABLE_ATTACK_ITEMS , null , contentVal);

        if(result== -1) {
            return false;
        }
        else
            return true;
    }

    public boolean insertDefenseItemsData(String name1 ,String picture1, int defenseValue1)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentVal = new ContentValues();
        contentVal.put(defenseItemName, name1);
        contentVal.put(defenseItemPicture , picture1);
        contentVal.put(defenseItemValue, defenseValue1);
        long result = db.insert(TABLE_DEFENSE_ITEMS , null , contentVal);

        if(result== -1) {
            return false;
        }
        else
            return true;
    }

    public boolean checkMonsters(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.query(    SqlLiteConnection.TABLE_MONSTER,
                new String[]{   SqlLiteConnection.monsterName},
                SqlLiteConnection.monsterName + " = ?",
                new String[] { "Monster 0" },
                null, null, null, null);
        if (c.getCount() > 0) {
            return true;
        }
        else
            return false;
    }


    public ArrayList<Monster> getMonsters(){
        Log.i(TAG, "getMonsters: in ");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(SqlLiteConnection.TABLE_MONSTER,
                new String[]{   SqlLiteConnection.monsterName, SqlLiteConnection.picture,SqlLiteConnection.attack,
                        SqlLiteConnection.defense,SqlLiteConnection.attackItemNam,SqlLiteConnection.defenseItemNam},
                null, null, null, null, null);

        ArrayList<Monster> monsters = new ArrayList<Monster>();

        if(cursor.moveToFirst()){
            do{
                Monster monster = new Monster();
                monster.setName(cursor.getString(cursor.getColumnIndex(monsterName)));
                monster.setPicture(cursor.getString(cursor.getColumnIndex(picture)));
                monster.setAttack(Integer.parseInt(cursor.getString(cursor.getColumnIndex(attack))));
                monster.setDefense(Integer.parseInt(cursor.getString(cursor.getColumnIndex(defense))));
                monster.setAttackItemName(cursor.getString(cursor.getColumnIndex(attackItemNam)));
                monster.setDefenseItemName(cursor.getString(cursor.getColumnIndex(defenseItemNam)));
                monsters.add(monster);
                Log.i(TAG, "getMonsters: DO "+cursor.getString(cursor.getColumnIndex(picture)));
            }while (cursor.moveToNext());
        }
        else
            Log.i("Sql Lite","no Monsters");
        cursor.close();
        db.close();
        return monsters;

    }

    public AttackItem loadAttackItem(String attackItemNam) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(    SqlLiteConnection.TABLE_ATTACK_ITEMS,
                new String[]{   SqlLiteConnection.attackItemName,SqlLiteConnection.attackItemPicture,SqlLiteConnection.attackItemValue},
                SqlLiteConnection.attackItemName + " = ?",
                new String[] { attackItemNam },
                null, null, null, null);

        AttackItem item = new AttackItem();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            item.setName(cursor.getString(cursor.getColumnIndex(attackItemName)));
            item.setPicture(cursor.getString(cursor.getColumnIndex(attackItemPicture)));
            item.setAttackValue(Integer.parseInt(cursor.getString(cursor.getColumnIndex(attackItemValue))));
        }
        else
            Log.i("Sql Lite","no attack Item");
        cursor.close();
        db.close();
        return item;
        
    }

    public DefenseItem loadDefenseItem(String defenseItemNam) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(    SqlLiteConnection.TABLE_DEFENSE_ITEMS,
                new String[]{   SqlLiteConnection.defenseItemName,SqlLiteConnection.defenseItemPicture,SqlLiteConnection.defenseItemValue},
                SqlLiteConnection.defenseItemName + " = ?",
                new String[] { defenseItemNam },
                null, null, null, null);

        DefenseItem item = new DefenseItem();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            item.setName(cursor.getString(cursor.getColumnIndex(defenseItemName)));
            item.setPicture(cursor.getString(cursor.getColumnIndex(defenseItemPicture)));
            item.setDefenseValue(Integer.parseInt(cursor.getString(cursor.getColumnIndex(defenseItemValue))));
        }
        else
            Log.i("Sql Lite","no defense Item");
        cursor.close();
        db.close();
        return item;
    }

    public String getMonsterId(String monsterName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String id="";
        Cursor c = db.query(    SqlLiteConnection.TABLE_MONSTER,
                new String[]{   SqlLiteConnection.id},
                SqlLiteConnection.monsterName + " = ?",
                new String[] { monsterName },
                null, null, null, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            id = c.getString(0);

        }
        return id;
    }

    public Monster getMonster(String id1) {
        SQLiteDatabase db = this.getWritableDatabase();
        Monster monster = new Monster();
        String name="";
        Cursor cursor = db.query(    SqlLiteConnection.TABLE_MONSTER,
                new String[]{   SqlLiteConnection.monsterName, SqlLiteConnection.picture,SqlLiteConnection.attack,
                        SqlLiteConnection.defense,SqlLiteConnection.attackItemNam,SqlLiteConnection.defenseItemNam},
                SqlLiteConnection.id + " = ?",
                new String[] { id1 },
                null, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            monster.setName(cursor.getString(cursor.getColumnIndex(monsterName)));
            monster.setPicture(cursor.getString(cursor.getColumnIndex(picture)));
            monster.setAttack(Integer.parseInt(cursor.getString(cursor.getColumnIndex(attack))));
            monster.setDefense(Integer.parseInt(cursor.getString(cursor.getColumnIndex(defense))));
            monster.setAttackItemName(cursor.getString(cursor.getColumnIndex(attackItemNam)));
            monster.setDefenseItemName(cursor.getString(cursor.getColumnIndex(defenseItemNam)));

        }
        return monster;
    }

    public boolean updateMonsterAttackData(String id1,String attackItemNam1)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentVal = new ContentValues();

        contentVal.put(attackItemNam , attackItemNam1);
        db.update(TABLE_MONSTER ,contentVal,SqlLiteConnection.id+" = ?",new String[] { id1 });

        return true;
    }

    public boolean updateMonsterDefenseData(String id1,String defenseItemNam1)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentVal = new ContentValues();

        contentVal.put(defenseItemNam , defenseItemNam1);
        db.update(TABLE_MONSTER ,contentVal,SqlLiteConnection.id+" = ?",new String[] { id1 });

        return true;
    }

    public boolean deleteMonster(String id1)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int sucess = db.delete(TABLE_MONSTER ,SqlLiteConnection.id+" = ?",new String[] { id1 });
        if(sucess !=0) {
            return true;
        }
        else
            return false;
    }
}
