package com.github.ricardopolit.sipmanager.ui.register

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.ricardopolit.sipmanager.R

class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        this.findNavController().navigate(
//            RegisterFragmentDirections.actionRegisterFragment2ToNavGraph()
//        )

        return inflater.inflate(R.layout.register_fragment, container, false)
    }

}