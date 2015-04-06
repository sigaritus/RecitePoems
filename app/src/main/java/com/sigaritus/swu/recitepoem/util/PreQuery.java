package com.sigaritus.swu.recitepoem.util;

import android.content.Context;
import android.util.Log;

import com.sigaritus.swu.recitepoem.bean.Poem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Administrator on 2015/4/5.
 */
public class PreQuery  {
    Context context;
    PoemDAO poemDAO;
    ArrayList<String> cList;

    public PreQuery(Context context) {
        this.context = context;
        this.poemDAO = new PoemDAO(context);
        cList = new ArrayList<String>();
    }

    public ArrayList getContent(){

        Poem poem =null;
        int count = (int)poemDAO.getCount();
        Log.i("count -----",count+"");
        //shujuku id
        for (int i = 1; i <count+1 ; i++) {

                poem = poemDAO.find(i);
                if (poem!=null) {
                    String s = poem.getTitle() + "\n" + poem.getAuthor() + "\n" + poem.getContent();
                    cList.add(s);
                }
                Log.i("size-----",cList.size()+""+"i"+i+cList.get(i));

        }
        return cList;
    }

}
