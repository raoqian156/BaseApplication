package com.raoqian.mobprosaleapplication.glide;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Util;
import com.raoqian.mobprosaleapplication.R;
import com.raoqian.mobprosaleapplication.utils.TLog;

/**
 * Created by Administrator on 2017/4/17 0017
 * Glide 加载图片
 */
public class GlideImageUtil {


    private static String imgepath = "";

    /**
     * load 图片
     * 以Activity生命周期为主
     */
    public static void Load(Activity activity, ImageView imageView, String url) {
        TLog.showUsingWhere();
        if (!isOnMainThread()) {
            return;
        }
        Load(activity, imageView, url, true);
    }

    /**
     * 以全局生命周期为主
     *
     * @param context
     * @param imageView
     * @param url
     */
    public static void Load(Context context, ImageView imageView, String url) {
        if (!isOnMainThread()) {
            return;
        }
        Glide.with(context).load(url).error(R.mipmap.ic_default_vertical_temp_image).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.mipmap.ic_default_vertical_temp_image).crossFade().dontAnimate().into(imageView);
    }
//
//    /**
//     * 图片圆角处理
//     */
//    public static void loadRoundImg(Context context, ImageView imageView, String url, int round) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        //.placeholder(R.mipmap.ic_default_vertical_temp_image)
//        Glide.with(context).load(url).error(R.drawable.ic_default_vertical_error)
//                .transform(new GlideRoundTransform(context, round))
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.ic_default_vertical_error)
//                .crossFade().dontAnimate().into(imageView);
//    }
//
//    /**
//     * 加载头像
//     */
//    public static void loadImg(Context context, ImageView imageView, String url) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context).load(url).error(R.mipmap.default_yungao)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.mipmap.default_yungao)
//                .crossFade().dontAnimate().into(imageView);
//    }
//
//    public static void LoadHead(Context context, ImageView imageView, String url) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context).load(url).error(R.mipmap.ic_default_person_all_icon).placeholder(R.mipmap.ic_default_person_all_icon).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().dontAnimate().into(imageView);
//    }
//
//    public static void LoadcenterCrop(Context context, ImageView imageView, String url) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context).load(url).error(R.mipmap.ic_default_vertical_temp_image).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.ic_default_vertical_temp_image).crossFade().dontAnimate().centerCrop().into(imageView);
//    }
//
//    /**
//     * 加载本地资源文件
//     *
//     * @param context
//     * @param imageView
//     * @param url
//     */
//    public static void Loadresource(Context context, ImageView imageView, int url) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context).load(url).error(R.mipmap.ic_default_vertical_temp_image).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.ic_default_vertical_temp_image).crossFade().dontAnimate().centerCrop().into(imageView);
//    }
//
//    /**
//     * 兼容自适应图片
//     */
//    public static void LoadWrap(Context context, ImageView imageView, String url, int width, int height) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context).load(url).override(width, height).error(R.mipmap.ic_default_vertical_temp_image).
//                diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.ic_default_vertical_temp_image).crossFade().dontAnimate().into(imageView);
//    }
//
//    /**
//     * 兼容自适应图片
//     */
//    public static void LoadWrap(Context context, ImageView imageView, String url) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context).load(url).error(R.mipmap.ic_default_vertical_temp_image).
//                diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.ic_default_vertical_temp_image).crossFade().dontAnimate().into(imageView);
//    }
//
//    /**
//     * 以全局生命周期为主加载视频图片
//     *
//     * @param context
//     * @param imageView
//     * @param url
//     */
//    public static void LoadVideo(Context context, ImageView imageView, String url) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context)
//                .load(new File(url)).asBitmap().fitCenter()
//                .placeholder(R.mipmap.ic_default_vertical_temp_image)
//                .error(R.mipmap.ic_default_vertical_temp_image)
//                .into(imageView);
//    }

//    /**
//     * 以全局生命周期为主加载视频图片
//     *
//     * @param context
//     * @param imageView
//     * @param url
//     */
//    public static void LoadVideoOrFile(Context context, ImageView imageView, String url) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        if (url.startsWith("https:") || url.startsWith("http:")) {
//            Glide.with(context).load(url).error(R.mipmap.ic_default_vertical_temp_image).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.ic_default_vertical_temp_image).crossFade().dontAnimate().into(imageView);
//        } else {
//            Glide.with(context)
//                    .load(new File(url)).asBitmap().fitCenter()
//                    .placeholder(R.mipmap.ic_default_vertical_temp_image)
//                    .error(R.mipmap.ic_default_vertical_temp_image)
//                    .into(imageView);
//        }
//    }

    /**
     * load 图片
     * 添加图片淡入加载的效果
     * crossFade()默认true
     */
    public static void Load(Activity activity, ImageView imageView, String url, boolean isCrossFade) {
        if (!isOnMainThread()) {
            return;
        }
        Load(activity, imageView, url, R.mipmap.ic_default_vertical_temp_image, isCrossFade);
    }
