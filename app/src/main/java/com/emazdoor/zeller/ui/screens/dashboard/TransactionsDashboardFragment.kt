package com.emazdoor.zeller.ui.screens.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.emazdoor.zeller.R
import com.emazdoor.zeller.databinding.FragmentMainBinding
import java.util.*

class TransactionsDashboardFragment : Fragment(), View.OnClickListener {

    private lateinit var transactionsDashboardViewModel : TransactionsDashboardViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        transactionsDashboardViewModel = ViewModelProvider(this).get(TransactionsDashboardViewModel::class.java)
        binding =  FragmentMainBinding.inflate(inflater).apply {
            lifecycleOwner = this@TransactionsDashboardFragment
            viewModel = transactionsDashboardViewModel
        }
        
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.apply{
            btnDeposit.setOnClickListener(this@TransactionsDashboardFragment)
            btnWithdraw.setOnClickListener(this@TransactionsDashboardFragment)
        }

        transactionsDashboardViewModel.account.observe( viewLifecycleOwner, Observer {
          println(it.balance)
        })
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnDeposit -> {
                transactionsDashboardViewModel.depositMoney(10, "Saving")
            }
            R.id.btnWithdraw ->{
                transactionsDashboardViewModel.withdrawMoney(2, "Spending")
            }
        }
    }
}