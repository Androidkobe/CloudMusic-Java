package com.netease.lib_api.model.user;

import java.util.List;

/**
 * 通过login接口返回来的bean
 */
public class Login2Bean {
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
