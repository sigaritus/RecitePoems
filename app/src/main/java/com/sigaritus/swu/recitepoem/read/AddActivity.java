package com.sigaritus.swu.recitepoem.read;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sigaritus.swu.recitepoem.R;
import com.sigaritus.swu.recitepoem.bean.Poem;
import com.sigaritus.swu.recitepoem.util.PoemDAO;

/**
 * Created by Administrator on 2015/4/3.
 */
public class AddActivity extends Activity {

    ImageView title_add,author_add,content_add,type_add,yes,no;
    EditText  title_edt,author_edt,content_edt,type_edt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_fragment);

        title_add = (ImageView)findViewById(R.id.title_view);
        author_add = (ImageView)findViewById(R.id.author_view);
        content_add = (ImageView)findViewById(R.id.content_view);
        type_add = (ImageView)findViewById(R.id.type_view);
        yes = (ImageView)findViewById(R.id.right);
        no = (ImageView)findViewById(R.id.wrong);

        title_edt = (EditText)findViewById(R.id.title_edt);
        author_edt = (EditText)findViewById(R.id.author_edt);
        content_edt = (EditText)findViewById(R.id.content_edt);
        type_edt = (EditText)findViewById(R.id.type_edt);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PoemDAO dao = new PoemDAO(AddActivity.this);

                dao.add(new Poem(title_edt.getText().toString(),author_edt.getText().toString(),
                        content_edt.getText().toString(),type_edt.getText().toString()));
                Toast.makeText(AddActivity.this,"add a new poem",Toast.LENGTH_SHORT).show();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddActivity.this.finish();
            }
        });


    }
}
