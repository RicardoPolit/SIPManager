 package com.github.ricardopolit.sipmanager.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.ricardopolit.sipmanager.R
import com.github.ricardopolit.sipmanager.databinding.LoginFragmentBinding
import com.github.ricardopolit.sipmanager.util.Preference
import com.google.android.material.snackbar.Snackbar

 private const val SHARED_PREFERENCE_DBEXISTS = "DB_EXISTS"

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: LoginFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.login_fragment, container, false)

        if( !Preference.getPreferenceFlag(
                requireActivity().applicationContext,
                SHARED_PREFERENCE_DBEXISTS ) ){

            Log.d( "LoginFragment", "DataBase not created" )
            this.findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        binding.loginPassword.addTextChangedListener( object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.buttonLogin.isEnabled = p0?.isNotEmpty() ?: false
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        viewModel.navigateToInvestment.observe( viewLifecycleOwner, Observer {
            if(it){
                this.findNavController().navigate(
                    LoginFragmentDirections
                        .actionLoginFragmentToNavGraphMain())
                viewModel.doneNavigating()
            }
        } )

        viewModel.showSnackBarEventPasswordIncorrect.observe( viewLifecycleOwner, Observer {
            if(it){
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.incorrect_password),
                    Snackbar.LENGTH_LONG
                ).show()
                viewModel.doneShowingSnackBarEventPasswordIncorrect()
            }
        })

        return binding.root
    }

}