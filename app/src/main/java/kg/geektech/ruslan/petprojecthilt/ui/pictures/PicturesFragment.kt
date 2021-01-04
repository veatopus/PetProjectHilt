package kg.geektech.ruslan.petprojecthilt.ui.pictures

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

import kg.geektech.ruslan.petprojecthilt.R
import kg.geektech.ruslan.petprojecthilt.core.*
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
       binding?.progressBar?.isVisible(isProgress)
    }

    override fun setUpView() {
        setUpRecyclerView()
    }

    //не нужно разделять это на методы..
    private fun setUpRecyclerView() {
        val snapHelper: SnapHelper = LinearSnapHelper()
        adapter = PicturesAdapter()
        binding?.recyclerView.apply {
            snapHelper.attachToRecyclerView(this)
            this?.adapter = adapter
        }
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
