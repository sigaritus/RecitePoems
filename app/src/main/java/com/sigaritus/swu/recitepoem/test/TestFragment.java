package com.sigaritus.swu.recitepoem.test;

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
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.sigaritus.swu.recitepoem.R;
import com.sigaritus.swu.recitepoem.bean.Sentence;
import com.sigaritus.swu.recitepoem.util.SentenceDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2015/4/5.
 */
public class TestFragment extends Fragment {
    private ListView mListView;
    private ChooseListAdapter mAdapter;
    List<String> slist;
    TextView question;
    Handler handler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_layout,container,false);
        Random r1 = new Random();
        question = (TextView)view.findViewById(R.id.question_view);

        TextView pre =(TextView)view.findViewById(R.id.pre_text);
        TextView sub = (TextView)view.findViewById(R.id.submit_text);
        TextView next = (TextView)view.findViewById(R.id.next_text);

        mListView = (ListView) view.findViewById(R.id.chooselistview);

        new SentenceQueryThread().start();

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                String sentence_pre = slist.get(0).split("，")[0];

                question.setText(sentence_pre);

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
                Toast.makeText(getActivity(), "OnItemLongClickListener", Toast.LENGTH_SHORT).show();
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

    class SentenceQueryThread extends Thread{
        @Override
        public void run() {
            SentenceDAO sentenceDAO = new SentenceDAO(getActivity());
             slist = sentenceDAO.getAllSentences();
            mAdapter = new ChooseListAdapter(getActivity(),slist);
            Message message = new Message();
            handler.sendMessage(message);

        }
    }
}
