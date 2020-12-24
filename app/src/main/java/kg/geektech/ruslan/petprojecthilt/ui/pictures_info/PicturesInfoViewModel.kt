package kg.geektech.ruslan.petprojecthilt.ui.pictures_info

import androidx.lifecycle.MutableLiveData
import kg.geektech.ruslan.petprojecthilt.core.BaseViewModel

class PicturesInfoViewModel : BaseViewModel() {

    val title =  MutableLiveData<String>()

    init {
        title.value = "surprise!"
    }

    fun finishClicked() {
        isFinished.value = true
    }

}
