package com.nancy.newapplication.ViewModel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.Toast;

import com.nancy.newapplication.Model.Flowers;
import com.nancy.newapplication.Service.ApiService;
import com.nancy.newapplication.Service.RetrofitInstance;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class FlowerViewModel {

    private Context context;
    public ObservableInt progressBar;
    public final ObservableField<String > name = new ObservableField<>("");
    public final ObservableField<Long > price = new ObservableField<>();

    public FlowerViewModel(Context context)
    {
        this.context = context;
        progressBar = new ObservableInt(View.GONE);
    }


    public void getData()
    {
        progressBar.set(View.VISIBLE);

        ApiService apiService = RetrofitInstance.getAPIService();
        Observable<List<Flowers>> flowersResponse = apiService.getFlowers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        flowersResponse.subscribeWith(new Observer<List<Flowers>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Flowers> flowers) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    void showToast(String msg)
    {

        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();

    }
}
