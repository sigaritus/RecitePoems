package com.sigaritus.swu.recitepoem.read;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.daimajia.swipe.util.Attributes;
import com.sigaritus.swu.recitepoem.R;


public class ReadFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View read_view = inflater.inflate(R.layout.grid_view,container,false);
         GridView gridView = (GridView)read_view.findViewById(R.id.gridview);
         GridViewAdapter adapter = new GridViewAdapter(getActivity());
        adapter.setMode(Attributes.Mode.Multiple);
        gridView.setAdapter(adapter);
        gridView.setSelected(false);
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("onItemLongClick", "onItemLongClick:" + position);
                return false;
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("onItemClick","onItemClick:" + position);
            }
        });


        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("onItemSelected","onItemSelected:" + position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Inflate the layout for this fragment
        return read_view;
        //返回操作后的view  inflater只是初始化。。
    }



}
