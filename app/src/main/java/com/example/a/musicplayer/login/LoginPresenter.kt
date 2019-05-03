package com.example.a.musicplayer.login

import android.util.Log
import com.example.a.musicplayer.login.Data.LoginBean
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(val UI : LoginService.UI) :LoginService.Presenter{
    private val LOGIN_BY_EMAIL = 0
    private val LOGIN_BY_PHONE = 1
    private val model = LoginModel()
    override fun getData(acc:Long,password:String,loginType : Int) {
        val call : Call<LoginBean> = if(loginType == LOGIN_BY_PHONE){
            model.loginByPhone(acc,password)
        }else{
            model.loginByEmail("$acc@163.com",password)
        }
        call.enqueue(object :Callback<LoginBean>{
            override fun onResponse(call: Call<LoginBean>, response: Response<LoginBean>) {
                val bean: LoginBean? = response.body()
                if(bean?.code == 200){
                    UI.startA(bean)
                }else{
                    UI.loginFailure("用户名或密码错误")
                }
            }

            override fun onFailure(call: Call<LoginBean>, t: Throwable) {
                Log.d("onFailure",t.message)
                UI.onError()
            }

        })
    }
}