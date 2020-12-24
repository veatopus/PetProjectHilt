package kg.geektech.ruslan.petprojecthilt.ui.pictures_info

import androidx.lifecycle.Observer
import kg.geektech.ruslan.petprojecthilt.R
import kg.geektech.ruslan.petprojecthilt.core.BaseFragment
import kg.geektech.ruslan.petprojecthilt.core.gone
import kg.geektech.ruslan.petprojecthilt.core.visible
import kg.geektech.ruslan.petprojecthilt.databinding.FragmentPicturesInfoBinding
import org.koin.android.ext.android.inject

class PicturesInfoFragment : BaseFragment<PicturesInfoViewModel, FragmentPicturesInfoBinding>(R.layout.fragment_pictures_info) {

    override fun getViewModule(): PicturesInfoViewModel = inject<PicturesInfoViewModel>().value

    override fun getViewBinding(): FragmentPicturesInfoBinding? =
        rootView?.let { FragmentPicturesInfoBinding.bind(it) }

    override fun progress(isProgress: Boolean) {
        if (isProgress) binding?.progressBar?.visible()
        else binding?.progressBar?.gone()
    }

    override fun setUpView() {
        binding?.textView?.setOnClickListener { mViewModule?.finishClicked() }
    }

    override fun setUpViewModelObs() {
        mViewModule?.title?.observe(requireActivity(), Observer {
            binding?.textView?.text = it
        })
    }

}
