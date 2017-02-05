package com.example.administrator.surface;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;

import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.Toast;

import com.example.administrator.surface.Adapter.VideoAdapter;
import com.example.administrator.surface.Model.VideoList;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;

import java.util.ArrayList;
import java.util.HashMap;
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
    ProgressDialog progressDialog;
    Context context;
    Bitmap bitmap;
    Bitmap bitmap2;
    Bitmap bitmap3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        container = (FrameLayout) findViewById(R.id.container);
        context = this;
        /*progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(true);*/
        Thumbload();
        customView = new CustomView(getBaseContext());
        customView.setOnTouchListener(customView);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(300, 200);
        container.addView(customView, params);
       /* customView.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customView.videoView.start();
            }
        });
*/
        //customView.requestFocus();
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        // customView.videoView.setBackground(bitmapDrawable);
        dragmgr = new RecyclerViewDragDropManager();
        dragmgr.setInitiateOnMove(false);
        dragmgr.setInitiateOnLongPress(true);
        customView.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), " 아 몰라", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.HORIZONTAL, false));
        videoAdapter = new VideoAdapter(videoList, getBaseContext());
        recyclerView.setAdapter(dragmgr.createWrappedAdapter(videoAdapter));
        dragmgr.attachRecyclerView(recyclerView);
        videoAdapter.setOnItemClickListener(
                new VideoAdapter.OnitemClickListener() {
                    @Override
                    public void onItemClick(View itemview, int position) {
                        customView.progressBar.setVisibility(View.VISIBLE);
                        customView.videoView.setVideoURI(Uri.parse(videoList.get(position).getURL()));
                        customView.videoView.requestFocus();
                        customView.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                customView.progressBar.setVisibility(View.INVISIBLE);
                                customView.videoView.start();
                            }
                        });
                    }
                }
        );
        customView.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getBaseContext(), "영상 끝", Toast.LENGTH_SHORT).show();
            }
        });
    }
//    public static Bitmap getWebVideoThumbnail(Context context, Uri uri) {
//        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//        try {
//            retriever.setDataSource(uri.toString(), new HashMap<String, String>());
//            return retriever.getFrameAtTime(1000, MediaMetadataRetriever.OPTION_CLOSEST);
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                retriever.release();
//            } catch (RuntimeException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
    public void Thumbload() {
        mmr = new FFmpegMediaMetadataRetriever();
        mmr.setDataSource(VIDEO_URL);
        mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
        mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM_ARTIST);
        bitmap= mmr.getFrameAtTime(2000000, FFmpegMediaMetadataRetriever.OPTION_CLOSEST);
       // bitmap = getWebVideoThumbnail(context, Uri.parse(VIDEO_URL));
      //  bitmap2 = getWebVideoThumbnail(context, Uri.parse(VIDEO_URL2));
       // bitmap3 = getWebVideoThumbnail(context, Uri.parse(VIDEO_URL3));
        VideoList v = new VideoList(bitmap, VIDEO_URL, 1);
      //  videoList.add(v);
       // v = new VideoList(bitmap2, VIDEO_URL2, 2);
      //  videoList.add(v);
      //  v = new VideoList(bitmap3, VIDEO_URL3, 3);
        videoList.add(v);

    }
}
