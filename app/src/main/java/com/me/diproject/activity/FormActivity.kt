package com.me.diproject.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.me.base.BaseActivity
import com.me.diproject.R
import com.me.diproject.databinding.ActivityFormBinding
import com.me.diproject.vm.FormViewModel
import kotlinx.coroutines.launch

class FormActivity : BaseActivity<ActivityFormBinding,FormViewModel>() {

    override val viewModel by lazy { FormViewModel() }

    companion object {
        @JvmStatic
        fun startActivity(context: Context?) {
            val intent = Intent(context, FormActivity::class.java)
            context?.startActivity(intent)
        }
    }


    override fun createBinding(inflater: LayoutInflater) = ActivityFormBinding.inflate(layoutInflater)

    override fun initView() {
    }

    override fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){

                // 观察表单有效性
                launch {
                    viewModel.isValid.collect { valid ->
//                        binding.loginButton.isEnabled = valid
                    }
                }

                // 观察用户名（如果需要回显）
                launch {
                    viewModel.account.collect {
                        binding.accountEt.setText(it)
                    }
                }
            }
        }

        // 输入监听
        binding.accountEt.doAfterTextChanged {
            viewModel.onUsernameChanged(it.toString())
        }

        binding.pwdEt.doAfterTextChanged {
            viewModel.onPasswordChanged(it.toString())
        }
    }
}

