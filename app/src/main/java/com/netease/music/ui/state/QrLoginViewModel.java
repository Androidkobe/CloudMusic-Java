package com.netease.music.ui.state;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.kunminx.architecture.utils.Utils;
import com.netease.lib_api.model.user.LoginBean;
import com.netease.lib_api.model.user.QrLoginImgBean;
import com.netease.lib_common_ui.utils.SharePreferenceUtil;
import com.netease.music.MainActivity;
import com.netease.music.domain.request.QrLoginRequest;
import com.netease.music.ui.page.login.PhoneLoginActivity;

public class QrLoginViewModel extends ViewModel {

    //base64 二维码数据
    public final ObservableField<String> mQrStringData = new ObservableField<String>();

    //界面标题
    public final ObservableField<String> title = new ObservableField<String>();

    public final QrLoginRequest qrLoginRequest = new QrLoginRequest();

    {
        title.set("二维码登录");
    }

    public void qrLogin(AppCompatActivity owner){
        qrLoginRequest.requestLoginKey();
        qrLoginRequest.getMLoginImgBean().observe(owner, new Observer<QrLoginImgBean>() {
            @Override
            public void onChanged(QrLoginImgBean qrLoginImgBean) {
                mQrStringData.set(qrLoginImgBean.data.getQrimg());
            }
        });
        qrLoginRequest.getMLoginBean().observe(owner, new Observer<LoginBean>() {
            @Override
            public void onChanged(LoginBean loginBean) {
                //登陆成功
                SharePreferenceUtil.getInstance(Utils.getApp()).saveUserInfo(loginBean);
                owner.startActivity(new Intent(owner, MainActivity.class));
                owner.finish();
            }
        });
    }
}
