package com.example.sql_studentrecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public final static String DATBASE_NAME = "Mystudent.db";
    public final static String TABLE_NAME = "Mystudent";
    public final static String COL_1 = "ID";
    public final static String COL_2 = "NAME";
    public final static String COL_3 = "EMAIL";
    public final static String COL_4 = "COURSE_COUNT";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATBASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " //DO AS YOU DO IN mysql after that put that in this format
                + TABLE_NAME+
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " NAME TEXT(20)," +
                " EMAIL TEXT(20)," +
                " COURSE_COUNT INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

public boolean insertData(String name,String email,String courseCount){
SQLiteDatabase db =this.getWritableDatabase();   //it is used to permission the database
ContentValues contentValues=new ContentValues();  //it is a big value that store multiple small values

    contentValues.put(COL_2,name);
    contentValues.put(COL_3,email);
    contentValues.put(COL_4,courseCount);

    long result=db.insert(TABLE_NAME, null, contentValues);
    if(result==-1){
        return false;

    }else{
        return true;
    }

}

public boolean updateData(String id,String name,String email,String courseCount){
SQLiteDatabase db=this.getWritableDatabase();
ContentValues contentValues=new ContentValues();

    contentValues.put(COL_1,id);
    contentValues.put(COL_2,name);
    contentValues.put(COL_3,email);
    contentValues.put(COL_2,courseCount);

db.update(TABLE_NAME,contentValues,"id=?", new String[]{id});
return true;

}
 public Cursor getData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+" WHERE ID='"+id+"'";
        Cursor cursor=db.rawQuery(query, null);
        return cursor;
 }
public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
return         db.delete(TABLE_NAME, "ID=?", new String[]{id});

}
public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return cursor;
}



}