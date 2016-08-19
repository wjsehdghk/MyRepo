package com.example.administrator.tabggum.FragmentPage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.tabggum.Adapter.BackgroundAdapter;
import com.example.administrator.tabggum.MainActivity;
import com.example.administrator.tabggum.Model.BackgroundImage;
import com.example.administrator.tabggum.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-08-17.
 */

public class OneFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    LinearLayoutManager linearLayoutManager;
    StaggeredGridLayoutManager gridLayoutManager;
    public List<BackgroundImage> imageList = new ArrayList<>();
    RecyclerView.Adapter adapter;

    public OneFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareBackground();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




        recyclerView.setHasFixedSize(true);
        //gridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager = new GridLayoutManager(getContext(), 2);
        // linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(2), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new BackgroundAdapter(getContext(), imageList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void prepareBackground(){
        int[] covers = new int[]{
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5,
                R.drawable.image6,
                R.drawable.mina,
                R.drawable.ha
        };
        BackgroundImage a = new BackgroundImage(covers[0], "MyGGum");
        imageList.add(a);
        a = new BackgroundImage(covers[1], "마이꿈");
        imageList.add(a);
        a = new BackgroundImage(covers[2], "마이이젤");
        imageList.add(a);
        a = new BackgroundImage(covers[3], "마이메뉴");
        imageList.add(a);
        a = new BackgroundImage(covers[4], "마이인포");
        imageList.add(a);
        a = new BackgroundImage(covers[5], "마이카운터");
       imageList.add(a);
        a = new BackgroundImage(covers[6], "신민아");
        imageList.add(a);
        a = new BackgroundImage(covers[7], "하연수");
        imageList.add(a);


    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    public int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
