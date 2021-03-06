package kg.geektech.ruslan.petprojecthilt.ui.pictures

import androidx.lifecycle.MutableLiveData
import kg.geektech.ruslan.petprojecthilt.core.BaseViewModel
import kg.geektech.ruslan.petprojecthilt.data.model.Pictures
import kg.geektech.ruslan.petprojecthilt.data.network.Status
import kg.geektech.ruslan.petprojecthilt.data.repositorty.PicturesRepository

class PicturesViewModel(
    private val repository: PicturesRepository
) : BaseViewModel() {

    val picturesListData = MutableLiveData<MutableList<Pictures>>()
    val currentTitle = MutableLiveData<String>()

    init {
        fetchPictures()
    }

    private fun fetchPictures() {
        repository.fetchArticles().observeForever { result ->
            if (result.status == Status.SUCCESS)
                result?.data?.let {
                    picturesListData.value = it
                }

            when (result.status) {
                Status.LOADING -> isLoading.value = true
                else -> isLoading.value = false
            }
        }
    }

    fun onPositionChanget(position: Int) {
        picturesListData.value?.let {
            currentTitle.value = picturesListData.value?.get(
                position % it.size
            )?.title.toString()
        }
    }

}
