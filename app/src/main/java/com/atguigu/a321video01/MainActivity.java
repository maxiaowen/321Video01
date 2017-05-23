package com.atguigu.a321video01;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.atguigu.a321video01.fragment.BaseFragment;
import com.atguigu.a321video01.pager.LocalAudioPager;
import com.atguigu.a321video01.pager.LocalVideoPager;
import com.atguigu.a321video01.pager.NetAudioPager;
import com.atguigu.a321video01.pager.NetVideoPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg_main;
    private ArrayList<BaseFragment> fragments;
    private int position;

    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isGrantExternalRW(this);// 解决安卓6.0以上版本不能读取外部存储权限的问题
        rg_main = (RadioGroup)findViewById(R.id.rg_main);
        initFragment();
        rg_main.setOnCheckedChangeListener(new MyCheckedChangeListener());
        rg_main.check(R.id.rb_local_video);

    }

    public static boolean isGrantExternalRW(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);

            return false;
        }
        return true;
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new LocalVideoPager());
        fragments.add(new LocalAudioPager());
        fragments.add(new NetAudioPager());
        fragments.add(new NetVideoPager());
    }

     class MyCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
         @Override
         public void onCheckedChanged(RadioGroup group, int checkedId) {
             switch (checkedId) {
                 case R.id.rb_local_video :

                     position = 0;
                     break;
                 case R.id.rb_local_audio :

                     position = 1;
                     break;
                 case R.id.rb_net_audio :

                     position = 2;
                     break;
                 case R.id.rb_net_video :

                     position = 3;
                     break;
             }

             //根据位置得到对应的Fragment
             BaseFragment fragment = fragments.get(position);
             addFragment(fragment);
         }
     }

    private void addFragment(Fragment fragment) {
        
        if(tempFragment != fragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if(!fragment.isAdded()) {
               if(tempFragment != null) {
                   ft.hide(tempFragment);
               }
                ft.add(R.id.fl_content,fragment);
            }else {
                if(tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(fragment);
            }

            ft.commit();
            tempFragment = fragment;
        }
    }
}
