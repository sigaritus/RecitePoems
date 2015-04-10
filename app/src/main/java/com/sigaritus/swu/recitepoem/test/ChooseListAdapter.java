package com.sigaritus.swu.recitepoem.test;

/**
 * Created by Administrator on 2015/4/7.
 */
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.sigaritus.swu.recitepoem.R;

import java.util.List;
import java.util.Random;


public class ChooseListAdapter extends BaseSwipeAdapter {

    private Context mContext;
    private List<String> clist;
    private String line;
    private TextView answer_text;
    private int count;
    private Button confirm_button;
    public ChooseListAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public ChooseListAdapter(Context mContext,List<String> list) {
        this.mContext = mContext;
        this.clist = list;
        Log.i("clist -------",clist.size()+"");
    }
    public ChooseListAdapter(Context mContext,List<String> list,int count) {
        this.mContext = mContext;
        this.clist = list;
        Log.i("clist -------",clist.size()+"");
        this.count =count;
    }


    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.choose_layout, null);
        SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));

        swipeLayout.setDragEdge(SwipeLayout.DragEdge.Right);

        answer_text = (TextView)v.findViewById(R.id.text_data);

        confirm_button =(Button)v.findViewById(R.id.confirm);

        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {

            }
        });

        return v;
    }

    @Override
    public void fillValues(int position, View convertView) {
        Random answer_random = new Random(System.currentTimeMillis());
        TextView t = (TextView)convertView.findViewById(R.id.position);

        int ans = answer_random.nextInt(4);
        t.setText((position + 1) + ".");
        if(ans==position) {
            answer_text.setText(clist.get(count).split("，")[1]);
        }else{
            answer_text.setText(clist.get(answer_random.nextInt(clist.size())).split("，")[1]);
        }
//        confirm_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public int getCount() {
        return 4;
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
