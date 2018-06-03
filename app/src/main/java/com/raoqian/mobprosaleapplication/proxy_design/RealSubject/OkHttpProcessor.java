package com.raoqian.mobprosaleapplication.proxy_design.RealSubject;


import android.util.Log;

import com.raoqian.mobprosaleapplication.ProgressRequestBody;
import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.HttpCallBack;
import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.HttpProcessor;
import com.raoqian.mobprosaleapplication.proxy_design.AbstractSubject.HttpProgressListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/5/15.
 */

public class OkHttpProcessor extends HttpProcessor {
    OkHttpClient client = new OkHttpClient
            .Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();

    @Override
    public void get(String url, Map<String, Object> params, final HttpCallBack callBack) {
        //调用ok的get请求
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        final Call call = client.newCall(request);
        //同步execute
        //同步请求
        //同步是耗时的
        //同步execute需要开启子线程
        new Thread(() -> {
            try {
                Response response = call.execute();
                if (response.isSuccessful()) {
                    String string = response.body().string();
                    //调用者只需要实现provide方法就能拿到这个String了
                    backOnSuccess(url, string, callBack);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    @Override
    public void post(String url, Map<String, Object> map, final HttpCallBack processorBack) {
        Callback callBack = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                backOnFail(url, e.getMessage(), processorBack);
                Log.e("OkHttpProcessor.LINE", "e.getMessage()  - " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) {
//processorBack.onSuccess(response.message());
                Log.e("OkHttpProcessor", "response -> " + response.toString());
//                Log.e("OkHttpProcessor.LINE", "70  - " + response.toString() + "\n" + response.body().string());
                try {
                    if (200 == response.code()) {
                        backOnSuccess(url, response.body().string(), processorBack);
                    } else {
                        Log.d("OkHttpProcessor", "backOnFail.Line- 88");
                        backOnFail(url, response.message(), processorBack);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    backOnFail(url, e.getMessage(), processorBack);
                }
            }
        };
        //上传文字格式 数据的传输，区别于多媒体输出
        FormBody.Builder formbody = new FormBody.Builder();
        if (map != null && !map.isEmpty()) {
            //上传参数
            for (String key : map.keySet()) {
                formbody.add(key, map.get(key).toString());
            }
        }
        FormBody body = formbody.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        //异步请求方式
        call.enqueue(callBack);
    }

    @Override
    public void uploadFile(String path, HashMap<String, Object> map, String fileName, String filePath, final HttpProgressListener callBack) {
        postSingleFileOkHttp(path, map, fileName, filePath, callBack);
    }

    //    public static final int DOWNLOAD_FAIL = 0;
//    public static final int DOWNLOAD_PROGRESS = 1;
//    public static final int DOWNLOAD_SUCCESS = 2;
//
//
//            new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case DOWNLOAD_PROGRESS:
//                    listener.onDownloading((Integer) msg.obj);
//                    break;
//                case DOWNLOAD_FAIL:
//                    listener.onDownloadFailed();
//                    break;
//                case DOWNLOAD_SUCCESS:
//                    listener.onDownloadSuccess((String) msg.obj);
//                    break;
//            }
//        }
//    };

    @Override
    public void download(String url, String saveDir, HttpProgressListener listener) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Message message = Message.obtain();
//                message.what = DOWNLOAD_FAIL;
//                mHandler.sendMessage(message);
                backOnFail(url, e.getMessage(), listener);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                //储存下载文件的目录
                String savePath = isExistDir(saveDir);
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(savePath, getNameFromUrl(url));
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
//                        Log.e("OkHttpProcessor", "完成百分比: " + progress);
//                        //下载中
//                        Message message = Message.obtain();
//                        message.what = DOWNLOAD_PROGRESS;
//                        message.obj = progress;
//                        mHandler.sendMessage(message);
//                        listener.onProgress(sum, total);
                        refuseView(total, sum, listener);

                    }
                    fos.flush();
                    //下载完成
//                    Message message = Message.obtain();
//                    message.what = DOWNLOAD_SUCCESS;
//                    message.obj = file.getAbsolutePath();
//                    mHandler.sendMessage(message);
                    backOnSuccess(url, file.getAbsolutePath(), listener);
                } catch (Exception e) {
//                    Message message = Message.obtain();
//                    message.what = DOWNLOAD_FAIL;
//                    mHandler.sendMessage(message);
                    backOnFail(url, e.getMessage(), listener);
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {

                    }
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {

                    }
                }
            }
        });
    }

    private String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }


    private String isExistDir(String saveDir) throws IOException {
        File downloadFile = new File(saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }

    /**
     * MultipartBody：用来提交包涵文件的参数
     *
     * @param url      :路径
     * @param map      ：普通参数
     * @param fileName ：提交文件的文件名
     * @param imgPath  ：提交文件的路径
     */
    public void postSingleFileOkHttp(String url, HashMap<String, Object> map, String fileName, String imgPath, HttpProgressListener callBack) {
        MediaType type = MediaType.parse("application/octet-stream");//"text/xml;charset=utf-8"
        File file = new File(imgPath);
        RequestBody fileBody = RequestBody.create(type, file);


        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        if (map != null && !map.isEmpty()) {
            //上传参数
            for (String key : map.keySet()) {
                requestBody.addFormDataPart(key, String.valueOf(map.get(key)));
            }
        }
        requestBody.addPart(Headers.of(
                "Content-Disposition",
                "form-data; name=\"file\"; filename=\"" + fileName + "\"")
                , fileBody);
        ProgressRequestBody progressRequestBody = new ProgressRequestBody(requestBody.build(), 150, callBack);
        Request request = new Request.Builder()
                .url(url)
                .post(progressRequestBody)//requestBody.build())
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                backOnFail(url, e.getMessage(), callBack);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                backOnSuccess(url, response.body().string(), callBack);
            }
        });
    }

    /**
     * MultipartBody：用来提交包涵文件的参数
     * 多文件上传
     */
    public void postMultiFileOkHttp(String path, HashMap<String, String> map, String img, List<String> imgPths, Callback callBack) {
        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        if (map != null && !map.isEmpty()) {
            //上传参数
            for (String key : map.keySet()) {
                requestBody.addFormDataPart(key, map.get(key));
            }
        }
        //遍历paths中所有图片绝对路径到builder，并约定key如“upload”作为后台接受多张图片的key
        if (imgPths != null && imgPths.size() > 0) {
            for (String string : imgPths) {
                File file = new File(string);
                if (file.exists()) {
                    requestBody.addFormDataPart(img, file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
                } else {
                    Log.e("OkHttpProcessor", "postMultiFileOkHttp file(" + string + ") doesn't exists");
                }
            }
        }
        Request request = new Request.Builder()
                .post(requestBody.build())
                .url(path)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callBack);
    }
}
