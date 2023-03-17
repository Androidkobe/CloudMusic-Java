package com.netease.music.ui.page.login

import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.netease.lib_common_ui.widget.CaptchaView.OnInputListener
import com.netease.music.BR
import com.netease.music.R
import com.netease.music.ui.state.PhoneLoginViewModel

class QrLoginActivity : BaseActivity() {

    private lateinit var mPhoneLoginViewModel: PhoneLoginViewModel

    //验证码输入完成后的回调
    var listener =
        OnInputListener { code -> //注册(更改密码)
            mPhoneLoginViewModel!!.loadingVisible.set(true)
            mPhoneLoginViewModel.accountRequest.register(
                mPhoneLoginViewModel.phone.get(),
                mPhoneLoginViewModel.password.get(),
                code
            )
        }

    override fun initViewModel() {
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.delegate_phone_login, BR.vm, mPhoneLoginViewModel)
            .addBindingParam(BR.captchaViewInputListener, listener)
    }
}