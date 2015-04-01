package com.sigaritus.swu.recitepoem.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sigaritus.swu.recitepoem.R;

/**
 * Created by Administrator on 2015/3/31.
 */
public class SearchFragment extends Fragment {
    WebView searchview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_layout,container,false);
        searchview = (WebView)view.findViewById(R.id.gushiwen);
        searchview.loadUrl("http://m.gushiwen.org/");
        searchview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        return view;

    }
}
