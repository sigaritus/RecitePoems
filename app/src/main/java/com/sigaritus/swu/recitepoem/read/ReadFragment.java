package com.sigaritus.swu.recitepoem.read;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.daimajia.swipe.util.Attributes;
import com.sigaritus.swu.recitepoem.R;
import com.sigaritus.swu.recitepoem.search.SearchFragment;
import com.sigaritus.swu.recitepoem.util.PoemDAO;

import java.util.ArrayList;
import java.util.List;


public class ReadFragment extends Fragment {

   private Handler handler;
    GridView gridView;
    private PoemDAO poemDAO;
//    private int Flag;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getData();

    }

//    public void getData(){
//        Flag=getArguments().getInt("flag");
//        Log.i("flag----","poem------flag = "+Flag);
//    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View read_view = inflater.inflate(R.layout.grid_view,container,false);
         gridView = (GridView)read_view.findViewById(R.id.gridview);
        poemDAO = new PoemDAO(getActivity());

        QueryPoemListThread queryPoemListThread = new QueryPoemListThread();
        queryPoemListThread.start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle poem_bund = msg.getData();
                ArrayList<String> poemList = poem_bund.getStringArrayList("poemList");

                Log.i("handler-----poemlist",poemList.size()+"");

                GridViewAdapter adapter = new GridViewAdapter(getActivity(),poemList);

                adapter.setMode(Attributes.Mode.Multiple);
                gridView.setAdapter(adapter);
                gridView.setSelected(false);
            }
        };



        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("onItemClick","onItemClick:" + position+"   "+"......");

                TextView textView = (TextView)view.findViewById(R.id.poem_text);


//                SearchFragment searchFragment = new SearchFragment();
//
//
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//
//                // 用这个fragment替换任何在fragment_container中的东西
//                // 并添加事务到back stack中以便用户可以回退到之前的状态
//                transaction.replace(R.id.viewpager, searchFragment);
//                transaction.addToBackStack(null);
//
//                // 提交事务
//                transaction.commit();
//
//                Log.i("onItemClick","onItemClick:" + position+"commit   "+"......");
                Intent intent = new Intent(getActivity(),ReadPoemActivity.class);
//

                intent.putExtra("poem",textView.getText().toString());

                getActivity().startActivity(intent);
                Log.i("after start ","after ");



                return false;
            }
        });
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i("onItemClick","onItemClick:" + position+"   "+"......");
//
//            }
//        });
//
//
//        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.e("onItemSelected","onItemSelected:" + position);
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        // Inflate the layout for this fragment
        return read_view;
        //返回操作后的view  inflater只是初始化。。
    }


    class QueryPoemListThread extends Thread{
        @Override
        public void run() {
            ArrayList<String> poemList=null;

                poemList = poemDAO.getPoemList();


            Log.i("poem",poemList.size()+"-----------------");
            Message message = new Message();
            Bundle poem_bund = new Bundle();
            poem_bund.putStringArrayList("poemList", poemList);

            Log.i("poemList------thread", poemList.size() + "");
            message.setData(poem_bund);
            handler.sendMessage(message);
        }
    }


}
