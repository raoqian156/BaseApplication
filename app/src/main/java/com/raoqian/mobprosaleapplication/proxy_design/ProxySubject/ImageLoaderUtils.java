package com.raoqian.mobprosaleapplication.proxy_design.ProxySubject;

import android.widget.ImageView;

import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.ImageLoaderProcessor;
import com.raoqian.mobprosaleapplication.utils.TLog;

/**
 * Created by Administrator on 2018/5/14.
 */

public class ImageLoaderUtils {
    private static ImageLoaderProcessor processor;
    private static ImageLoaderUtils httpUtils;

    private ImageLoaderUtils() {
    }

    // 单例  
    private static ImageLoaderUtils getInstance() {
        if (httpUtils == null) {
            synchronized (ImageLoaderUtils.class) {
                if (httpUtils == null)
                    httpUtils = new ImageLoaderUtils();
            }
        }
        return httpUtils;
    }

    public static void init(ImageLoaderProcessor processor) {
        ImageLoaderUtils.processor = processor;
    }

    public static void show( String path, ImageView view, int defaultRes) {
        TLog.error("ImageLoaderUtils", "show.path = " + path);
        getInstance().displayImageOnView( path, view, defaultRes);
    }

    private void displayImageOnView( String path, ImageView view, int defaultRes) {
        processor.displayImageOnView( path, view, defaultRes);
    }

}
