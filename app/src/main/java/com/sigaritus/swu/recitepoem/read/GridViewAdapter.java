package com.sigaritus.swu.recitepoem.read;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.sigaritus.swu.recitepoem.R;
import com.sigaritus.swu.recitepoem.bean.Poem;
import com.sigaritus.swu.recitepoem.bean.Sentence;
import com.sigaritus.swu.recitepoem.util.PoemDAO;
import com.sigaritus.swu.recitepoem.util.SentenceDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2015/3/31.
 */
public class GridViewAdapter extends BaseSwipeAdapter {

    private Context mContext;
    private AlarmManager am;
    private SwipeLayout swipeLayout;
    private TextView  poem_view;
    private List<String> mList;
    private PoemDAO poemDAO;
    public GridViewAdapter(Context mContext) {
        this.mContext = mContext;
        this.poemDAO = new PoemDAO(mContext);
        this.am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
    }
    public GridViewAdapter(Context mContext,List<String> mList) {
        this.mContext = mContext;
        this.poemDAO = new PoemDAO(mContext);
        this.mList =mList;
        this.am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        Log.i("mlist-----------",mList.size()+"");
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {

        return R.id.swipe;
    }

    @Override
    public View generateView(final int position, ViewGroup parent) {
        View view =LayoutInflater.from(mContext).inflate(R.layout.read_layout, null);

       swipeLayout = (SwipeLayout)view.findViewById(getSwipeLayoutResourceId(position));

        swipeLayout.setDragEdge(SwipeLayout.DragEdge.Left);

        ImageView star=(ImageView)view.findViewById(R.id.star);
        ImageView clock=(ImageView)view.findViewById(R.id.clock);
        ImageView delete=(ImageView)view.findViewById(R.id.delete);




        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (poemDAO.get_collected(position)==0) {
                    poemDAO.collect_poem(position);
                    Toast.makeText(mContext, "已收藏", Toast.LENGTH_SHORT).show();
                }else{
                    poemDAO.cancel_collect_poem(position);
                    Toast.makeText(mContext, "取消收藏", Toast.LENGTH_SHORT).show();
                }
            }
        });
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SentenceDAO sentenceDAO = new SentenceDAO(mContext);
                String[] content =mList.get(position).split("\n");
                Sentence sentence = new Sentence(0,content[2]+"，"+content[3]+"。");
                sentenceDAO.add(sentence);
                Toast.makeText(mContext,"加入测试",Toast.LENGTH_SHORT).show();

            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,ReadPoemActivity.class);

                intent.putExtra("poem",mList.get(position));

                mContext.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void fillValues(int position, View convertView) {
        TextView t = (TextView)convertView.findViewById(R.id.position);
        t.setText((position + 1 )+".");
        poem_view = (TextView)convertView.findViewById(R.id.poem_text);


        poem_view.setText(mList.get(position));


    }

    @Override
    public int getCount() {

        return  mList.size();
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}