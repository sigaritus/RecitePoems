package com.sigaritus.swu.recitepoem.read;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sigaritus.swu.recitepoem.R;

/**
 * Created by Administrator on 2015/4/3.
 */
public class AddFragment extends Fragment{

    ImageView title_add,author_add,content_add,type_add,yes,no;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_fragment,container,false);
        title_add = (ImageView)view.findViewById(R.id.title_view);
        author_add = (ImageView)view.findViewById(R.id.author_view);
        content_add = (ImageView)view.findViewById(R.id.content_view);
        type_add = (ImageView)view.findViewById(R.id.type_view);
        yes = (ImageView)view.findViewById(R.id.right);
        no = (ImageView)view.findViewById(R.id.wrong);



        return view;
    }
}
