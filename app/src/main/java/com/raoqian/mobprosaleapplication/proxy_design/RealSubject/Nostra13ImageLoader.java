package com.raoqian.mobprosaleapplication.proxy_design.RealSubject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.ImageLoaderProcessor;

import java.io.File;


/**
 * Created by Administrator on 2018/5/15.
 */

public class Nostra13ImageLoader implements ImageLoaderProcessor {
    private Context mContext;

    public Nostra13ImageLoader(Context context) {
        this.mContext = context;
        init();
    }

    @Override
    public void displayImageOnView(String path, ImageView view, int defaultRes) {
        ImageLoader.getInstance().displayImage(path, view);
    }


    private void init() {
        File cacheDir = StorageUtils.getCacheDirectory(mContext);
        // 初始化默认的ImageLoader配置参数

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions 缓存最大图片大小
                .diskCacheExtraOptions(480, 800, null) // 闪存最大图片大小
                .threadPoolSize(3) // default 最大线程数
                .threadPriority(Thread.NORM_PRIORITY - 2) // default 线程优先级
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default 线程处理队列，先进先出
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) // LruMemory
                .memoryCacheSize(2 * 1024 * 1024) // 缓存
                .memoryCacheSizePercentage(13)    // default 缓存比例？
                .diskCache(new UnlimitedDiskCache(cacheDir)) // default 闪存缓存
                .diskCacheSize(50 * 1024 * 1024) // 闪存缓存大小
                .diskCacheFileCount(100) // 闪存缓存图片文件数量
                //                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default 文件名
                .imageDownloader(new BaseImageDownloader(mContext)) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(getDefaultOptions()) // default
                .writeDebugLogs() // LOG
                .build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 默认的图片显示Options,可设置图片的缓存策略，编解码方式等，非常重要
     *
     * @return
     */
    private DisplayImageOptions getDefaultOptions() {

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                //                .showImageForEmptyUri(R.drawable...)//图片地址为空的时候显示的图片
                //                .showImageOnFail(R.drawable...)//出错的时候显示的图片
                .cacheInMemory(true)//设置图片可以缓存在内存中
                .cacheOnDisk(true)//设置图片可以缓存在硬盘中
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)//使用的图片解码类型
                .decodingOptions(new BitmapFactory.Options())//图片解码配置
                .resetViewBeforeLoading(true)
                .build();
        return options;

    }

}
