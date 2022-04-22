package me.thekusch.ozancaseproject.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import me.thekusch.ozancaseproject.core.BaseFragment
import me.thekusch.ozancaseproject.databinding.FragmentDetailPageBinding

class DetailFragment: BaseFragment<FragmentDetailPageBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentDetailPageBinding? {
        return FragmentDetailPageBinding.inflate(inflater, container, attachToRoot)
    }

    companion object {
        fun newInstance() = DetailFragment()
    }
}