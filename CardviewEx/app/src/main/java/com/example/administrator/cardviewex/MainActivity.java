package com.example.administrator.cardviewex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.cardviewex.Adapter.ItemAdapter;
import com.example.administrator.cardviewex.Model.ITem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<ITem> items = new ArrayList<>();
    RecyclerView recyclerView;
    ITem iTem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         recyclerView= (RecyclerView)findViewById(R.id.recycler);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        ItemAdapter itemAdapter=new ItemAdapter(items);

        recyclerView.setAdapter(itemAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        iTem=new ITem(R.drawable.bmw,"BMW");
        items.add(iTem);
        iTem= new ITem(R.drawable.five,"CAFE FIVE");
        items.add(iTem);
        iTem= new ITem(R.drawable.mini,"MINI GOLD");
        items.add(iTem);

        itemAdapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemview, int position) {
                String title=items.get(position).getTitle();
                Toast.makeText(getApplicationContext(),title,Toast.LENGTH_LONG).show();
            }
        });

        itemAdapter.notifyDataSetChanged();



    }
}
