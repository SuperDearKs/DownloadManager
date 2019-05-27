package com.downloadtwo.mylibrary;

import java.io.Closeable;
import java.io.IOException;


/**
 * Created by wz on 2019/5/20.
 */

    public class IOUtil {
        public static void closeAll(Closeable... closeables){
            if(closeables == null){
                return;
            }
            for (Closeable closeable : closeables) {
                if(closeable!=null){
                    try {
                        closeable.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
}
