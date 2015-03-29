package com.sigaritus.swu.recitepoem;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private ViewPager viewpager;
    private View read_view,plan_view,data_view,test_view;
    private TextView title_view;
    private ImageView read_img_view,plan_img_view,data_img_view,test_img_view;
    private List<View> viewList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);


        initTextView();
        initViewpager();



    }


    private void initViewpager() {
        viewpager=(ViewPager) findViewById(R.id.viewpager);// threadid=1: thread exiting with uncaught exception
        LayoutInflater inflater = getLayoutInflater();
        read_view = inflater.inflate(R.layout.read_layout, null);
        plan_view = inflater.inflate(R.layout.plan_layout, null);
        data_view = inflater.inflate(R.layout.data_layout, null);
        test_view = inflater.inflate(R.layout.test_layout, null);

        viewList = new ArrayList<View>();

        viewList.add(read_view);
        viewList.add(plan_view);
        viewList.add(test_view);
        viewList.add(data_view);


        viewpager.setAdapter(new MyViewPagerAdapter(viewList));
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
                    title_view.setText("古诗阅读");
                    break;
                case 1:
                    plan_img_view.setBackgroundResource(R.drawable.plan1);
                    title_view.setText("学习计划");
                    break;
                case 2:
                    test_img_view.setBackgroundResource(R.drawable.test1);
                    title_view.setText("即时测试");
                    break;
                case 3:
                    data_img_view.setBackgroundResource(R.drawable.data1);
                    title_view.setText("学习数据");
                    break;

            };
            Toast.makeText(MainActivity.this, "view" + viewpager.getCurrentItem() + "", Toast.LENGTH_SHORT).show();
        }
    }


    private void initTextView() {

        title_view = (TextView)findViewById(R.id.title_text);
        read_img_view =(ImageView)findViewById(R.id.read_img);
        plan_img_view =(ImageView)findViewById(R.id.plan_img);
        test_img_view =(ImageView)findViewById(R.id.test_img);
        data_img_view =(ImageView)findViewById(R.id.data_img);

        read_img_view.setOnClickListener(new MyOnClickListener(0));
        plan_img_view.setOnClickListener(new MyOnClickListener(1));
        test_img_view.setOnClickListener(new MyOnClickListener(2));
        data_img_view.setOnClickListener(new MyOnClickListener(3));

        read_img_view.setBackgroundResource(R.drawable.read1);
        title_view.setText("古诗阅读");
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
                    title_view.setText("古诗阅读");
                    break;
                case 1:
                    plan_img_view.setBackgroundResource(R.drawable.plan1);
                    title_view.setText("学习计划");
                    break;
                case 2:
                    test_img_view.setBackgroundResource(R.drawable.test1);
                    title_view.setText("即时测试");
                    break;
                case 3:
                    data_img_view.setBackgroundResource(R.drawable.data1);
                    title_view.setText("学习数据");
                    break;
            };
        }

    }

    private void resetImage(){
        read_img_view.setBackgroundResource(R.drawable.read);
        plan_img_view.setBackgroundResource(R.drawable.plan);
        test_img_view.setBackgroundResource(R.drawable.test);
        data_img_view.setBackgroundResource(R.drawable.data);
    }

    public class MyViewPagerAdapter extends PagerAdapter{
        private List<View> mListViews;

        public MyViewPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) 	{
            container.removeView(mListViews.get(position));
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mListViews.get(position), 0);
            return mListViews.get(position);
        }

        @Override
        public int getCount() {
            return  mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
