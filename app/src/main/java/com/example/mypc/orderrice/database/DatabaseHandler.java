package com.example.mypc.orderrice.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mypc.orderrice.model.Brunch;
import com.example.mypc.orderrice.model.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 2/14/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_foodh";
    public static final String TABLE_NAME = "food";
    public static final String TABLE_NAMEBRUNCH = "brunch";
    public static final String KEY_ID = "id";
    public static final String KEY_IDIMAGE = "idimage";
    public static final String KEY_NAME = "name";
    public static final String KEY_IDBRUNCH = "idbrunch";
    public static final String KEY_QUANTITY = "quantity";
    public static final String KEY_VALUE = "value";
    public static final String KEY_CHECKINT = "checkint";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT,"
                + KEY_IDIMAGE + " INTEGER ,"
                + KEY_NAME + " TEXT,"
                + KEY_IDBRUNCH + " INTEGER ,"
                + KEY_QUANTITY + " INTEGER ,"
                + KEY_VALUE + " INTEGER ,"
                + KEY_CHECKINT + " INTEGER )";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_CONTACTS_TABLE_BRUNCH = "CREATE TABLE " + TABLE_NAMEBRUNCH + " ("
                + KEY_NAME + " TEXT,"
                + KEY_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT,"
                + KEY_IDIMAGE + " INTEGER )";
        db.execSQL(CREATE_CONTACTS_TABLE_BRUNCH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXITS " + TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXITS " + TABLE_NAMEBRUNCH);
        onCreate(db);
    }

    public void addFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, food.getIdName());
        values.put(KEY_IDIMAGE, food.getIdImage());
        values.put(KEY_NAME, food.getName());
        values.put(KEY_IDBRUNCH, food.getIdBrunchName());
        values.put(KEY_QUANTITY, food.getQuantity());
        values.put(KEY_VALUE, food.getValue());
        values.put(KEY_CHECKINT, food.getCheckInt());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void addBrunch(Brunch brunch) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, brunch.getFood());
        values.put(KEY_ID, brunch.getIdBrunch());
        values.put(KEY_IDIMAGE, brunch.getIdImageItems());
        db.insert(TABLE_NAMEBRUNCH, null, values);
        db.close();
    }

    public void deleteFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[]{String.valueOf(food.getIdName())});
        db.close();
    }

    public void deleteBrunch(Brunch brunch) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAMEBRUNCH, KEY_ID + " = ?",
                new String[]{String.valueOf(brunch.getIdBrunch())});
        db.close();
    }

    public void updateBrunch(Brunch brunch) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, "Chuối");
        db.update(TABLE_NAMEBRUNCH, values, KEY_ID + " = ?",
                new String[]{String.valueOf(brunch.getIdBrunch())});
        db.close();
    }

    public void updateFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, food.getName());
        values.put(KEY_IDBRUNCH, food.getIdBrunchName());
        values.put(KEY_VALUE, food.getValue());
        db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(food.getIdName())});
        db.close();
    }

    public void deleteAllFood() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public void deleteAllBrunch() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAMEBRUNCH, null, null);
        db.close();
    }

    public void deleteAllTableForName(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(tableName, null, null);
        db.close();
    }

    public Food getFood(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{KEY_ID, KEY_IDIMAGE,
                        KEY_NAME, KEY_IDBRUNCH,
                        KEY_QUANTITY, KEY_VALUE,
                        KEY_CHECKINT}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Food food = new Food(cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getInt(4),
                cursor.getInt(5),
                cursor.getInt(6));
        return food;
    }

    public List<Food> getAllFood() {
        List<Food> contactList = new ArrayList<Food>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setIdName(Integer.parseInt(cursor.getString(0)));
                food.setIdImage(cursor.getInt(1));
                food.setName(cursor.getString(2));
                food.setIdBrunchName(cursor.getInt(3));
                food.setQuantity(cursor.getInt(4));
                food.setValue(cursor.getInt(5));
                food.setCheckInt(cursor.getInt(6));
                contactList.add(food);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    public List<Brunch> getAllBrunch() {
        List<Brunch> contactList = new ArrayList<Brunch>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAMEBRUNCH;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Brunch brunch = new Brunch();
                brunch.setFood(cursor.getString(0));
                brunch.setIdBrunch(cursor.getInt(1));
                brunch.setIdImageItems(cursor.getInt(2));
                contactList.add(brunch);
            } while (cursor.moveToNext());
        }
        return contactList;
    }
}
