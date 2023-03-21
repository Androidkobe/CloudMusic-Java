package com.netease.lib_api.model.user;

/**
 * 通过login接口返回来的bean
 */
public class QrLoginCookie {

    public int code;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String cookie = "";


}
