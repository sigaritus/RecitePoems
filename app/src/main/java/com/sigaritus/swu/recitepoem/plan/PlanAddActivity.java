package com.sigaritus.swu.recitepoem.plan;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sigaritus.swu.recitepoem.R;
import com.sigaritus.swu.recitepoem.bean.Plan;
import com.sigaritus.swu.recitepoem.util.PlanDAO;

//acvitity   actionbaractivity XX
public class PlanAddActivity extends Activity {
    private DatePicker dp;
    private TimePicker tp;
    private EditText note;
    private TextView confirm ;
    private TextView cancel;
    private PlanDAO planDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_add);

        planDAO = new PlanDAO(this);

        confirm = (TextView)findViewById(R.id.plan_conf);
        cancel = (TextView)findViewById(R.id.plan_cancel);

        note = (EditText)findViewById(R.id.note_edt);

        dp = (DatePicker)findViewById(R.id.dtpicker);
        tp = (TimePicker)findViewById(R.id.tpPicker);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("plan add","before add");
                planDAO.add(new Plan(note.getText().toString(),dp.getYear()+"年"+dp.getMonth()+"月"+dp.getDayOfMonth()
                        +"日"+tp.getCurrentHour()+":"+tp.getCurrentMinute()));
                Toast.makeText(PlanAddActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        cancel = (TextView)findViewById(R.id.plan_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




    }


}
