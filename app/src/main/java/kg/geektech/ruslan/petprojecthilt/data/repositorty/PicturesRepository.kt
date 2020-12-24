package kg.geektech.ruslan.petprojecthilt.data.repositorty

import androidx.lifecycle.liveData
import kg.geektech.ruslan.petprojecthilt.data.network.PicturesApi
import kg.geektech.ruslan.petprojecthilt.data.network.Resource
import kotlinx.coroutines.Dispatchers

class PicturesRepository(private val api: PicturesApi) {

    fun fetchArticles() = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        try {
            emit(Resource.success(api.fetchPictures()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error"))
        }
    }
}