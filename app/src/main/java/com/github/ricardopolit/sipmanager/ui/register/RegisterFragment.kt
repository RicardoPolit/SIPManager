package com.github.ricardopolit.sipmanager.ui.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.ricardopolit.sipmanager.R
import com.github.ricardopolit.sipmanager.databinding.RegisterFragmentBinding

class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding : RegisterFragmentBinding = DataBindingUtil.inflate(
        inflater, R.layout.register_fragment, container, false)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.registerViewModel = viewModel
        binding.lifecycleOwner = this
        setTextWatchers(binding)

        viewModel.navigateToLogin.observe( viewLifecycleOwner, Observer {
            if(it){
                this.findNavController().navigate(
                    RegisterFragmentDirections
                        .actionRegisterFragmentToLoginFragment())
                viewModel.doneNavigating()
            }
        } )

        return binding.root
    }

    private fun setTextWatchers( binding: RegisterFragmentBinding ){
        binding.registerPassword.addTextChangedListener( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val registerPasswordEmpty = p0?.isNotEmpty() ?: false
                binding.button.isEnabled = registerPasswordEmpty &&
                        binding.editTextRecoverQuestion.text?.isNotEmpty() ?: false &&
                        binding.editTextRecoverAnswer.text?.isNotEmpty() ?: false
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.editTextRecoverQuestion.addTextChangedListener( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val recoverQuestionEmpty = p0?.isNotEmpty() ?: false
                binding.button.isEnabled = recoverQuestionEmpty &&
                        binding.registerPassword.text?.isNotEmpty() ?: false &&
                        binding.editTextRecoverAnswer.text?.isNotEmpty() ?: false
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        binding.editTextRecoverAnswer.addTextChangedListener( object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val recoverAnswerEmpty = p0?.isNotEmpty() ?: false
                binding.button.isEnabled = recoverAnswerEmpty &&
                        binding.registerPassword.text?.isNotEmpty() ?: false &&
                        binding.editTextRecoverQuestion.text?.isNotEmpty() ?: false
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

}