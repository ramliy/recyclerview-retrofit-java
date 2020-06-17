package com.ramliy.retrofit.java;

/**
 * Created by ramliy10 on 16/06/20.
 */
public class BaseContract {
    public interface RemoteView {
        void setProgressIndicator(final boolean active);

        void showEmpty(String message);

        void showError(String message);
    }

}
