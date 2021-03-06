package com.sigaritus.swu.recitepoem;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;


import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.sigaritus.swu.recitepoem.plan.PlanFragment;
import com.sigaritus.swu.recitepoem.read.AddActivity;
import com.sigaritus.swu.recitepoem.read.CollectActivity;
import com.sigaritus.swu.recitepoem.read.ReadFragment;
import com.sigaritus.swu.recitepoem.search.SearchFragment;
import com.sigaritus.swu.recitepoem.test.TestFragment;
import com.sigaritus.swu.recitepoem.util.PoemDAO;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ViewPager viewpager;
    public DBManager dbHelper;
    private ImageView read_img_view, plan_img_view,search_img_view,test_img_view;
    private List<Fragment> fragmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("oncreate","mainactivity poem 1");

        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbHelper = new DBManager(this);
        dbHelper.openDatabase();
        dbHelper.closeDatabase();
        Log.i("oncreate","mainactivity poem 1");
        initTextView();
        Log.i("oncreate","mainactivity poem 2");
        initViewpager();
        Log.i("oncreate","mainactivity poem 3");

    }


    private void initViewpager() {
        viewpager=(ViewPager) findViewById(R.id.viewpager);// threadid=1: thread exiting with uncaught exception

        fragmentList = new ArrayList<Fragment>();

        fragmentList.add(new ReadFragment());
        fragmentList.add(new PlanFragment());
        fragmentList.add(new TestFragment());
        fragmentList.add(new SearchFragment());

        viewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),
                fragmentList));
        //设置显示哪页

        viewpager.setCurrentItem(0);
        viewpager.setOnPageChangeListener(new MyOnPageChangeListener());

    }


    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {


        public void onPageScrollStateChanged(int arg0) {


        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {


        }

        public void onPageSelected(int arg0) {

            resetImage();

            switch (arg0) {
                case 0:
                    read_img_view.setBackgroundResource(R.drawable.read1);
//
                    break;
                case 1:
                    plan_img_view.setBackgroundResource(R.drawable.plan1);
                    break;
                case 2:
                    test_img_view.setBackgroundResource(R.drawable.test1);
                    break;
                case 3:
                    search_img_view.setBackgroundResource(R.drawable.search1);
                    break;

            };

        }
    }


    private void initTextView() {

        read_img_view =(ImageView)findViewById(R.id.read_img);
        plan_img_view =(ImageView)findViewById(R.id.plan_img);
        test_img_view =(ImageView)findViewById(R.id.test_img);
        search_img_view =(ImageView)findViewById(R.id.search_img);

        read_img_view.setOnClickListener(new MyOnClickListener(0));
        plan_img_view.setOnClickListener(new MyOnClickListener(1));
        test_img_view.setOnClickListener(new MyOnClickListener(2));
        search_img_view.setOnClickListener(new MyOnClickListener(3));
//
        read_img_view.setBackgroundResource(R.drawable.read1);
//
    }

    private class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            resetImage();
            viewpager.setCurrentItem(index);
            switch (index){
                case 0:
                    read_img_view.setBackgroundResource(R.drawable.read1);
                    break;
                case 1:
                    plan_img_view.setBackgroundResource(R.drawable.plan1);
                    break;
                case 2:
                    test_img_view.setBackgroundResource(R.drawable.test1);
                    break;
                case 3:
                    search_img_view.setBackgroundResource(R.drawable.search1);
                    break;
            };
        }

    }

    private void resetImage(){
        read_img_view.setBackgroundResource(R.drawable.read);
        plan_img_view.setBackgroundResource(R.drawable.plan);
        test_img_view.setBackgroundResource(R.drawable.test);
        search_img_view.setBackgroundResource(R.drawable.search);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.collect_poem) {
            Intent collect_intent = new Intent(MainActivity.this, CollectActivity.class);
                startActivity(collect_intent);
            return true;
        }else if (id==R.id.add_poem){
            Intent add_intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(add_intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
