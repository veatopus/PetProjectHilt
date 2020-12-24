package kg.geektech.ruslan.petprojecthilt.ui.pictures

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

import kg.geektech.ruslan.petprojecthilt.R
import kg.geektech.ruslan.petprojecthilt.core.BaseAdapter
import kg.geektech.ruslan.petprojecthilt.core.BaseFragment
import kg.geektech.ruslan.petprojecthilt.core.gone
import kg.geektech.ruslan.petprojecthilt.core.visible
import kg.geektech.ruslan.petprojecthilt.data.model.Pictures
import kg.geektech.ruslan.petprojecthilt.databinding.PicturesFragmentBinding
import kg.geektech.ruslan.petprojecthilt.ui.pictures.adapter.PicturesAdapter

import org.koin.android.ext.android.inject


class PicturesFragment :
    BaseFragment<PicturesViewModel, PicturesFragmentBinding>(R.layout.pictures_fragment) {

    private var data: MutableList<Pictures>? = null
    private var adapter: PicturesAdapter? = null

    override fun getViewModule(): PicturesViewModel = inject<PicturesViewModel>().value

    override fun getViewBinding(): PicturesFragmentBinding? =
        rootView?.let { PicturesFragmentBinding.bind(it) }

    override fun progress(isProgress: Boolean) {
        if (isProgress) binding?.progressBar?.visible()
        else binding?.progressBar?.gone()
    }

    override fun setUpView() {
        setUpRecycler()
    }

    private fun setUpRecycler() {
        initAdapter()
        addSnapHelper()
        addPositionListener()
        addListener()
    }

    private fun addListener() {
        adapter?.listener = object : BaseAdapter.IBaseAdapterClickListener<Pictures> {
            override fun onClick(model: Pictures) {
                findNavController().navigate(R.id.action_picturesFragment_to_picturesInfoFragment)
            }
        }
    }

    private fun addPositionListener() {
        binding?.recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val linearLayoutManager: LinearLayoutManager? =
                    recyclerView.layoutManager as LinearLayoutManager?
                linearLayoutManager?.findFirstVisibleItemPosition()?.let {
                    mViewModule?.onPositionChanget(it)
                }
            }
        })
    }

    private fun addSnapHelper() {
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding?.recyclerView)
    }

    private fun initAdapter() {
        adapter = PicturesAdapter()
        binding?.recyclerView?.adapter = adapter
    }

    override fun setUpViewModelObs() {
        subscribePictures()
        subscribeCurrentTitle()
    }

    private fun subscribeCurrentTitle() {
        mViewModule?.currentTitle?.observe(requireActivity(), Observer {
            requireActivity().title = it
        })
    }

    private fun subscribePictures() {
        mViewModule?.picturesListData?.observe(requireActivity(), Observer {
            this.data = it
            val targetPosition = Int.MAX_VALUE / 2
            adapter?.addAllData(it)
            binding?.recyclerView?.scrollToPosition(targetPosition - (targetPosition % it.size))
        })
    }


}