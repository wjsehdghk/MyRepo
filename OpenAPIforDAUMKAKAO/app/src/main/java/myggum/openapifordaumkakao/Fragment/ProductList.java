package myggum.openapifordaumkakao.Fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import myggum.openapifordaumkakao.APIService;
import myggum.openapifordaumkakao.Channel;
import myggum.openapifordaumkakao.Item;
import myggum.openapifordaumkakao.ListAdapter;
import myggum.openapifordaumkakao.ListAdapter2;
import myggum.openapifordaumkakao.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Administrator on 2016-11-28.
 */
public class ProductList extends Fragment {
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    Retrofit retrofit;
    APIService apiService;
    ListAdapter listAdapter;
    ListAdapter2 listAdapter2;
    ProgressDialog dialog;
    Channel channel;
    List<Item> items;
    List<Item> itemList;
    static final String apikey = "4f4ce90940718827c723e492596efbc4";
    public ProductList() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://apis.daum.net/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        apiService = retrofit.create(APIService.class);
        channel = new Channel();
        dialog = new ProgressDialog(getContext());
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.productlist, container, false);
        Log.d("tag", "test222222");
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d("tag", "test11111");
        Call<Channel> call = apiService.getImage(apikey, "햄버거", "xml");
        Call<Channel> call2 = apiService.getImage(apikey, "나주곰탕", "xml");
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.recycler2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        // recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);
        dialog.setMessage("데이터 불러오는중..");
        dialog.show();

        call.enqueue(new Callback<Channel>() {
            @Override
            public void onResponse(Call<Channel> call, Response<Channel> response) {
                int statuscode = response.code();
                // Toast.makeText(getActivity(), "code : " + statuscode, Toast.LENGTH_SHORT).show();
                dialog.hide();
                items = response.body().getItemList();
                listAdapter = new ListAdapter(items, getContext());
                listAdapter.setItemClick(new ListAdapter.ItemClick() {
                    @Override
                    public void onClick(View view, int position) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                        String str = items.get(position).getTitle();

                        alert.setMessage(str + "\n"+"해당 제품 설정화면으로 이동하시겠습니까?");
                        alert.setIcon(R.drawable.file);
                        alert.setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getContext(), "예", Toast.LENGTH_SHORT).show();
                            }
                        });

                        alert.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        alert.show();
                    }
                });
                recyclerView.setAdapter(listAdapter);
            }

            @Override
            public void onFailure(Call<Channel> call, Throwable t) {
                String str = t.getLocalizedMessage();
                Log.d("tag:::", "msg-->" + str);
            }
        });
        call2.enqueue(new Callback<Channel>() {
            @Override
            public void onResponse(Call<Channel> call, Response<Channel> response) {
                int statuscode = response.code();
                itemList = response.body().getItemList();
                dialog.hide();

                listAdapter2=new ListAdapter2(itemList,getContext());
                listAdapter2.setItemClick(new ListAdapter2.ItemClick() {
                    @Override
                    public void onClick(View view, int position) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                        String str = itemList.get(position).getTitle();
                        alert.setMessage(str +"\n" + " 해당제품 설정화면으로 이동하시겠습니까?");
                        alert.setIcon(R.drawable.file);
                        alert.setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getContext(), "예", Toast.LENGTH_SHORT).show();
                            }
                        });

                        alert.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        alert.show();
                    }
                });
                //  Toast.makeText(getBaseContext(), "code : " + statuscode, Toast.LENGTH_SHORT).show();
                recyclerView2.setAdapter(listAdapter2);
                //  recyclerView2.scrollToPosition(itemList.size()-1);
            }
            @Override
            public void onFailure(Call<Channel> call, Throwable t) {
                String str = t.getLocalizedMessage();
                Log.d("tag:::", "msg-->" + str);
            }
        });
    }
}
