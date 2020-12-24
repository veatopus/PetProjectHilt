package kg.geektech.ruslan.petprojecthilt.ui.pictures_info

import android.annotation.SuppressLint
import kg.geektech.ruslan.petprojecthilt.R
import kg.geektech.ruslan.petprojecthilt.core.BaseFragment
import kg.geektech.ruslan.petprojecthilt.core.gone
import kg.geektech.ruslan.petprojecthilt.core.visible
import kg.geektech.ruslan.petprojecthilt.databinding.FragmentPicturesInfoBinding
import kg.geektech.ruslan.petprojecthilt.databinding.PicturesFragmentBinding
import kg.geektech.ruslan.petprojecthilt.ui.pictures.PicturesViewModel
import kotlinx.coroutines.newFixedThreadPoolContext
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject


class PicturesInfoFragment : BaseFragment<PicturesInfoViewModel, FragmentPicturesInfoBinding>(R.layout.fragment_pictures_info) {

    override fun getViewModule(): PicturesInfoViewModel = inject<PicturesInfoViewModel>().value

    override fun getViewBinding(): FragmentPicturesInfoBinding? =
        rootView?.let { FragmentPicturesInfoBinding.bind(it) }

    override fun progress(isProgress: Boolean) {
        if (isProgress) binding?.progressBar?.visible()
        else binding?.progressBar?.gone()
    }


    @SuppressLint("SetTextI18n")
    override fun setUpView() {
        binding?.textView?.text = "information"
    }

    override fun setUpViewModelObs() {}

}
