 package com.github.ricardopolit.sipmanager.ui.login

import android.content.Intent
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
import com.github.ricardopolit.sipmanager.MainActivity
import com.github.ricardopolit.sipmanager.R
import com.github.ricardopolit.sipmanager.databinding.LoginFragmentBinding
import com.github.ricardopolit.sipmanager.util.Preference
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment() {

    private val SHARED_PREFERENCE_DBEXISTS = "DB_EXISTS"
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

        viewModel.navigateToInvestment.observe( viewLifecycleOwner, Observer { password ->
            password?.let{
//                this.findNavController().navigate(
//                    LoginFragmentDirections
//                        .actionLoginFragmentToNavGraphMain())
                val intent = Intent(activity,MainActivity::class.java)
                intent.putExtra("com.github.ricardopolit.sipmanager.P455W0RD_U53R",password)
                startActivity(intent)
                viewModel.doneNavigating()
                activity?.finish()
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