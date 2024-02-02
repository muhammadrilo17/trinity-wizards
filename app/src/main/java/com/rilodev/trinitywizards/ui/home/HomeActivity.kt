package com.rilodev.trinitywizards.ui.home

import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import com.rilodev.trinitywizards.databinding.ActivityHomeBinding
import com.rilodev.trinitywizards.utils.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, ViewModel>(ViewModel::class) {
    override val bindingInflater: (LayoutInflater) -> ActivityHomeBinding
        get() = ActivityHomeBinding::inflate

    override fun ActivityHomeBinding.initObserve() {

    }

    override fun ActivityHomeBinding.initEvent() {

    }

    override fun ActivityHomeBinding.initUI() {

    }
}