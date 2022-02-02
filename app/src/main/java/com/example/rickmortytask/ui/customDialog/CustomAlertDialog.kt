package com.example.rickmortytask.ui.customDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.rickmortytask.databinding.CustomDialogBinding

class CustomAlertDialog : DialogFragment() {

    private lateinit var binding: CustomDialogBinding
    private lateinit var listener: CustomListener
    private var statusCheck: String? = null
    private var genderCheck: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CustomDialogBinding.inflate(inflater)
        return binding.root
    }

    fun setListener(listener: CustomListener) {
        this.listener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnYes.setOnClickListener {
            val selectStatus = binding.rgStatusGroup.checkedRadioButtonId
            val stat = binding.root.findViewById<RadioButton>(selectStatus)

            if (stat != null) {
                statusCheck = stat.text.toString()
            }
            val selectGen = binding.rgGenderGroup.checkedRadioButtonId
            val gender = binding.root.findViewById<RadioButton>(selectGen)

            if (gender != null) {
                genderCheck = gender.text.toString()
            }
            if (statusCheck != null && genderCheck != null) {
                listener.statusAndGender(statusCheck, genderCheck)
            } else if (genderCheck != null) {
                listener.gender(genderCheck)
            } else if (genderCheck != null) {
                listener.status(statusCheck)
            } else {
                Toast.makeText(context, "Choose anything", Toast.LENGTH_SHORT).show()
            }
            dismiss()
            binding.rgGenderGroup.clearCheck()
            binding.rgStatusGroup.clearCheck()

        }
        binding.btnNo.setOnClickListener {
            dismiss()
        }
    }
}

interface CustomListener {
    fun status(status: String?)
    fun gender(gender: String?)
    fun statusAndGender(status: String?, gender: String?)
}