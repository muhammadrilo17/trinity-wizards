package com.rilodev.trinitywizards.ui.main

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.rilodev.core.data.repositories.Resource
import com.rilodev.trinitywizards.databinding.ActivityMainBinding
import com.rilodev.trinitywizards.utils.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class) {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun ActivityMainBinding.initObserve() {
        lifecycleScope.launch {
            viewModel.dataResult.collect {
                when(it) {
                    is Resource.Loading -> showLoading(false)
                    is Resource.Success -> {
                        dismissLoading()

                        Toast.makeText(this@MainActivity, it.data?.size.toString(), Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Empty -> {
                        dismissLoading()
                        confirmDialog(
                            "There is no data, reload?",
                            {
                                viewModel.dataResult()
                            },
                            cancelable = false
                        )
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        errorDialog(
                            it.message.toString(),
                            { dismissLoading() },
                            "Reload",
                            false
                        )
                    }
                }
            }
        }
    }

    override fun ActivityMainBinding.initEvent() {

    }

    override fun ActivityMainBinding.initUI() {

    }
}