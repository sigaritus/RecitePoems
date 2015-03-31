package com.sigaritus.swu.recitepoem;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

public class FragmentAdapter extends FragmentPagerAdapter{

	List<Fragment> list ;
	
	public FragmentAdapter(FragmentManager fm,List<Fragment> list) {
		super(fm);
		
		this.list = list;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

    @Override
    public boolean isViewFromObject(View arg0, Object arg1)
    {
        return arg0 == arg1;
    }



}
