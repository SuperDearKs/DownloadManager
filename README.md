# DownloadManager
基于OK+Retrofit的断点下载工具

使用方法：

1.Add it in your root build.gradle at the end of repositories:

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
 2.Add the dependency
  
  implementation 'com.github.SuperDearKs:DownloadManager:0.1' 
  
  implementation 'com.squareup.okhttp3:okhttp:3.6.0' 
  
  implementation 'io.reactivex.rxjava2:rxandroid:2.0.1' 
  
  implementation 'io.reactivex.rxjava2:rxjava:2.0.1' 
  
  
3.在你的AndroidManifest文件的application标签下添加
  android:networkSecurityConfig="@xml/network_security_config"
  
4.在你需要用的地方使用：（事先需要获取sd权限）

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
