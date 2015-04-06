package com.sigaritus.swu.recitepoem.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sigaritus.swu.recitepoem.bean.Poem;
import com.sigaritus.swu.recitepoem.bean.Sentence;

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
    }

    public Sentence find(int id){
        Cursor cursor = db.rawQuery(
                "select content,error_times from poems where sid = ?",
                new String[] { String.valueOf(id) });
        if (cursor.moveToNext()) {
            Log.i("sid----------------", "" + id);
            return new Sentence(
                    cursor.getInt(cursor.getColumnIndex("error_times")),
                    cursor.getString(cursor.getColumnIndex("content"))
            );
        }
        return null;
    }


}
