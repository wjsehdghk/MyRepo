package com.example.administrator.surface;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.administrator.surface.Adapter.VideoAdapter;
import com.example.administrator.surface.Model.VideoList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wseemann.media.FFmpegMediaMetadataRetriever;

public class MainActivity extends AppCompatActivity {

    public final static String VIDEO_URL = "http://techslides.com/demos/sample-videos/small.mp4";
    public final static String VIDEO_URL2 = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    RecyclerView recyclerView;
    VideoView videoView;
    List<VideoList> videoList = new ArrayList<>();
    VideoAdapter videoAdapter;
    FFmpegMediaMetadataRetriever mmr;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        videoView = (VideoView) findViewById(R.id.videoView);
        pDialog = new ProgressDialog(getBaseContext());
        pDialog.setTitle("동영상 로딩중");
        pDialog.setMessage("Buffering");
        pDialog.setCancelable(false);
        pDialog.setIndeterminate(false);
        Thumbload();

        //

        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        videoAdapter = new VideoAdapter(videoList, getBaseContext());
        recyclerView.setAdapter(videoAdapter);

        videoAdapter.setOnItemClickListener(new VideoAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(View itemview, int position) {


                //pDialog.show();
                try {
                    videoView.setVideoURI(Uri.parse(VIDEO_URL));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                videoView.requestFocus();
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {

                        //pDialog.dismiss();
                        videoView.start();
                    }
                });
            }
        });

    }


    public void Thumbload() {

        mmr = new FFmpegMediaMetadataRetriever();
        mmr.setDataSource(VIDEO_URL);
        mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
        mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM_ARTIST);
        Bitmap bitmap = mmr.getFrameAtTime(-1, FFmpegMediaMetadataRetriever.OPTION_CLOSEST); //썸네일


        mmr.setDataSource(VIDEO_URL2);
        mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
        mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM_ARTIST);
        Bitmap bitmap2 = mmr.getFrameAtTime(-1, FFmpegMediaMetadataRetriever.OPTION_CLOSEST);


        // mmr.release();

        VideoList v = new VideoList(bitmap);
        videoList.add(v);

        v = new VideoList(bitmap2);
        videoList.add(v);

    }
}