//
//    /**
//     * glide加载圆形图片
//     */
//    public static void LoadCircle(Context context, String url, ImageView imageView) {
//        Glide.with(context).load(url).placeholder(R.mipmap.ic_default_vertical_temp_image).error(R.mipmap.ic_default_vertical_temp_image).transform(new GlideCircleTransform(context)).into(imageView);
//    }
//
//    /**
//     * glide加载圆角图片
//     */
//    public static void LoadRound(Context context, String url, ImageView imageView) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context).load(url).placeholder(R.mipmap.ic_default_vertical_temp_image).error(R.mipmap.ic_default_vertical_temp_image)
//                .transform(new CenterCrop(context), new GlideRoundTransform(context, 5)).dontAnimate().into(imageView);
//    }
//
//    /**
//     * glide加载圆角图片
//     */
//    public static void LoadRound(Context context, String url, ImageView imageView, int circularDp) {
//        Glide.with(context).load(url).placeholder(R.mipmap.ic_default_vertical_temp_image).error(R.mipmap.ic_default_vertical_temp_image)
//                .transform(new CenterCrop(context), new GlideRoundTransform(context, circularDp)).into(imageView);
//    }
//
//
//    /**
//     * glide加载圆角高斯模糊图片
//     */
//    public static void LoadRoundBlur(Context context, String url, ImageView imageView) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context).load(url).placeholder(R.mipmap.ic_default_vertical_temp_image).error(R.mipmap.ic_default_vertical_temp_image)
//                .bitmapTransform(new CenterCrop(context), new BlurTransformation(context, 14, 3), new GlideRoundTransform(context, 5)).dontAnimate().into(imageView);
//    }

    /**
     * 按照规格加载圆形图片
     */
    public static void Load(Context context, String url, int width, int height, ImageView imageView) {
        if (!isOnMainThread()) {
            return;
        }
        Glide.with(context).load(url)
                .error(R.mipmap.ic_default_vertical_temp_image)
                .override(width, height)
                .transform(new GlideCircleTransform(context)).into(imageView);
    }

//    /**
//     * 以全局生命周期为主,加载图片不做缓存
//     *
//     * @param context
//     * @param imageView
//     * @param url
//     */
//    public static void LoadNoCache(Context context, ImageView imageView, String url) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context).load(url).error(R.mipmap.ic_default_vertical_temp_image).diskCacheStrategy(DiskCacheStrategy.NONE).placeholder(R.mipmap.ic_default_vertical_temp_image).crossFade().dontAnimate().into(imageView);
//    }
//
//    /**
//     * glide加载高斯模糊图片
//     * 14:设置模糊度(在0.0到25.0之间)，默认25;
//     * 3:图片缩放比例,默认1。
//     */
//    public static void LoadGaussian(Context context, int resId, ImageView imageView) {
//        Glide.with(context).load(resId).bitmapTransform(new BlurTransformation(context, 14, 3)).dontAnimate().into(imageView);
//    }
//
//    /**
//     * glide加载高斯模糊图片
//     * 2:设置模糊度(在0.0到25.0之间)，默认25;
//     * 3:图片缩放比例,默认1。
//     */
//    public static void LoadGaussian(Context context, String url, ImageView imageView) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context).load(url).bitmapTransform(new BlurTransformation(context, 2, 3)).dontAnimate().into(imageView);
//    }
//
//    /**
//     * glide加载高斯模糊图片
//     * definition:设置模糊度(在0.0到25.0之间)，默认25;
//     * scale:图片缩放比例,默认1。
//     */
//    public static void LoadGaussian(Context context, String url, ImageView imageView, int definition, int scale) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context).load(url).bitmapTransform(new BlurTransformation(context, definition, scale)).dontAnimate().into(imageView);
//    }
//

    /**
     * load背景
     *
     * @param activity
     * @param imageView
     * @param url
     * @param drawableId
     * @param isCrossFade
     */
    public static void Load(Activity activity, ImageView imageView, String url, int drawableId, boolean isCrossFade) {
        if (!isOnMainThread()) {
            return;
        }
        if (isCrossFade) {
            Glide.with(activity).load(url).error(R.mipmap.ic_default_vertical_temp_image).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(drawableId).crossFade().dontAnimate().into(imageView);
        } else {
            Glide.with(activity).load(url).error(R.mipmap.ic_default_vertical_temp_image).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(drawableId).dontAnimate().into(imageView);
        }
    }

    /**
     * load背景
     *
     * @param activity
     * @param imageView
     * @param url
     * @param drawableId
     */
    public static void Load(Activity activity, ImageView imageView, String url, int drawableId, final RequestUtilListener listener) {
        if (!isOnMainThread()) {
            return;
        }
        Glide.with(activity).load(url).error(R.mipmap.ic_default_vertical_temp_image).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(drawableId).crossFade().dontAnimate().listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                listener.onException(e, model, target, isFirstResource);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                listener.onResourceReady(resource, model, target, isFromMemoryCache, isFirstResource);
                return false;
            }
        }).into(imageView);


    }
