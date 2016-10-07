package com.example.administrator.customtest.FragmentPage;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.customtest.Adapter.ViewPagerAdapter;
import com.example.administrator.customtest.CustomView.CustomEdit;
import com.example.administrator.customtest.CustomView.CustomTest;
import com.example.administrator.customtest.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016-09-28.
 */
public class OneFragment extends Fragment{

    RelativeLayout relativeLayout;
    CustomEdit customEdit;
    CustomEdit[] ce;
    Button button2;
    Button display;
    int count =0 ;
    AnimationDrawable animationDrawable;
    ImageView imageview;
    ImageView imageview2;
    ImageView imageView3;
    ViewPagerAdapter adapter;
   BitmapDrawable drawable;
    BitmapDrawable drawable2;
    public   OneFragment() {
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        ce = new CustomEdit[10];
        return view;

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(getContext(),"저장됨",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Toast.makeText(getContext(),"저장된값 가져온다",Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {


        imageview = (ImageView)view.findViewById(R.id.imageView);
        imageview2 = (ImageView) view.findViewById(R.id.imageView2);
        imageView3 = (ImageView)view.findViewById(R.id.imageView3);
        drawable = (BitmapDrawable)imageview.getDrawable();
        drawable2 = (BitmapDrawable)imageview2.getDrawable();

        animationDrawable = new AnimationDrawable();

        animationDrawable.addFrame(drawable,1000);
        animationDrawable.addFrame(drawable2,5000);





        animationDrawable.setOneShot(true);




        display = (Button)view.findViewById(R.id.button);
        final Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.flow);


        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {


            }


            @Override
            public void onAnimationEnd(Animation animation) {
                //ce[0].clearAnimation();
                //ce[1].startAnimation(animation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageView3.setBackground(animationDrawable);
                animationDrawable.start();

                //if(ce[0]!=null)
                ///    ce[0].startAnimation(animation);
                // else
                // Toast.makeText(getContext(),"자막이 없습니다",Toast.LENGTH_SHORT).show();
            }
        });
        button2= (Button)view.findViewById(R.id.create);
        relativeLayout = (RelativeLayout)view.findViewById(R.id.container);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //ce[count] = new CustomEdit(getContext());


               // RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);


               // relativeLayout.addView(ce[count],params);


                //ce[count].requestFocus();

               // count++;
            }
        });
    }
}
