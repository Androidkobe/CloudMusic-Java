package com.netease.lib_api.model.user;

/**
 * 通过login接口返回来的bean
 */
public class QrLoginKeyBean {

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data data;


    public class Data{
        String unikey;

        public String getUnikey() {
            return unikey;
        }

        public void setUnikey(String unikey) {
            this.unikey = unikey;
        }
    }
}