//
//    //异步下载缓存图片
//    public static void downLoad(final Context context, final String url) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context.getApplicationContext()).load(url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
//
//    }
//
//    //加载缩略图，没网加载原图
//    public static void loadThumbnail(final Context context, final ImageView imageView, String largeUrl, final String smallUrl, final ProgressBar progressBar) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        //用其它图片作为缩略图
//        DrawableTypeRequest<String> thumbnailRequest = Glide
//                .with(context)
//                .load(smallUrl);
//
//        Glide.with(context).load(largeUrl)
//                .dontAnimate()
//                .listener(new RequestListener<String, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        GlideImageUtil.Load((Activity) context, imageView, smallUrl, 0, false);
//                        return true;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        progressBar.setVisibility(View.GONE);
//                        return false;
//                    }
//
//                }).diskCacheStrategy(DiskCacheStrategy.ALL)
//                .thumbnail(thumbnailRequest).into(imageView);
//    }
//
//    /**
//     * 加载缩略图
//     */
//    public static void loadThumbnail(Context context, final ImageView imageView, String url) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context).load(url).error(R.mipmap.ic_default_temp_image_yungao).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().placeholder(R.mipmap.ic_default_temp_image_yungao).dontAnimate().thumbnail(0.1f).into(imageView);
//    }
//
//    //加载背景图
//    public static void loadBackground(Context context, String url, final RelativeLayout relativeLayout) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context)
//                .load(url)
//                .asBitmap()
//                .transform(new GlideRoundTransform(context, 5))
//                .dontAnimate()
//                .into(new SimpleTarget<Bitmap>(SimpleTarget.SIZE_ORIGINAL, SimpleTarget.SIZE_ORIGINAL) {
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                        Drawable drawable = new BitmapDrawable(resource);
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                            relativeLayout.setBackground(drawable);
//                        }
//                    }
//                });
//    }
//
//    //加载高斯模糊背景图
//    public static void loadBlurBackground(Context context, String url, final RelativeLayout relativeLayout) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context)
//                .load(url)
//                .asBitmap()
//                .transform(new GlideRoundTransform(context, 5))
//                .dontAnimate()
//                .into(new SimpleTarget<Bitmap>(SimpleTarget.SIZE_ORIGINAL, SimpleTarget.SIZE_ORIGINAL) {
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                        Drawable drawable = new BitmapDrawable(resource);
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                            relativeLayout.setBackground(drawable);
//                        }
//                    }
//                });
//    }
//
//    /**
//     * 设置高斯模糊图片
//     *
//     * @param context
//     * @param url
//     * @param imageView
//     */
//    public static void loadBlurImg(Context context, String url, final ImageView imageView) {
//        if (!isOnMainThread()) {
//            return;
//        }
//        Glide.with(context).load(url).error(R.mipmap.ic_default_vertical_temp_image).bitmapTransform(new BlurTransformation(context, 5)).into(imageView);
//    }
//
//    public static String GlideImageCachePath(final Context context, final String url) {
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(500);
//                    imgepath = getImgPathFromCache(context, url);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        return imgepath;
//
//    }
//
//    private static String getImgPathFromCache(Context context, String url) {
//        FutureTarget<File> future = Glide.with(context).load(url).downloadOnly(100, 100);
//        try {
//            File cacheFile = future.get();
//            String path = cacheFile.getAbsolutePath();
//            return path;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//

    /**
     * 是否主线程运行
     */
    private static boolean isOnMainThread() {
        return Util.isOnMainThread();
    }

    public interface RequestUtilListener {
        boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource);

        boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource);
    }
}
