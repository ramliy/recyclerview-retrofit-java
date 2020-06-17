package com.ramliy.retrofit.java.util.network;

/**
 * Created by ramliy10 on 16/06/20.
 */
public class RemoteCallback {
    public interface Load<T> {
        void onDataLoaded(T data);

        void onFailed(String message);

        void onDataEmpty(String message);
    }
}
