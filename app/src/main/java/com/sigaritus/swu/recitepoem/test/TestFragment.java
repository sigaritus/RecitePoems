package com.sigaritus.swu.recitepoem.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigaritus.swu.recitepoem.R;

/**
 * Created by Administrator on 2015/4/5.
 */
public class TestFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_layout,container,false);

        return view;

    }
}
