package com.sigaritus.swu.recitepoem.util;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;

public class DBHelper extends SQLiteOpenHelper {

	private static final int VERSION = 1;
	private static final String DB_NAME = "poem.db";


	public DBHelper(Context context) {
		super(context, DB_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists poems(pid integer primary key autoincrement,"
				+ "title text not null,author text not null,content text not null,collected integer ,type text not null)");
        db.execSQL("create table if not exists sentences(sid integer primary key autoincrement,"
                + "content text not null,error_times integer not null)");
        db.execSQL("create table if not exists plan(id integer primary key autoincrement,"
                + "content text not null,time text not null)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
