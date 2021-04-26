package com.github.ricardopolit.sipmanager.ui.investment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ricardopolit.sipmanager.R

class InvestmentFragment : Fragment() {

    companion object {
        fun newInstance() = InvestmentFragment()
    }

    private lateinit var viewModel: InvestmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.investment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InvestmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}