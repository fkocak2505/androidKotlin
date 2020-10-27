package com.androidlab.jetpackarchitecture.mvvm.view.splash

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androidlab.jetpackarchitecture.BR
import com.androidlab.jetpackarchitecture.R
import com.androidlab.jetpackarchitecture.databinding.ActivitySplashBinding
import com.androidlab.jetpackarchitecture.mvvm.viewModel.splash.SplashViewModel
import com.androidlab.jetpackarchitecture.mvvm.view.base.BaseActivity

class SplashActivityImpl : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    private lateinit var viewModel: SplashViewModel

    override fun getBindingVariable(): Int {
        return BR.splashViewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModel(): SplashViewModel {
        viewModel = ViewModelProvider(this).get<SplashViewModel>(
            SplashViewModel::class.java
        )
        return viewModel
    }

    override fun initUiAndData() {
        viewModel.getTokenModel().observe(this, Observer { tokenModel ->
            tokenModel?.let {
                viewDataBinding!!.textToken.text = tokenModel.token
            }
        })

        viewModel.fetchAppToken()
    }
}
