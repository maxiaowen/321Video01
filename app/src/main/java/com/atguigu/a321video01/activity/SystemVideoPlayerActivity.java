package com.atguigu.a321video01.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.atguigu.a321video01.R;

public class SystemVideoPlayerActivity extends AppCompatActivity {
    private VideoView vv;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video_player);
        vv = (VideoView)findViewById(R.id.vv);
        uri = getIntent().getData();
        setListener();
        //设置播放地址
        vv.setVideoURI(uri);
        //设置控制面板
        vv.setMediaController(new MediaController(this));
    }

    //监听方法
    private void setListener() {
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            //底层准备播放完成监听
            @Override
            public void onPrepared(MediaPlayer mp) {
                vv.start();//开始播放
            }
        });
        //播放出错监听
        vv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(SystemVideoPlayerActivity.this, "播放出错了哦", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        //设置监听播放完成
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(SystemVideoPlayerActivity.this, "视频播放完成", Toast.LENGTH_SHORT).show();
                finish();//退出当前页面
            }
        });
    }
}
