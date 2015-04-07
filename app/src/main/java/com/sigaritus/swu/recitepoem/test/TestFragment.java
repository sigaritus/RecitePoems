package com.sigaritus.swu.recitepoem.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_layout,container,false);
        Random r1 = new Random();
        TextView question = (TextView)view.findViewById(R.id.question_view);

        RadioButton radioButton_a = (RadioButton)view.findViewById(R.id.radio_a);
        RadioButton radioButton_b = (RadioButton)view.findViewById(R.id.radio_b);
        RadioButton radioButton_c = (RadioButton)view.findViewById(R.id.radio_c);
        RadioButton radioButton_d = (RadioButton)view.findViewById(R.id.radio_d);

        TextView text_a = (TextView)view.findViewById(R.id.text_a);
        TextView text_b = (TextView)view.findViewById(R.id.text_b);
        TextView text_c = (TextView)view.findViewById(R.id.text_c);
        TextView text_d = (TextView)view.findViewById(R.id.text_d);

        TextView pre =(TextView)view.findViewById(R.id.pre_text);
        TextView sub = (TextView)view.findViewById(R.id.submit_text);
        TextView next = (TextView)view.findViewById(R.id.next_text);


        SentenceDAO sentenceDAO = new SentenceDAO(getActivity());

        List<String> sList = sentenceDAO.getAllSentences();
        Log.i("slist------testfragment",sList.size()+"");

        Sentence sentence1  = sentenceDAO.find(r1.nextInt(sList.size()));
        Log.i("sentence-------",sentence1.toString());
        String s = sentence1.getContent();

        String[] ques = s.split("，");

        int getques = r1.nextInt(2);
        question.setText(ques[getques]);

        int answer = r1.nextInt(4);

        switch (answer){
            case 0:
                text_a.setText(ques[getques==0?1:0]);
                break;
            case 1:
                text_b.setText(ques[getques==0?1:0]);
                break;
            case 2:
                text_c.setText(ques[getques==0?1:0]);
                break;
            case 3:
                text_d.setText(ques[getques==0?1:0]);
                break;
        }

        return view;

    }
}
