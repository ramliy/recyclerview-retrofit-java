package com.ramliy.retrofit.java.service.retrofit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ramliy10 on 16/06/20.
 */
public abstract class DefaultCallback<T> implements Callback {
    @Override
    public void onResponse(Call call, Response response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
//                onSuccess(response.body().ge, response.code());
                return;
            }
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }

    public abstract void onSuccess(@NonNull final T response, int code);

    public abstract void onError(@Nullable String message);
}
