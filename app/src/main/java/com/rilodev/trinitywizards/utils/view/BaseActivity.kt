package com.rilodev.trinitywizards.utils.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.rilodev.trinitywizards.utils.CustomDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

abstract class BaseActivity<VB: ViewBinding, VM : ViewModel>(
    private val viewModelClass: KClass<VM>
) : AppCompatActivity() {
    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> VB

    protected val viewModel by lazy { ViewModelProvider(this)[viewModelClass.java] }

    private var loadingDialog: Dialog? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB get() = _binding as VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(binding.root)

        initialActivity()

        with(binding) {
            initUI()
            initEvent()
            initObserve()
        }
    }

    open fun initialActivity() {}

    open fun onDestroyActivity() {}

    abstract fun VB.initUI()

    abstract fun VB.initEvent()

    abstract fun VB.initObserve()

    override fun onDestroy() {
        onDestroyActivity()
        _binding = null

        super.onDestroy()
    }

    protected fun showLoading(cancelable: Boolean = true, message: String? = null) {
        if (loadingDialog == null) {
            loadingDialog = CustomDialog.loading(this, cancelable, message)
            loadingDialog?.show()
        } else {
            if (loadingDialog?.isShowing != true)
                loadingDialog?.show()
        }
    }

    protected fun dismissLoading() {
        loadingDialog?.dismiss()
    }

    protected fun confirmDialog(
        message: String,
        positiveButton: (() -> Unit)? = null,
        negativeButton: (() -> Unit)? = null,
        cancelable: Boolean = true,
    ) {
        val dialog = CustomDialog.confirmation(
            this,
            message,
            {
                positiveButton?.invoke()
                dismissLoading()
            },
            {
                negativeButton?.invoke()
                dismissLoading()
            }
        )

        dialog.setCancelable(cancelable)

        dialog.show()
    }

    protected fun errorDialog(
        message: String,
        positiveButton: (() -> Unit)? = null,
        positiveButtonText: String,
        cancelable: Boolean = true,
    ) {
        val dialog = CustomDialog.error(
            this,
            message,
            {
                positiveButton?.invoke()
            },
            positiveButtonText,
        )

        dialog.setCancelable(cancelable)

        dialog.show()
    }
}