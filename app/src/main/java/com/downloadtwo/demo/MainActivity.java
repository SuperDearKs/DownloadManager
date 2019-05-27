package com.downloadtwo.demo;

import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apeng.permissions.EsayPermissions;
import com.apeng.permissions.OnPermission;
import com.apeng.permissions.Permission;
import com.downloadtwo.mylibrary.DownLoadObserver;
import com.downloadtwo.mylibrary.DownloadInfo;
import com.downloadtwo.mylibrary.DownloadManager;
import com.downloadtwo.mylibrary.listener.DownFileCallback;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button downloadBtn1, downloadBtn2, downloadBtn3;
    private Button cancelBtn1, cancelBtn2, cancelBtn3;
    private ProgressBar progress1, progress2, progress3;
    private String url1 = "http://imtt.dd.qq.com/16891/89E1C87A75EB3E1221F2CDE47A60824A.apk?fsname=com.snda.wifilocating_4.2.62_3192.apk";
    private String url2 = "http://imtt.dd.qq.com/16891/89E1C87A75EB3E1221F2CDE47A60824A.apk";
    private String url3 = "http://download.sdk.mob.com/apkbus.apk";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();


        downloadBtn1 = bindView(R.id.main_btn_down1);
        downloadBtn2 = bindView(R.id.main_btn_down2);
        downloadBtn3 = bindView(R.id.main_btn_down3);

        cancelBtn1 = bindView(R.id.main_btn_cancel1);
        cancelBtn2 = bindView(R.id.main_btn_cancel2);
        cancelBtn3 = bindView(R.id.main_btn_cancel3);

        progress1 = bindView(R.id.main_progress1);
        progress2 = bindView(R.id.main_progress2);
        progress3 = bindView(R.id.main_progress3);

        downloadBtn1.setOnClickListener(this);
        downloadBtn2.setOnClickListener(this);
        downloadBtn3.setOnClickListener(this);

        cancelBtn1.setOnClickListener(this);
        cancelBtn2.setOnClickListener(this);
        cancelBtn3.setOnClickListener(this);


    }


    private <T extends View> T bindView(@IdRes int id) {
        View viewById = findViewById(id);
        return (T) viewById;
    }


    /**
     * 6.0 权限控制
     */
    public void requestPermission() {
        EsayPermissions.with(this)
                .constantRequest() //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
//                .permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES) //支持请求6.0悬浮窗权限8.0请求安装权限
                .permission(Permission.WRITE_EXTERNAL_STORAGE)
                .request(new OnPermission() {
                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {
                        if (isAll) {
                            Toast.makeText(MainActivity.this, "获取权限成功", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "取权限成功，部分权限未正常授予", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                            Toast.makeText(MainActivity.this, "被永久拒绝授权，请手动授予权限", Toast.LENGTH_LONG).show();
                            //如果是被永久拒绝就跳转到应用权限系统设置页面
                            EsayPermissions.gotoPermissionSettings(MainActivity.this);
                        } else {
                            Toast.makeText(MainActivity.this, "获取权限失败", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_down1:
                DownloadManager.getInstance().downloadPath(AppStoragePath.getCachePath()).download(url1, new DownFileCallback() {
                    @Override
                    public void onProgress(long totalSize, long downSize) {
                        progress1.setMax((int) totalSize);
                        progress1.setProgress((int) downSize);
                    }

                    @Override
                    public void onSuccess(String url) {
                        Toast.makeText(MainActivity.this, url1 + "下载完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(String msg) {
                        Toast.makeText(MainActivity.this, url1 + "下载失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.main_btn_down2:
                DownloadManager.getInstance().downloadPath(AppStoragePath.getCachePath()).download(url2, new DownFileCallback() {
                    @Override
                    public void onProgress(long totalSize, long downSize) {
                        progress2.setMax((int) totalSize);
                        progress2.setProgress((int) downSize);
                    }

                    @Override
                    public void onSuccess(String url) {
                        Toast.makeText(MainActivity.this, url2 + "下载完成", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFail(String msg) {
                        Toast.makeText(MainActivity.this, url2 + "下载失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.main_btn_down3:
                DownloadManager.getInstance().downloadPath(AppStoragePath.getCachePath()).download(url3, new DownFileCallback() {
                    @Override
                    public void onProgress(long totalSize, long downSize) {
                        progress3.setMax((int) totalSize);
                        progress3.setProgress((int) downSize);
                    }

                    @Override
                    public void onSuccess(String url) {
                        Toast.makeText(MainActivity.this, url3 + "下载完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(String msg) {
                        Toast.makeText(MainActivity.this, url3 + "下载失败", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.main_btn_cancel1:
                DownloadManager.getInstance().cancel(url1);
                break;
            case R.id.main_btn_cancel2:
                DownloadManager.getInstance().cancel(url2);
                break;
            case R.id.main_btn_cancel3:
                DownloadManager.getInstance().cancel(url3);
                break;
        }

    }
}
