package com.emazdoor.zeller.ui.screens.dashboard

import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.emazdoor.zeller.model.BankModel
import java.util.*

class TransactionsDashboardViewModel : ViewModel() {


    private val _account = MutableLiveData<BankModel>()
    val account : LiveData<BankModel> = _account

    var isBalanceAvailable =  ObservableBoolean(false)

    init {
        _account.value = BankModel(0, "", Date())
    }

    fun getCurrentBalance(): LiveData<BankModel>{
        return account
    }

    fun depositMoney(amount : Int, description: String): Unit{
        isBalanceAvailable.set(true)
        val depositAmount = _account.value?.balance?.plus(amount)
        depositAmount?.let {
            _account.postValue(BankModel(depositAmount, description, Date()))
        }
    }

    fun withdrawMoney (amount: Int, description: String): Unit{
        val withdrawAmount = _account.value?.balance?.minus(amount)
        isBalanceAvailable.set( withdrawAmount != -1)
        withdrawAmount?.let {
            _account.postValue(BankModel(withdrawAmount, description, Date()))
        }
    }

}