package com.netease.music.domain.request

import androidx.lifecycle.MutableLiveData
import com.kunminx.architecture.domain.request.BaseRequest
import com.netease.lib_api.model.user.LoginBean
import com.netease.lib_api.model.user.QrLoginCookie
import com.netease.lib_api.model.user.QrLoginImgBean
import com.netease.lib_api.model.user.QrLoginKeyBean
import com.netease.lib_common_ui.utils.GsonUtil
import com.netease.lib_network.ApiEngine
import com.netease.lib_network.ExceptionHandle.ResponseThrowable
import com.netease.lib_network.SimpleObserver
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class QrLoginRequest :BaseRequest(){

    var re : Boolean = true

    var mLoginKeyBean = MutableLiveData<QrLoginKeyBean> ()

    var mLoginImgBean = MutableLiveData<QrLoginImgBean> ()

    var mLoginBean = MutableLiveData<LoginBean>()

    fun requestLoginKey() {
        ApiEngine.getInstance().apiService.createQrKey()
            .compose<QrLoginKeyBean>(ApiEngine.getInstance().applySchedulers<QrLoginKeyBean>())
            .subscribe(object : SimpleObserver<QrLoginKeyBean>() {
                override fun onFailed(errorMsg: ResponseThrowable) {}
                override fun onSuccess(loginKeyBean: QrLoginKeyBean) {
                   requestLoginImg(loginKeyBean.data.unikey)
                }
            })
    }

    fun requestLoginImg(key:String) {
        ApiEngine.getInstance().apiService.createQrImg(key,true,System.currentTimeMillis())
            .compose<QrLoginImgBean>(ApiEngine.getInstance().applySchedulers<QrLoginImgBean>())
            .subscribe(object : SimpleObserver<QrLoginImgBean>() {
                override fun onFailed(errorMsg: ResponseThrowable) {}
                override fun onSuccess(loginImgBean: QrLoginImgBean) {
                    mLoginImgBean.value = loginImgBean
                    val observable = Observable.create<Int> {
                        while (re){
                            checkLogin(key)
                            Thread.sleep(1000)
                        }
                        it.onComplete()
                    }

                    val observer: Observer<Int> = object : Observer<Int> {
                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onNext(value: Int) {
                        }
                        override fun onError(e: Throwable) {
                        }
                        override fun onComplete() {
                        }
                    }
                    observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer)

                }
            })
    }

    fun checkLogin(key: String){
        ApiEngine.getInstance().apiService.createQrImgCheck(key)
            .compose<QrLoginCookie>(ApiEngine.getInstance().applySchedulers<QrLoginCookie>())
            .subscribe(object : SimpleObserver<QrLoginCookie>() {
                override fun onFailed(errorMsg: ResponseThrowable) {}
                override fun onSuccess(loginCookie: QrLoginCookie) {
                    val d = "{\"data\":{\"code\":200,\"account\":{\"id\":8453940274,\"userName\":\"1_********407\",\"type\":1,\"status\":0,\"whitelistAuthority\":0,\"createTime\":1678967017508,\"tokenVersion\":1,\"ban\":0,\"baoyueVersion\":0,\"donateVersion\":0,\"vipType\":11,\"anonimousUser\":false,\"paidFee\":false},\"profile\":{\"userId\":8453940274,\"userType\":0,\"nickname\":\"16村村民16\",\"avatarImgId\":109951165647004060,\"avatarUrl\":\"http://p2.music.126.net/SUeqMM8HOIpHv9Nhl9qt9w==/109951165647004069.jpg\",\"backgroundImgId\":109951162868128400,\"backgroundUrl\":\"http://p1.music.126.net/2zSNIqTcpHL2jIvU6hG0EA==/109951162868128395.jpg\",\"signature\":null,\"createTime\":1678967017776,\"userName\":\"1_********407\",\"accountType\":1,\"shortUserName\":\"********407\",\"birthday\":-2209017600000,\"authority\":0,\"gender\":0,\"accountStatus\":0,\"province\":110000,\"city\":110101,\"authStatus\":0,\"description\":null,\"detailDescription\":null,\"defaultAvatar\":false,\"expertTags\":null,\"experts\":null,\"djStatus\":0,\"locationStatus\":10,\"vipType\":11,\"followed\":false,\"mutual\":false,\"authenticated\":false,\"lastLoginTime\":1679425406817,\"lastLoginIP\":\"192.168.31.238\",\"remarkName\":null,\"viptypeVersion\":1678967025144,\"authenticationTypes\":0,\"avatarDetail\":null,\"anchor\":false}}}"
                    val loginBean = GsonUtil.fromJSON(d,LoginBean::class.java)
                    mLoginBean.postValue(loginBean)
                    re = false
//                    when(loginCookie.code){
//                        803->{
//                            re = false
//                            login(loginCookie.cookie)
//                        }
//                    }
                }
            })
    }

    fun login(cookie: String){
        ApiEngine.getInstance().apiService.qrLogin(cookie,System.currentTimeMillis())
            .compose<LoginBean>(ApiEngine.getInstance().applySchedulers<LoginBean>())
            .subscribe(object : SimpleObserver<LoginBean>() {
                override fun onFailed(errorMsg: ResponseThrowable) {}
                override fun onSuccess(loginBean: LoginBean) {
                    val d = "{\"data\":{\"code\":200,\"account\":{\"id\":8453940274,\"userName\":\"1_********407\",\"type\":1,\"status\":0,\"whitelistAuthority\":0,\"createTime\":1678967017508,\"tokenVersion\":1,\"ban\":0,\"baoyueVersion\":0,\"donateVersion\":0,\"vipType\":11,\"anonimousUser\":false,\"paidFee\":false},\"profile\":{\"userId\":8453940274,\"userType\":0,\"nickname\":\"16村村民16\",\"avatarImgId\":109951165647004060,\"avatarUrl\":\"http://p2.music.126.net/SUeqMM8HOIpHv9Nhl9qt9w==/109951165647004069.jpg\",\"backgroundImgId\":109951162868128400,\"backgroundUrl\":\"http://p1.music.126.net/2zSNIqTcpHL2jIvU6hG0EA==/109951162868128395.jpg\",\"signature\":null,\"createTime\":1678967017776,\"userName\":\"1_********407\",\"accountType\":1,\"shortUserName\":\"********407\",\"birthday\":-2209017600000,\"authority\":0,\"gender\":0,\"accountStatus\":0,\"province\":110000,\"city\":110101,\"authStatus\":0,\"description\":null,\"detailDescription\":null,\"defaultAvatar\":false,\"expertTags\":null,\"experts\":null,\"djStatus\":0,\"locationStatus\":10,\"vipType\":11,\"followed\":false,\"mutual\":false,\"authenticated\":false,\"lastLoginTime\":1679425406817,\"lastLoginIP\":\"192.168.31.238\",\"remarkName\":null,\"viptypeVersion\":1678967025144,\"authenticationTypes\":0,\"avatarDetail\":null,\"anchor\":false}}}"
                    val loginBean = GsonUtil.fromJSON(d,LoginBean::class.java)
                    mLoginBean.postValue(loginBean)
                }
            })
    }
}