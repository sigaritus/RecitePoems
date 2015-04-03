package com.sigaritus.swu.recitepoem.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;


import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.sigaritus.swu.recitepoem.bean.Poem;

public class PoemDAO {
	private DBHelper helper;
	private SQLiteDatabase db;

	public PoemDAO(Context context) {
		helper = new DBHelper(context);
	}

	public void add(Poem Poem) {

		db = helper.getWritableDatabase();
		db.execSQL(
				"insert into poems(title,author,content,type) values (?,?,?,?) ",
				new Object[] { Poem.getTitle(), Poem.getAuthor(), Poem.getContent(), Poem.getType()});
		Log.i("PoemDAO--------------->", "add()");
	}

	public void update(Poem Poem) {
		db = helper.getWritableDatabase();
		db.execSQL(
				"update poems set title = ?,author = ? ,content = ?,type=? where pid = ?",
				new Object[] { Poem.getTitle(), Poem.getAuthor(), Poem.getContent(), Poem.getType()});
	}

	public Poem find(int id) {
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select title,author,content,type from poems where pid = ?",
				new String[] { String.valueOf(id) });
		if (cursor.moveToNext()) {
			return new Poem(
					cursor.getString(cursor.getColumnIndex("title")),
					cursor.getString(cursor.getColumnIndex("author")),
					cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getString(cursor.getColumnIndex("type"))
                    );
		}
		return null;
	}



	public void detele(Integer... ids) {
		if (ids.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ids.length; i++) {
				sb.append('?').append(',');
			}
			sb.deleteCharAt(sb.length() - 1);
			SQLiteDatabase database = helper.getWritableDatabase();
			database.execSQL("delete from poems where pid in (" + sb + ")",
					(Object[]) ids);
		}
	}

	public List<Poem> getScrollData(int start, int count) {
		List<Poem> Poems = new ArrayList<Poem>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from poems limit ?,?",
				new String[] { String.valueOf(start), String.valueOf(count) });
		while (cursor.moveToNext()) {
			Poems.add(new Poem(
					cursor.getString(cursor.getColumnIndex("title")), cursor
							.getString(cursor.getColumnIndex("author")), cursor
							.getString(cursor.getColumnIndex("content")), cursor
                    .getString(cursor.getColumnIndex("type"))));
		}
		return Poems;
	}

	public List<String> getAllPoems() {
		List<String> PoemsList = new ArrayList<String>();
		db = helper.getWritableDatabase();

		Cursor cursor = db.query("poems", new String[] { "poem" }, null, null,
				null, null, null, null);
		while (cursor.moveToNext()) {
			PoemsList.add(cursor.getString(cursor.getColumnIndex("poem")));
		}
		Log.i("------------>PoemDAO", PoemsList.toString());
		return PoemsList;

	}


	public long getCount() {
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select count(pid) from poems", null);
		if (cursor.moveToNext()) {
			return cursor.getLong(0);
		}
		return 0;
	}
}
