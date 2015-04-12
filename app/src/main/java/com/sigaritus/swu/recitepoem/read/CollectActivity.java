package com.sigaritus.swu.recitepoem.read;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.daimajia.swipe.util.Attributes;
import com.sigaritus.swu.recitepoem.R;
import com.sigaritus.swu.recitepoem.util.PoemDAO;

import java.util.ArrayList;


public class CollectActivity extends Activity {

   private Handler handler;
    GridView gridView;
    private PoemDAO poemDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collect_grid_view);
        gridView = (GridView)findViewById(R.id.gridview);
        poemDAO = new PoemDAO(CollectActivity.this);

        QueryPoemListThread queryPoemListThread = new QueryPoemListThread();
        queryPoemListThread.start();

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle poem_bund = msg.getData();
                ArrayList<String> poemList = poem_bund.getStringArrayList("poemList");

                Log.i("collect fragment",poemList.size()+"");

                GridViewAdapter adapter = new GridViewAdapter(CollectActivity.this,poemList);

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
                Intent intent = new Intent(CollectActivity.this,ReadPoemActivity.class);
//

                intent.putExtra("poem",textView.getText().toString());

                startActivity(intent);




                return false;
            }
        });
    }




    class QueryPoemListThread extends Thread{
        @Override
        public void run() {
            ArrayList<String> poemList=null;

                poemList=poemDAO.getCollectedPoemList();

            Log.i("poem",poemList.size()+"-----------------");
            Message message = new Message();
            Bundle poem_bund = new Bundle();
            poem_bund.putStringArrayList("poemList", poemList);


            message.setData(poem_bund);
            handler.sendMessage(message);
        }
    }


}
