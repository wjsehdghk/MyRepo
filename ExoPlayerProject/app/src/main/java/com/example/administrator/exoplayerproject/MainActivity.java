package com.example.administrator.exoplayerproject;

import android.appwidget.AppWidgetProviderInfo;
import android.media.MediaCodec;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.upstream.DefaultUriDataSource;
import com.google.android.exoplayer.util.Util;

public class MainActivity extends AppCompatActivity {

    //public String URL="http://sites.google.com/ubiaccessmobile/sample_video.mp4";
    //public VideoView videoView;

    private ExoPlayer exoPlayer;
    private SurfaceView surfaceView;
    private int RenderCount = 300000;
    private int minBufferms = 250000;
    private final int BUFFER_SEGMENT_SIZE= 64*1024;
    private final int BUFFER_SEGMENT_COUNT=256;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Preparing the ExoPlayer

        surfaceView=(SurfaceView)findViewById(R.id.surfaceview);
        String userAgent= Util.getUserAgent(getApplicationContext(),"ExoplayerDemo");
        String url =  "http://www.sample-videos.com/video/mp4/480/big_buck_bunny_480p_5mb.mp4";
        Allocator allocator = new DefaultAllocator(minBufferms);
        DataSource dataSource= new DefaultUriDataSource(getApplicationContext(),null,userAgent);
        ExtractorSampleSource sampleSource= new ExtractorSampleSource(Uri.parse(url),dataSource,allocator,BUFFER_SEGMENT_SIZE*BUFFER_SEGMENT_COUNT);
        MediaCodecVideoTrackRenderer videoTrackRenderer =new
                MediaCodecVideoTrackRenderer(this, sampleSource,
                MediaCodec.VIDEO_SCALING_MODE_SCALE_TO_FIT);
        //Creating an instance of the Exoplayer

        exoPlayer = ExoPlayer.Factory.newInstance(RenderCount);
        exoPlayer.prepare(videoTrackRenderer);

        //Muting Player
        exoPlayer.sendMessage(videoTrackRenderer,MediaCodecVideoTrackRenderer.MSG_SET_SURFACE,surfaceView.getHolder().getSurface());

        //play and Stop ExoPlayer
        exoPlayer.setPlayWhenReady(true);

        /*
        exoPlayer.setPlayWhenReady(false);
        exoPlayer.stop();
        exoPlayer.release();
        */

        // 1 -> Audio Renderer
        // 2 -> Audio and Video Renderer
       // exoPlayer=ExoPlayer.Factory.newInstance(1);
        /*
        videoView=(VideoView)findViewById(R.id.videoview);
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        videoView.setVideoURI(Uri.parse(URL));
        videoView.requestFocus();
        */
    }
}
