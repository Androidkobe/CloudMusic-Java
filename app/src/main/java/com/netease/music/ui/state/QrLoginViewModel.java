package com.netease.music.ui.state;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.netease.music.R;
import com.netease.music.domain.request.AccountRequest;

public class QrLoginViewModel extends ViewModel {

//    //base64 二维码数据
//    public final ObservableField<String> mQrStringData = new ObservableField<String>();

    //界面标题
    public final ObservableField<String> title = new ObservableField<String>();

    {
//        mQrStringData.set("");
        title.set("二维码登录");
    }
}
