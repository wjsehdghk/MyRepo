package com.example.administrator.surface;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.example.administrator.surface.Adapter.VideoAdapter;
import com.example.administrator.surface.Model.VideoList;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import java.util.ArrayList;
import java.util.List;
import wseemann.media.FFmpegMediaMetadataRetriever;




public class MainActivity extends AppCompatActivity {
    public final static String VIDEO_URL = "http://techslides.com/demos/sample-videos/small.mp4";
    public final static String VIDEO_URL2 = "http://www.html5videoplayer.net/videos/toystory.mp4";
    public final static String VIDEO_URL3 = "http://www.archive.org/download/Unexpect2001/Unexpect2001_512kb.mp4";
    public List<VideoList> videoList = new ArrayList<>();
    VideoAdapter videoAdapter;
    CustomView customView;
    FFmpegMediaMetadataRetriever mmr;
    RecyclerView recyclerView;
    RecyclerViewDragDropManager dragmgr;
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        container = (FrameLayout) findViewById(R.id.container);
        Thumbload();
        customView = new CustomView(getBaseContext());
        customView.setOnTouchListener(customView);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
        container.addView(customView);
        customView.requestFocus();
        dragmgr = new RecyclerViewDragDropManager();
        dragmgr.setInitiateOnMove(false);
        dragmgr.setInitiateOnLongPress(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        videoAdapter = new VideoAdapter(videoList, getBaseContext());
        recyclerView.setAdapter(dragmgr.createWrappedAdapter(videoAdapter));
        dragmgr.attachRecyclerView(recyclerView);}
        /*videoAdapter.setOnItemClickListener(new VideoAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(View itemview, int position) {
                try {
                   // customView.videoView.setVideoURI(Uri.parse(videoList.get(position).getURL()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                customView.requestFocus();
              //  customView.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                       // customView.videoView.start();
                    }
                });
            }
        });
    }
*/
    public void Thumbload() {
        mmr = new FFmpegMediaMetadataRetriever();
        mmr.setDataSource(VIDEO_URL);
        mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
        mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM_ARTIST);
        Bitmap bitmap = mmr.getFrameAtTime(2000000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST);
        mmr.setDataSource(VIDEO_URL3);
        mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
        mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM_ARTIST);
        Bitmap bitmap1 = mmr.getFrameAtTime(2000000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST);
        mmr.setDataSource(VIDEO_URL2);
        mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
        mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM_ARTIST);
        Bitmap bitmap2 = mmr.getFrameAtTime(2000000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST);
        VideoList v = new VideoList(bitmap, VIDEO_URL, 1);
        videoList.add(v);
        v = new VideoList(bitmap2, VIDEO_URL2, 2);
        videoList.add(v);
        v = new VideoList(bitmap1, VIDEO_URL3, 3);
        videoList.add(v);
    }
}
