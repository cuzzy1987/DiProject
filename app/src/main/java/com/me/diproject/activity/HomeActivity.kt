package com.me.diproject.activity

import android.view.LayoutInflater
import com.me.base.BaseActivity
import com.me.base.BaseViewModel
import androidx.activity.viewModels
import com.me.diproject.databinding.ActivityHomeBinding

class HomeActivity() : BaseActivity<ActivityHomeBinding,BaseViewModel>() {

    // 实现抽象属性
    override val viewModel: BaseViewModel by viewModels()


    override fun createBinding(inflater: LayoutInflater)
    = ActivityHomeBinding.inflate(inflater)

    override fun initView() {
    }

    override fun initObserver() {
    }


}