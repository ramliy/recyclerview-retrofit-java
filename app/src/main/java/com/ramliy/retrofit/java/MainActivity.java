package com.ramliy.retrofit.java;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ramliy.retrofit.java.adapter.HomeAdapter;
import com.ramliy.retrofit.java.model.genre.Genre;
import com.ramliy.retrofit.java.model.genre.GenreResponse;
import com.ramliy.retrofit.java.service.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvEmptyData;
    private RecyclerView rvHome;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView.Adapter adapter;
    private List<Genre> listGenre = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvEmptyData = findViewById(R.id.tv_empty_data);
        rvHome = findViewById(R.id.rv_home);
        mSwipeRefreshLayout = findViewById(R.id.refresh);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();
            }
        });

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvHome.setLayoutManager(layoutManager);
        rvHome.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        fetchData();
    }

    private void fetchData() {

        mSwipeRefreshLayout.setRefreshing(true);
        Call<List<GenreResponse>> call = ApiService.getInstance().getListGenres(BuildConfig.API_KEY);
        call.enqueue(new Callback<List<GenreResponse>>() {
            @Override
            public void onResponse(Call<List<GenreResponse>> call, Response<List<GenreResponse>> response) {
                System.out.println("onResponse " + response.isSuccessful());
                adapter = new HomeAdapter(listGenre);
                rvHome.setAdapter(adapter);
                mSwipeRefreshLayout.setRefreshing(false);
                tvEmptyData.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<GenreResponse>> call, Throwable t) {
                System.out.println("onFailure " + t.getMessage());

            }
        });

    }

}
