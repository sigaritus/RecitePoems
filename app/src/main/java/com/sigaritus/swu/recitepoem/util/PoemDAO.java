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
				"insert into poems(title,author,content,collected,type) values (?,?,?,?,?) ",
				new Object[] { Poem.getTitle(), Poem.getAuthor(), Poem.getContent(),0, Poem.getType()});
		Log.i("PoemDAO--------------->", "add()");
        db.close();
	}

	public void update(Poem Poem) {
		db = helper.getWritableDatabase();
		db.execSQL(
				"update poems set title = ?,author = ? ,content = ?,type=? where pid = ?",
				new Object[] { Poem.getTitle(), Poem.getAuthor(), Poem.getContent(), Poem.getType()});
        db.close();
	}

    public void collect_poem(int pos){
        db= helper.getWritableDatabase();
        db.execSQL("update poems set collected =1 where pid =?",new Object[]{pos});
        db.close();
    }

    public void cancel_collect_poem(int pos){
        db= helper.getWritableDatabase();
        db.execSQL("update poems set collected =0 where pid =?",new Object[]{pos});
        db.close();
    }



    public int get_collected(int pos){
        int collected=0;
        db=helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select collected from poems where pid =?",new String[]{String.valueOf(pos)});
        if (cursor.moveToNext()){
            collected =cursor.getInt(cursor.getColumnIndex("collected"));
        }
        db.close();
        return collected;
    }

	public Poem find(int id) {
		db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery(
				"select title,author,content,type from poems where pid = ?",
				new String[] { String.valueOf(id) });
		if (cursor.moveToNext()) {
            Log.i("pid----------------",""+id);
            Poem poem = new Poem(
                    cursor.getString(cursor.getColumnIndex("title")),
                    cursor.getString(cursor.getColumnIndex("author")),
                    cursor.getString(cursor.getColumnIndex("content")),
                    cursor.getString(cursor.getColumnIndex("type"))
            );
            db.close();
			return poem;
		}
        db.close();
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
            db.close();
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
        cursor.close();
        db.close();
		return Poems;
	}
    public ArrayList<String> getPoemList() {
        ArrayList<String> Poems = new ArrayList<String>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("poems", new String[] { "title","author","content","type" }, null, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            Poem poem =new Poem(
                    cursor.getString(cursor.getColumnIndex("title")), cursor
                    .getString(cursor.getColumnIndex("author")), cursor
                    .getString(cursor.getColumnIndex("content")), cursor
                    .getString(cursor.getColumnIndex("type")));
//            String[] contents = poem.getContent().split("，");
//            Log.i("trim ",contents.length+"------");
            String poemString = poem.getTitle()+"\n"+poem.getAuthor()+"\n";
            String[] contents = poem.getContent().split("，|。");
            //格式调整
            for (int i = 0; i <contents.length ; i++) {
                poemString+=contents[i]+"\n";
            }
            Poems.add(poemString);
        }
        cursor.close();
        db.close();
        return Poems;
    }

    public ArrayList<String> getCollectedPoemList() {
        ArrayList<String> Poems = new ArrayList<String>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("poems", new String[] { "title","author","content","type" }, "collected=?", new String[]{String.valueOf(1)},
                null, null, null, null);

        while (cursor.moveToNext()) {
            Poem poem =new Poem(
                    cursor.getString(cursor.getColumnIndex("title")), cursor
                    .getString(cursor.getColumnIndex("author")), cursor
                    .getString(cursor.getColumnIndex("content")), cursor
                    .getString(cursor.getColumnIndex("type")));
            String poemString = poem.getTitle()+"\n"+poem.getAuthor()+"\n";
            String[] contents = poem.getContent().split("，|。");
            //格式调整
            for (int i = 0; i <contents.length ; i++) {
                poemString+=contents[i]+"\n";
            }
            Poems.add(poemString);
        }
        cursor.close();
        db.close();
        Log.i("poemdao-collected list","---"+Poems.size());
        return Poems;
    }

	public List<String> getAllPoems() {
		List<String> PoemsList = new ArrayList<String>();
		db = helper.getWritableDatabase();

		Cursor cursor = db.query("poems", new String[] { "content" }, null, null,
				null, null, null, null);
		while (cursor.moveToNext()) {
			PoemsList.add(cursor.getString(cursor.getColumnIndex("content")));
		}
		Log.i("------------>PoemDAO", PoemsList.toString());
        cursor.close();
        db.close();
		return PoemsList;

	}


	public long getCount() {


   		db = helper.getWritableDatabase();


		Cursor cursor = db.rawQuery("select count(pid) from poems", null);
		if (cursor.moveToNext()) {
            long count =cursor.getLong(0);
            cursor.close();
            db.close();
			return count;
		}
        cursor.close();
        db.close();
		return 0;
	}

}
