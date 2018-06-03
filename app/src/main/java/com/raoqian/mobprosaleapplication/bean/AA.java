package com.raoqian.mobprosaleapplication.bean;

/**
 * Created by Administrator on 2018/5/17.
 */


public class AA {
    String port, max, serverName;
    int min;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Override
    public String toString() {
        return "getMax = " + getMax() + " | getPort = " + getPort() + "getMin = " + getMin() + "  getServerName = " + getServerName();
    }
}
