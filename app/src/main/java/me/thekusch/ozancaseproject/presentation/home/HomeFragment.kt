package me.thekusch.ozancaseproject.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import me.thekusch.ozancaseproject.core.BaseFragment
import me.thekusch.ozancaseproject.databinding.FragmentHomeBinding
import me.thekusch.ozancaseproject.presentation.ext.observeData
import me.thekusch.ozancaseproject.presentation.home.components.CoinList
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModel<HomeViewModel>()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentHomeBinding? {
        return FragmentHomeBinding.inflate(inflater,container,attachToRoot)
    }

    override fun fetch() {
        super.fetch()
        viewModel.getCoinList()
    }

    override fun observe() {
        super.observe()
        viewModel.getCoinsLiveData.observeData(
            lifecycleOwner = viewLifecycleOwner,
            success = {
                binding.coinList.setup(it?.data)
            },
            fail = {

            }
        )
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}