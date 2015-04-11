package com.sigaritus.swu.recitepoem.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sigaritus.swu.recitepoem.bean.Plan;
import com.sigaritus.swu.recitepoem.bean.Poem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/4/11.
 */
public class PlanDAO {
    private DBHelper helper;
    private SQLiteDatabase db;

    public PlanDAO(Context context) {
        helper = new DBHelper(context);
    }

    public void add(Plan plan) {

        db = helper.getWritableDatabase();
        db.execSQL(
                "insert into plan(content,time) values (?,?) ",
                new Object[] { plan.getContent(), plan.getTime()});
        Log.i("planDAO--------------->", "add()");
        db.close();
    }


    public ArrayList<String> getPlanList() {
        ArrayList<String> plans = new ArrayList<String>();
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("plan", new String[] { "content","time" }, null, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
           Plan plan =new Plan(
                    cursor.getString(cursor.getColumnIndex("content")), cursor
                    .getString(cursor.getColumnIndex("time")));
//
           plans.add(plan.getContent());
           plans.add(plan.getTime());
        }
        cursor.close();
        db.close();
        return plans;
    }

    public long getCount() {


        db = helper.getWritableDatabase();


        Cursor cursor = db.rawQuery("select count(id) from plan", null);
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
