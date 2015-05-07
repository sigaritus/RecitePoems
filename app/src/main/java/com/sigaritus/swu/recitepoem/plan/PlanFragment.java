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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.util.Attributes;
import com.sigaritus.swu.recitepoem.R;
import com.sigaritus.swu.recitepoem.bean.Plan;
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
    private static Handler handler;
    private ArrayList<Plan> planlist;
    private QueryPlanThread planThread;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.plan_layout,container,false);
        planThread = new QueryPlanThread();
        mListView = (ListView) view.findViewById(R.id.planlistview);
        planDAO = new PlanDAO(getActivity());
        add_img = (ImageView)view.findViewById(R.id.add_plan_img);

        add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PlanAddActivity.class);
                startActivity(intent);

            }
        });

        planThread.start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                mListView.setAdapter(mAdapter);
                Log.i("poem query plan thread",planlist.size()+"");

            }
        };

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {

                SwipeLayout s=((SwipeLayout)(mListView.getChildAt(position - mListView.getFirstVisiblePosition())));
                s.open(true);
                LinearLayout linearLayout =(LinearLayout)s.getChildAt(0);

                Button confirm_btn =(Button)linearLayout.getChildAt(2);

                LinearLayout linearLayout1 = (LinearLayout)s.getChildAt(1);
                final TextView pos_text = (TextView)linearLayout1.getChildAt(0);
                final TextView ans_text = (TextView)linearLayout1.getChildAt(1);
                Log.i("ans------",ans_text.getText().toString()+"--");
                String s1 = ans_text.getText().toString();
                String[] contents = s1.split("\n");
                final String plan = contents[0];
                confirm_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("poem s1---","----"+plan);
                        planDAO.detele(plan);
                        Log.i("plan poem ","delete");
                        Toast.makeText(getActivity(),"删除该计划",Toast.LENGTH_SHORT).show();
                        new QueryPlanThread().start();
                    }
                });

            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("onstart----poem","onStart");
        new QueryPlanThread().start();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("onpause----poem","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("onstop----poem","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("ondestroy----poem","onDestroyView");
    }


    class QueryPlanThread extends Thread{
        @Override
        public void run() {

            planlist = planDAO.getPlanList();
            mAdapter = new PlanAdapter(getActivity(),planlist);

            Log.i("poem query plan thread",planlist.size()+"");

//            System.out.println("query plan thread"+planlist.size()+"");
            mAdapter.setMode(Attributes.Mode.Single);

           Message message = new Message();
            handler.sendMessage(message);

        }
    }


}
