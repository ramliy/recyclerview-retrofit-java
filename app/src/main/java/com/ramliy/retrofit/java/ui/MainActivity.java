package com.ramliy.retrofit.java.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ramliy.retrofit.java.BuildConfig;
import com.ramliy.retrofit.java.R;
import com.ramliy.retrofit.java.adapter.HomeAdapter;
import com.ramliy.retrofit.java.adapter.MovieAdapter;
import com.ramliy.retrofit.java.model.genre.Genre;
import com.ramliy.retrofit.java.model.genre.GenreResponse;
import com.ramliy.retrofit.java.model.movie.Movie;
import com.ramliy.retrofit.java.service.retrofit.ApiService;
import com.ramliy.retrofit.java.ui.movie.MovieContract;
import com.ramliy.retrofit.java.ui.movie.MoviePresenter;

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

public class MainActivity extends AppCompatActivity implements MovieContract.MovieView, SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView rvHome;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView.Adapter adapter;
    private MoviePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvHome = findViewById(R.id.rv_home);
        mSwipeRefreshLayout = findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mPresenter = new MoviePresenter(this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvHome.setLayoutManager(layoutManager);

        showData();
    }

    private void showData() {
        mPresenter.loadMovies();
    }

    @Override
    public void setProgressIndicator(final boolean active) {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmpty(String message) {
        Toast.makeText(this, getString(R.string.data_empty), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoviesLoaded(List<Movie> itemList) {
        if (isFinishing()) return;
        adapter = new MovieAdapter(itemList);
        rvHome.setAdapter(adapter);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onRefresh() {
        showData();
    }
}
