package com.example.a.musicplayer.login

import com.example.a.musicplayer.Data.LoginBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val UI : LoginService.UI) :LoginService.Presenter{
    private val LOGIN_BY_EMAIL = 0
    private val LOGIN_BY_PHONE = 1
    private val model = LoginModel()
    override fun getData(acc:String,password:String,loginType : Int) {
        val call : Call<LoginBean> = if(loginType == LOGIN_BY_PHONE){
            model.loginByPhone(acc,password)
        }else{
            model.loginByEmail(acc,password)
        }
        call.enqueue(object :Callback<LoginBean>{
            override fun onResponse(call: Call<LoginBean>, response: Response<LoginBean>) {
                val bean = response.body()
                bean?.let {
                    if (it.code != 200) {
                        UI.loginFailure(it.msg)
                    } else {
                        UI.addData()
                    }
                }
            }

            override fun onFailure(call: Call<LoginBean>, t: Throwable) {
                UI.onError()
            }

        })
    }
}