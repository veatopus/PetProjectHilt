package kg.geektech.ruslan.petprojecthilt.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    val toast = MutableLiveData<String>()
    val isFinished = MutableLiveData<Boolean>(false)
    val isLoading = MutableLiveData<Boolean>(false)

}