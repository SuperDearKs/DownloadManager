package com.downloadtwo.mylibrary.listener;



/**
 * Created by wz on 2019/5/20.
 */


public interface DownFileCallback {

    void onSuccess( String url);

    void onFail(String msg);

    void onProgress(long totalSize, long downSize);
}
