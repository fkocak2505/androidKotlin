package com.onedio.androidside.mvp.view.notDesigned.doBlockUser

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user_do_block.*
import com.onedio.androidside.R
import com.onedio.androidside.mvp.model.noDesigned.doBlockUser.UserDoBlockActivityModelImpl
import com.onedio.androidside.mvp.model.noDesigned.doBlockUser.responseModel.Response4DoBlockedUser
import com.onedio.androidside.mvp.presenter.notDesigned.doBlockUser.UserDoBlockActivityPresenterImpl
import com.onedio.androidside.mvp.view.dashboard.DashboardActivityViewImpl

class UserDoBlockActivityViewImpl : AppCompatActivity(),
    IUserDoBlockActivityView {

    private lateinit var userDoBlockActivityPresenterImpl: UserDoBlockActivityPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_do_block)

        initUserDoBlockActivityComponent()

        clickDoBlockUser()

    }

    override fun initUserDoBlockActivityComponent() {
        supportActionBar?.hide()

        userDoBlockActivityPresenterImpl =
            UserDoBlockActivityPresenterImpl(
                UserDoBlockActivityModelImpl(),
                this
            )


    }

    override fun clickDoBlockUser() {
        doBlockUser.setOnClickListener() {
            doBlockUser()
        }
    }

    override fun doBlockUser() {
        userDoBlockActivityPresenterImpl.doBlockUser(edtOfUserName.text.toString().trim())
    }

    override fun handleDoUserBlockedInfoData(response4DoBlockedUser: Response4DoBlockedUser) {
        if (response4DoBlockedUser.status?.code == 200) {
            if (response4DoBlockedUser.isBlocked!!) {
                Toast.makeText(
                    applicationContext,
                    "Başarıyla Engellendi",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(Intent(applicationContext, DashboardActivityViewImpl::class.java))
            } else
                Toast.makeText(
                    applicationContext,
                    "Engelleme sırasında bir sorun oluştur.",
                    Toast.LENGTH_SHORT
                ).show()
        } else
            Toast.makeText(
                applicationContext,
                "Şu an da bir sorun var. Daha sonra tekrar deneyiniz.",
                Toast.LENGTH_SHORT
            ).show()
    }

    override fun showLoading() {
        avlIndicatorProgressDoBlockUser.playAnimation()
        avlIndicatorProgressDoBlockUser.visibility = View.VISIBLE
        /*window?.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )*/
    }

    override fun hideLoading() {
        avlIndicatorProgressDoBlockUser.cancelAnimation()
        avlIndicatorProgressDoBlockUser.visibility = View.GONE
        //window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
}