package com.rilodev.trinitywizards.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import com.rilodev.trinitywizards.databinding.ConfirmationDialogBinding
import com.rilodev.trinitywizards.databinding.LoadingDialogBinding

object CustomDialog {
    fun loading(context: Context, cancelable: Boolean = true, loadingTitle: String? = null): Dialog {
        val dialog = Dialog(context)
        val binding = LoadingDialogBinding.inflate(LayoutInflater.from(context))
        if (!loadingTitle.isNullOrEmpty())
            binding.loadingTitle.text = loadingTitle
        dialog.setContentView(binding.root)
        dialog.setCancelable(cancelable)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    fun confirmation(
        context: Context,
        message: String,
        positiveAction: () -> Unit,
        negativeAction: () -> Unit,
        cancelable: Boolean = true
    ): Dialog {
        val dialog = Dialog(context)
        val binding = ConfirmationDialogBinding.inflate(LayoutInflater.from(context))

        binding.confirmationMessage.text = message

        binding.positiveButton.setOnClickListener {
            positiveAction.invoke()
            dialog.dismiss()
        }

        binding.negativeButton.setOnClickListener {
            negativeAction.invoke()
            dialog.dismiss()
        }

        binding.btnClose.setOnClickListener { dialog.dismiss() }

        dialog.setContentView(binding.root)
        dialog.setCancelable(cancelable)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    fun error(
        context: Context,
        message: String,
        positiveAction: () -> Unit,
        positiveActionText: String,
        cancelable: Boolean = true
    ): Dialog {
        val dialog = Dialog(context)
        val binding = ConfirmationDialogBinding.inflate(LayoutInflater.from(context))

        binding.confirmationMessage.text = message
        binding.positiveButton.text = positiveActionText
        binding.negativeButton.visibility = View.INVISIBLE

        binding.positiveButton.setOnClickListener {
            positiveAction.invoke()
            dialog.dismiss()
        }

        binding.btnClose.setOnClickListener { dialog.dismiss() }

        dialog.setContentView(binding.root)
        dialog.setCancelable(cancelable)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}