package com.sigaritus.swu.recitepoem.read;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.sigaritus.swu.recitepoem.R;

/**
 * Created by Administrator on 2015/3/31.
 */
public class GridViewAdapter extends BaseSwipeAdapter {

    private Context mContext;

    public GridViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        View view =LayoutInflater.from(mContext).inflate(R.layout.read_layout, null);
        ImageView star=(ImageView)view.findViewById(R.id.star);
        ImageView clock=(ImageView)view.findViewById(R.id.clock);
        ImageView delete=(ImageView)view.findViewById(R.id.delete);
        TextView  poem_view = (TextView)view.findViewById(R.id.poem_text);



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
    }

    @Override
    public int getCount() {
        return 50;
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