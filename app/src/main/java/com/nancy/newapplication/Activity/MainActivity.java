package com.nancy.newapplication.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.nancy.newapplication.Adapter.RecyclerViewAdapter;
import com.nancy.newapplication.Application.FlowerApplication;
import com.nancy.newapplication.Model.Flowers;
import com.nancy.newapplication.R;
import com.nancy.newapplication.Service.ApiService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.FlowerClickListener{


    @Inject
    ApiService mApiService;

    RecyclerView mRecyclerView;

    private RecyclerViewAdapter mAdapter;
    List<Flowers> mListArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler);
        resolveDependency();
        configureView();
        loadData();
    }

    private void resolveDependency() {
        ((FlowerApplication) getApplication())
                .getApiComponent()
                .inject(MainActivity.this);
    }

    private void loadData() {
        //ApiService apiService = RetrofitInstance.getAPIService();
        Observable<List<Flowers>> flowersResponse = mApiService.getFlowers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        flowersResponse.subscribeWith(new Observer<List<Flowers>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Flowers> flowers) {
                mListArray = new ArrayList<>();
                List<Flowers>  response = flowers;
                for(int i=0;i<response.size();i++)
                {
                    Flowers flower  = response.get(i);
                    flower.setName(response.get(i).getName());
                    flower.setPrice(response.get(i).getPrice());
                    flower.setPhoto(response.get(i).getPhoto());

                    mListArray.add(flower);

                }
                mAdapter = new RecyclerViewAdapter(MainActivity.this, getLayoutInflater(),mListArray);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void configureView() {
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    @Override
    public void onClick(int position, String name) {
        Toast.makeText(getApplicationContext()," clicked ",Toast.LENGTH_LONG).show();

    }
}
