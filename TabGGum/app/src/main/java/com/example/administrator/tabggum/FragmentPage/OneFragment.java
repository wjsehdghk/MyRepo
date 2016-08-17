package com.example.administrator.tabggum.FragmentPage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.tabggum.Adapter.BackgroundAdapter;
import com.example.administrator.tabggum.Model.BackgroundImage;
import com.example.administrator.tabggum.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-08-17.
 */
public class OneFragment extends Fragment {
    RecyclerView recyclerView;
    Context context;
    RecyclerView.LayoutManager layoutManager;
    public List<BackgroundImage> imageList=new ArrayList<>();
    RecyclerView.Adapter adapter;
    int[] covers = new int[]{
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
    };
    BackgroundImage a;
    public OneFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BackgroundImage a = new BackgroundImage(covers[0], "MyGGUm");
        imageList.add(a);
         a = new BackgroundImage(covers[1], "hello");
        imageList.add(a);
          a = new BackgroundImage(covers[2], "Maroon 5");
        imageList.add(a);
          a = new BackgroundImage(covers[3], "Born to Die");
        imageList.add(a);
          a = new BackgroundImage(covers[4], "Honeymoon");
        imageList.add(a);
        a = new BackgroundImage(covers[5], "I Need a Doctor");
        imageList.add(a);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new BackgroundAdapter(getContext(), imageList);
        recyclerView.setAdapter(adapter);
        prepareImage();

        return view;
    }

    private void prepareImage() {


    }
}
