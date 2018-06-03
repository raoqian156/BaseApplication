package com.raoqian.mobprosaleapplication.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/21.
 */

@Entity
public class NetCashInfo implements Serializable {

    private static final long serialVersionUID = 536871008L;
    @Unique
    private String url;
    private long requestTime;
    private String content;

    @Keep
    public NetCashInfo(String url, long requestTime, String content) {
        this.url = url;
        this.requestTime = requestTime;
        this.content = content;
    }

    @Generated(hash = 1727051383)
    public NetCashInfo() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
