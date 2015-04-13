package com.sigaritus.swu.recitepoem.plan;

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
import com.sigaritus.swu.recitepoem.bean.Plan;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/11.
 */
public class PlanAdapter  extends BaseSwipeAdapter{

    private Context mContext;
    private ArrayList<Plan> planlist;
    private TextView plan_text_added;
    public PlanAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public PlanAdapter(Context mContext,ArrayList<Plan> planlist) {
        this.mContext = mContext;
        this.planlist =planlist;
        Log.i("planlist adapter",""+planlist.size());
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.plan_item_layout, null);
        plan_text_added = (TextView)v.findViewById(R.id.added_plan_text);
        SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));
//        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
//            @Override
//            public void onOpen(SwipeLayout layout) {
//
//            }
//        });
//        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
//            @Override
//            public void onDoubleClick(SwipeLayout layout, boolean surface) {
//                Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
//            }
//        });
        return v;
    }

    @Override
    public void fillValues(int position, View convertView) {
        TextView t = (TextView)convertView.findViewById(R.id.position);
        t.setText((position + 1)+"" );
        Plan plan = planlist.get(position);
        plan_text_added.setText(plan.getContent()+"\n"+"\n"+plan.getTime());
    }

    @Override
    public int getCount() {
        return planlist.size();
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
