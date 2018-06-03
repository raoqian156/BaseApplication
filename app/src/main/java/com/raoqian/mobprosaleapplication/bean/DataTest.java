package com.raoqian.mobprosaleapplication.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/14.
 */
public class DataTest implements Serializable {

    /**
     * fileNumber : 5
     * resFileBeans : [{"fileName":"img","filePath":"http://www.29160047.com:80/resources/img"},{"fileName":"js","filePath":"http://www.29160047.com:80/resources/js"},{"fileName":"html","filePath":"http://www.29160047.com:80/resources/html"},{"fileName":"css","filePath":"http://www.29160047.com:80/resources/css"},{"fileName":"mp4","filePath":"http://www.29160047.com:80/resources/mp4"}]
     */

    private int fileNumber;
    private List<ResFileBeansBean> resFileBeans;

    public DataTest(int fileNumber) {
        this.fileNumber = fileNumber;
    }

    public DataTest() {
    }

    public int getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(int fileNumber) {
        this.fileNumber = fileNumber;
    }

    public List<ResFileBeansBean> getResFileBeans() {
        return resFileBeans;
    }

    public void setResFileBeans(List<ResFileBeansBean> resFileBeans) {
        this.resFileBeans = resFileBeans;
    }
    public static class ResFileBeansBean implements Serializable {
        /**
         * fileName : img
         * filePath : http://www.29160047.com:80/resources/img
         */

        private String fileName;
        private String filePath;

        public ResFileBeansBean(String fileName, String filePath) {
            this.fileName = fileName;
            this.filePath = filePath;
        }

        public ResFileBeansBean() {
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
    }
}
