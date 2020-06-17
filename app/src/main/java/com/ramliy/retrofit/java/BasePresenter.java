package com.ramliy.retrofit.java;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by ramliy10 on 16/06/20.
 */
public abstract class BasePresenter {

    // List of calls to cancel on onDestroy
    private List<Call> cancellableCalls;

    protected void addAsCancellableCall(Call call) {
        if (cancellableCalls == null) cancellableCalls = new ArrayList<>();
        cancellableCalls.add(call);
    }

    public void onDestroy() {
        if (cancellableCalls != null && !cancellableCalls.isEmpty()) {
            for (Call call : cancellableCalls) {
                Log.d("check", "On destroy, cancel all calls");
                call.cancel();
            }
        }

        cancellableCalls = null;
    }
}