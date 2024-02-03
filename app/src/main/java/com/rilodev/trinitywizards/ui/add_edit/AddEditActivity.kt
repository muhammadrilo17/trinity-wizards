package com.rilodev.trinitywizards.ui.add_edit

import android.app.DatePickerDialog
import android.os.Build
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.rilodev.core.domain.model.PersonModel
import com.rilodev.trinitywizards.databinding.ActivityAddEditBinding
import com.rilodev.trinitywizards.utils.Constants
import com.rilodev.trinitywizards.utils.Enum
import com.rilodev.trinitywizards.utils.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class AddEditActivity : BaseActivity<ActivityAddEditBinding, ViewModel>(ViewModel::class) {
    override val bindingInflater: (LayoutInflater) -> ActivityAddEditBinding
        get() = ActivityAddEditBinding::inflate
    private var type = ""
    private var editPersonData: PersonModel? = null

    override fun initialActivity() {
        super.initialActivity()

        type = intent.getStringExtra(Constants.TYPE) ?: ""
    }

    override fun ActivityAddEditBinding.initObserve() {

    }

    override fun ActivityAddEditBinding.initEvent() {
        cancelButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        saveButton.setOnClickListener {
            if(validateForm()) {
                Toast.makeText(this@AddEditActivity, "Data saved!", Toast.LENGTH_SHORT).show()
                onBackPressedDispatcher.onBackPressed()
            }

        }

        inputDob.setOnClickListener {
            showDatePicker()
        }
    }

    override fun ActivityAddEditBinding.initUI() {
        if(type == Enum.AddEditType.EDIT.displayName) {
            editPersonData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(Constants.DATA, PersonModel::class.java)
            } else {
                intent.getParcelableExtra(Constants.DATA)
            }

            if(editPersonData != null) {
                inputFirstName.setText(editPersonData?.firstName ?: "")
                inputLastName.setText(editPersonData?.lastName ?: "")
                inputEmail.setText(editPersonData?.email ?: "")
                inputDob.text = editPersonData?.dob ?: ""
            }
        }
    }

    private fun ActivityAddEditBinding.validateForm(): Boolean {
        val firstName = inputFirstName.text.toString().trim()
        val lastName = inputLastName.text.toString().trim()
        if(firstName.isEmpty()) {
            inputFirstName.error = "Should be filled"
        } else if(lastName.isEmpty()) {
            inputLastName.error = "Should be filled"
        } else {
            return true
        }

        return  false
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.inputDob.text = selectedDate
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }
}