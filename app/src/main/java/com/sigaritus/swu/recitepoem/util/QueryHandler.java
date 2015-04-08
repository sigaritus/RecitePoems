package com.sigaritus.swu.recitepoem.util;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/4/8.
 */
public class QueryHandler extends Handler {
    //
    private static class HandlerHolder {
        private static final QueryHandler INSTANCE = new QueryHandler();
    }

    private QueryHandler(){

    }
    public final static QueryHandler getInstance(){
        return HandlerHolder.INSTANCE;
    }

    private TextView textView;
    @Override
    public void handleMessage(Message msg) {

        switch (msg.what){
            case Constant.GTEPOEM_LIST:

                break;
            case Constant.GETSENTECNE_LIST:
                break;

        }



        super.handleMessage(msg);
    }


    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
