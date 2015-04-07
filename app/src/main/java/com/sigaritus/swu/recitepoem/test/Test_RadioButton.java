package com.sigaritus.swu.recitepoem.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.sigaritus.swu.recitepoem.R;

/**
 * Created by Administrator on 2015/4/7.
 */
public class Test_RadioButton extends RadioButton {

    public Test_RadioButton(Context context) {
        super(context);
    }

    public Test_RadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean isChecked() {
        return super.isChecked();
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
    }

    @Override
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        super.setOnCheckedChangeListener(listener);
    }

    @Override
    public void setButtonDrawable(int resid) {
        super.setButtonDrawable(resid);
    }

    @Override
    public void setButtonDrawable(Drawable d) {
        super.setButtonDrawable(d);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isChecked()){
            super.setButtonDrawable(R.drawable.checked1111);
        }else{
            super.setButtonDrawable(R.drawable.unchecked1111);
        }
    }
}
