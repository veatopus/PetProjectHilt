package kg.geektech.ruslan.petprojecthilt.data.network

import kg.geektech.ruslan.petprojecthilt.data.model.Pictures
import retrofit2.http.GET

interface PicturesApi {

    @GET("a_test_1/test_app.json")
    suspend fun fetchPictures() : MutableList<Pictures>?

}