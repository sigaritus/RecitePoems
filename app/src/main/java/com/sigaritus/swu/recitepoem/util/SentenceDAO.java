package com.sigaritus.swu.recitepoem.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sigaritus.swu.recitepoem.bean.Poem;
import com.sigaritus.swu.recitepoem.bean.Sentence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/5.
 */
public class SentenceDAO {
    private DBHelper helper;
    private SQLiteDatabase db;

    public SentenceDAO(Context context) {
        helper = new DBHelper(context);
    }

    public void add(Sentence s){
        db = helper.getWritableDatabase();
        db.execSQL("insert into sentences (content,error_times) values(?,?)",new Object[]{s.getContent(),s.getError_times()});
        db.close();
    }

    public Sentence find(int id){
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(
                "select content,error_times from sentences where sid = ?",
                new String[] { String.valueOf(id) });
        if (cursor.moveToNext()) {
            Log.i("sid----------------", "" + id);
            Sentence sentence =new Sentence(
                    cursor.getInt(cursor.getColumnIndex("error_times")),
                    cursor.getString(cursor.getColumnIndex("content"))
            );
            db.close();
            return sentence;
        }
        db.close();
        return null;
    }

    public List<String> getAllSentences() {
        List<String> SentencesList = new ArrayList<String>();
        db = helper.getWritableDatabase();

        Cursor cursor = db.query("sentences", new String[] { "content" }, null, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            SentencesList.add(cursor.getString(cursor.getColumnIndex("content")));
        }
//        Log.i("------------>sDAO", SentencesList.toString());
        db.close();
//        Log.i("sentence list",SentencesList.toString());
        return SentencesList;

    }

    public long getCount() {
        db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(sid) from sentences", null);
        if (cursor.moveToNext()) {
            long count =cursor.getLong(0);
            db.close();
            return count;
        }
        db.close();
        return 0;
    }

}
