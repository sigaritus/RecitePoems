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
import com.sigaritus.swu.recitepoem.bean.Poem;
import com.sigaritus.swu.recitepoem.util.PoemDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/31.
 */
public class GridViewAdapter extends BaseSwipeAdapter {

    private Context mContext;
    private PoemDAO dao ;
    public GridViewAdapter(Context mContext) {
        this.mContext = mContext;
        this.dao = new PoemDAO(mContext);
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
        TextView  poem_view = (TextView)convertView.findViewById(R.id.poem_text);

        Poem poem = dao.find(position+1);




        if (poem!=null) {
            poem_view.setText(poem.getTitle()+"\n"+poem.getAuthor()+"\n"+poem.getContent());

        }

    }

    @Override
    public int getCount() {
        return (int)dao.getCount();
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