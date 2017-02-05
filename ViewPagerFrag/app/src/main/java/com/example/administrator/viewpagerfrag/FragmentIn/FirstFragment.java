package com.example.administrator.viewpagerfrag.FragmentIn;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.viewpagerfrag.R;




/**
 * Created by Administrator on 2016-08-12.
 */
public class FirstFragment extends Fragment {
    private String title;
    private int page;
    TextView tx;
    View view;
    public static FirstFragment newInstance(int page,String title){
        FirstFragment firstFragment=new FirstFragment();
        Bundle args=new Bundle();
        args.putInt("someInt",page);
        args.putString("someTitle",title);
        firstFragment.setArguments(args);
        return firstFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page=getArguments().getInt("someInt",0);
        title=getArguments().getString("someTitle");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_first,container,false);
        tx=(TextView)view.findViewById(R.id.text);
        tx.setText(page + "   ==   " + title);
        return view;
    }
}
