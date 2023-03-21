package com.netease.lib_api.model.user;

/**
 * 通过login接口返回来的bean
 */
public class QrLoginImgBean {

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data data;


    public class Data{
        String qrurl;
        String qrimg;

        public String getQrurl() {
            return qrurl;
        }

        public void setQrurl(String qrurl) {
            this.qrurl = qrurl;
        }

        public String getQrimg() {
            return qrimg;
        }

        public void setQrimg(String qrimg) {
            this.qrimg = qrimg;
        }
    }
}
