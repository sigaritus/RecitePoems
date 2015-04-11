package com.sigaritus.swu.recitepoem.plan;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.util.Attributes;
import com.sigaritus.swu.recitepoem.R;
import com.sigaritus.swu.recitepoem.util.PlanDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2015/4/7.
 */
public class PlanFragment extends Fragment {

    private ListView mListView;
    private PlanAdapter mAdapter;
    private Context mContext = getActivity();
    private ImageView add_img;
    private PlanDAO planDAO;
    private Handler handler;
    private ArrayList<String> planlist;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.plan_layout,container,false);
        mListView = (ListView) view.findViewById(R.id.planlistview);
        planDAO = new PlanDAO(getActivity());
        add_img = (ImageView)view.findViewById(R.id.add_plan_img);

        add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PlanAddActivity.class);

                Log.i("intent--------","planadd activity");

                startActivity(intent);

                Log.i("intent--------","after start");
            }
        });

        new QueryPlanThread().start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                mListView.setAdapter(mAdapter);

            }
        };

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((SwipeLayout)(mListView.getChildAt(position - mListView.getFirstVisiblePosition()))).open(true);
            }
        });
        mListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("ListView", "OnTouch");
                return false;
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "OnItemLongClickListener", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.e("ListView", "onScrollStateChanged");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("ListView", "onItemSelected:" + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e("ListView", "onNothingSelected:");
            }
        });


        return view;
    }

    class QueryPlanThread extends Thread{
        @Override
        public void run() {

            planlist = planDAO.getPlanList();
            mAdapter = new PlanAdapter(getActivity(),planlist);

            Log.i("query plan thread",planlist.size()+"");

            System.out.println("query plan thread"+planlist.size()+"");
            mAdapter.setMode(Attributes.Mode.Single);

           Message message = new Message();
            handler.sendMessage(message);

        }
    }


}
