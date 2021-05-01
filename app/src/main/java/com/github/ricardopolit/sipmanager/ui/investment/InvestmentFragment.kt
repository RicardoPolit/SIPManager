package com.github.ricardopolit.sipmanager.ui.investment

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.github.ricardopolit.sipmanager.MainActivity
import com.github.ricardopolit.sipmanager.R
import com.github.ricardopolit.sipmanager.data.Portfolio
import com.github.ricardopolit.sipmanager.data.SIPManagerDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InvestmentFragment : Fragment() {

    private lateinit var viewModel: InvestmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val password = (activity as MainActivity).getUserPassword()
        Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                "The user password is $password",
                Snackbar.LENGTH_LONG
        ).show()

//        val portfolio = Portfolio(
//                name = "primer portafolio",
//                goal = "probar la base de datos",
//                dateFinish = System.currentTimeMillis()+1000,
//                color = Color.BLACK.toString()
//        )
//
//        viewLifecycleOwner.lifecycleScope.launch{
//            insert(portfolio,password)
//        }

        return inflater.inflate(R.layout.investment_fragment, container, false)
    }

//    private suspend fun insert(portfolio: Portfolio, password: String){
//        withContext(Dispatchers.IO){
//            SIPManagerDatabase.getInstance(requireContext(),password).portfolioDAO.insert(portfolio)
//        }
//    }

}