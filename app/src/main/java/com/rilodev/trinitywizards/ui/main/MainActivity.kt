package com.rilodev.trinitywizards.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.rilodev.core.data.repositories.Resource
import com.rilodev.trinitywizards.databinding.ActivityMainBinding
import com.rilodev.trinitywizards.ui.add_edit.AddEditActivity
import com.rilodev.trinitywizards.utils.Constants
import com.rilodev.trinitywizards.utils.Enum
import com.rilodev.trinitywizards.utils.adapter.PersonRvAdapter
import com.rilodev.trinitywizards.utils.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class) {
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate
    private val adapter = PersonRvAdapter()

    override fun ActivityMainBinding.initObserve() {
        lifecycleScope.launch {
            viewModel.getPersons.collect {
                when(it) {
                    is Resource.Loading -> showLoading(false)
                    is Resource.Success -> {
                        dismissLoading()

                        adapter.submitList(it.data)
                    }
                    is Resource.Empty -> {
                        dismissLoading()
                        confirmDialog(
                            "There is no data, reload?",
                            {
                                viewModel.getPersons()
                            },
                            cancelable = false
                        )
                    }
                    is Resource.Error -> {
                        dismissLoading()
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
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
        btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditActivity::class.java)
            intent.putExtra(Constants.TYPE, Enum.AddEditType.ADD.displayName)
            startActivity(intent)
        }

        adapter.onItemClick = {
            val intent = Intent(this@MainActivity, AddEditActivity::class.java)
            intent.putExtra(Constants.TYPE, Enum.AddEditType.EDIT.displayName)
            intent.putExtra(Constants.DATA, it)
            startActivity(intent)
        }

        swipeRefresh.setOnRefreshListener {
            fetchData()
        }
    }

    private fun fetchData() {
        viewModel.getPersons()

        binding.swipeRefresh.postDelayed({
            binding.swipeRefresh.isRefreshing = false
        }, 2000)
    }

    override fun ActivityMainBinding.initUI() {
        viewModel.getPersons()

        rvPersons.adapter = adapter

        val layoutManager = GridLayoutManager(this@MainActivity, 2)
        rvPersons.layoutManager = layoutManager
    }
}