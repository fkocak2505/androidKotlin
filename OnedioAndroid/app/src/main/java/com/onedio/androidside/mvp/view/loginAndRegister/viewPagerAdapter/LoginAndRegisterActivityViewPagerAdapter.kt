package com.onedio.androidside.mvp.view.loginAndRegister.viewPagerAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.onedio.androidside.mvp.view.loginAndRegister.login.LoginFragmentActivityViewImpl
import com.onedio.androidside.mvp.view.loginAndRegister.register.RegisterFragmentAcitivityViewImpl

class LoginAndRegisterActivityViewPagerAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    //==================================================================================================================
    /**
     * Get Item
     */
    //==================================================================================================================
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                LoginFragmentActivityViewImpl()
            }
            else -> RegisterFragmentAcitivityViewImpl()
        }
    }

    //==================================================================================================================
    /**
     * get Count
     */
    //==================================================================================================================
    override fun getCount(): Int {
        return 2
    }

    //==================================================================================================================
    /**
     * get Page Title
     */
    //==================================================================================================================
    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Giriş Yap"
            else -> "Kayıt Ol"
        }
    }
}