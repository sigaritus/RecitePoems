package com.sigaritus.swu.recitepoem.read;

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
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.sigaritus.swu.recitepoem.R;
import com.sigaritus.swu.recitepoem.bean.Poem;
import com.sigaritus.swu.recitepoem.util.PoemDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/31.
 */
public class GridViewAdapter extends BaseSwipeAdapter {

    private Context mContext;

    private SwipeLayout swipeLayout;
    private TextView  poem_view;
    private List<String> mList;
    public GridViewAdapter(Context mContext) {
        this.mContext = mContext;

    }
    public GridViewAdapter(Context mContext,List<String> mList) {
        this.mContext = mContext;

        this.mList =mList;
        Log.i("mlist-----------",mList.size()+"");
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {

        return R.id.swipe;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View view =LayoutInflater.from(mContext).inflate(R.layout.read_layout, null);

       swipeLayout = (SwipeLayout)view.findViewById(getSwipeLayoutResourceId(position));

        swipeLayout.setDragEdge(SwipeLayout.DragEdge.Left);

        ImageView star=(ImageView)view.findViewById(R.id.star);
        ImageView clock=(ImageView)view.findViewById(R.id.clock);
        ImageView delete=(ImageView)view.findViewById(R.id.delete);




        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"star",Toast.LENGTH_SHORT).show();
            }
        });
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"clock",Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"delete",Toast.LENGTH_SHORT).show();
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


//        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
//            @Override
//            public void onDoubleClick(SwipeLayout layout, boolean surface) {
//                Toast.makeText(mContext,"doubleclicked",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mContext,ReadPoemActivity.class);
//
//                Bundle poem_view_bund = new Bundle();
//                poem_view_bund.putString("poem",poem_view.getText()+"");
//
//                mContext.startActivity(intent);
//
//            }
//        });

//        poem_view.setOnClickListener(new View.OnClickListener() {
//
//           public void onClick(View v) {
//                Intent intent = new Intent(mContext,ReadPoemActivity.class);
////
//
//                intent.putExtra("poem",poem_view.getText().toString());
//
//                mContext.startActivity(intent);
//               Log.i("after start ","after ");
//                   }
//              }
//
//        );
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