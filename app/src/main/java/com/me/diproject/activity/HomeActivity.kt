package com.me.diproject.activity

import android.view.LayoutInflater
import com.me.base.BaseActivity
import com.me.base.BaseViewModel
import androidx.activity.viewModels
import com.me.diproject.databinding.ActivityHomeBinding
import com.me.diproject.vm.FormViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding,BaseViewModel>() {

    // 实现抽象属性
    override val viewModel by lazy { FormViewModel() }


    override fun createBinding(inflater: LayoutInflater)
    = ActivityHomeBinding.inflate(inflater)

    override fun initView() {
        initClick()
    }

    private fun initClick() {
        binding.loginTv.setOnClickListener {
            FormActivity.startActivity(this)
        }
    }

    override fun initObserver() {
    }


}