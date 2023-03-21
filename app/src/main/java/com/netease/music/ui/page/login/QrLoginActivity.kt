package com.netease.music.ui.page.login

import android.os.Bundle
import com.kunminx.architecture.ui.page.BaseActivity
import com.kunminx.architecture.ui.page.DataBindingConfig
import com.netease.music.BR
import com.netease.music.R
import com.netease.music.ui.state.QrLoginViewModel

class QrLoginActivity : BaseActivity() {

    private var mQrLoginViewModel: QrLoginViewModel ? = null

    override fun initViewModel() {
        mQrLoginViewModel = getActivityScopeViewModel(QrLoginViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.delegate_er_login, BR.vm, mQrLoginViewModel!!).
        addBindingParam(BR.proxy,Proxy())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mQrLoginViewModel?.qrLogin(this)
    }

    inner class  Proxy{
        fun back(){
            finish()
        }
    }
}