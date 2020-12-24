package kg.geektech.ruslan.petprojecthilt.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<V : BaseViewModel, B : ViewBinding>(private val layoutId: Int) :
    Fragment() {

    var mViewModule: V? = null
    var binding: B? = null

    private var hasInitializedRootView = false
    var rootView: View? = null

    abstract fun getViewModule(): V
    abstract fun getViewBinding(): B?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return getPersistentView(inflater, container)
    }

    private fun getPersistentView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(layoutId, container, false)
        } else {
            (rootView?.parent as? ViewGroup)?.removeView(rootView)
        }
        binding = getViewBinding()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!hasInitializedRootView) {
            hasInitializedRootView = true
            mViewModule = getViewModule()
            setUpViewModelObs()
            setUpObs()
            setUpView()
        }
    }

    private fun setUpObs() {
        mViewModule?.let { viewModel ->
            viewModel.isFinished.observe(requireActivity(), Observer { isFinish(it) })
            viewModel.isLoading.observe(requireActivity(), Observer { progress(it) })
            viewModel.toast.observe(requireActivity(), Observer { requireContext().showToast(it) })
        }
    }

    abstract fun progress(isProgress: Boolean)

    private fun isFinish(isFinish: Boolean) {
        if (isFinish) findNavController().navigateUp()
    }

    abstract fun setUpView()

    abstract fun setUpViewModelObs()
}