package com.atguigu.a321video01.pager;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.a321video01.fragment.BaseFragment;

/**
 * Created by Administrator on 2017/5/19.
 */

public class NetVideoPager extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {

        textView = new TextView(context);
        textView.setTextColor(Color.RED);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);

        return textView;
    }
    public void initData(){

        super.initData();
        textView.setText("网络视频的内容");
    }
}
