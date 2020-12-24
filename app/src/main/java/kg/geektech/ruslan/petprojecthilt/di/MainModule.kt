package kg.geektech.ruslan.petprojecthilt.di

import kg.geektech.ruslan.petprojecthilt.core.SavedImageToGalleryService
import kg.geektech.ruslan.petprojecthilt.data.network.RetrofitClient
import kg.geektech.ruslan.petprojecthilt.data.repositorty.PicturesRepository
import kg.geektech.ruslan.petprojecthilt.ui.pictures.PicturesViewModel
import kg.geektech.ruslan.petprojecthilt.ui.pictures_info.PicturesInfoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelModule = module {
    viewModel { PicturesViewModel(get(), get()) }
    viewModel { PicturesInfoViewModel() }
}

var networkModule = module {
    factory { RetrofitClient().instanceRetrofit() }
}

var serviceModule = module {
    factory { SavedImageToGalleryService(androidContext()) }
}

var repositoryModule = module {
    factory { PicturesRepository(get()) }
}