package com.example.administrator.productlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.productlist.Adapter.ProductAdapter;
import com.example.administrator.productlist.Model.ProductList;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<ProductList> pList = new ArrayList<>();
    ProductAdapter adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareProduct();
        recyclerView= (RecyclerView)findViewById(R.id.recycler);
        adapter2= new ProductAdapter(pList,getBaseContext());
        adapter2.setOnItemClickListener(new ProductAdapter.OnitemClickListener() {
            @Override
            public void onItemClick(View itemview, int position) {
                String name = pList.get(position).getName1();
                Toast.makeText(getBaseContext(),name,Toast.LENGTH_SHORT).show();


                            }
        });
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter2);
        recyclerView.setHasFixedSize(true);
    }
    private void prepareProduct() {
        int[] covers = new int[]{
                R.drawable.zzz,
                R.drawable.aaa
        };
        ProductList a = new ProductList(covers[0], "마이이젤1번","세로","123123123");
        pList.add(a);
        a = new ProductList(covers[0], "마이이젤2번","세로","1412123");
        pList.add(a);
        a = new ProductList(covers[1], "마이이젤3번","가로","5123");
        pList.add(a);


    }
}
