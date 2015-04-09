package com.sigaritus.swu.recitepoem.test;

/**
 * Created by Administrator on 2015/4/7.
 */
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.sigaritus.swu.recitepoem.R;

import java.util.List;


public class ChooseListAdapter extends BaseSwipeAdapter {

    private Context mContext;
    private List<String> clist;
    private TextView answer_text;
    public ChooseListAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public ChooseListAdapter(Context mContext,List<String> list) {
        this.mContext = mContext;
        this.clist = list;
        Log.i("clist -------",clist.size()+"");
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
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {

            }
        });

        return v;
    }

    @Override
    public void fillValues(int position, View convertView) {
        TextView t = (TextView)convertView.findViewById(R.id.position);

        t.setText((position + 1) + ".");
        answer_text.setText(clist.get(0).split("，")[1]);

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
