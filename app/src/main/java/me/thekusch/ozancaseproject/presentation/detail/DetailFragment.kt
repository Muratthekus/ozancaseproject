package me.thekusch.ozancaseproject.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import me.thekusch.ozancaseproject.core.BaseFragment
import me.thekusch.ozancaseproject.databinding.FragmentDetailPageBinding
import me.thekusch.ozancaseproject.presentation.ext.observeData
import me.thekusch.ozancaseproject.presentation.feature.DetailPage.BUNDLE_UUID
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment: BaseFragment<FragmentDetailPageBinding>() {

    private var uuid: String? = null

    private val viewModel by viewModel<DetailViewModel>()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentDetailPageBinding? {
        return FragmentDetailPageBinding.inflate(inflater, container, attachToRoot)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            uuid = it.getString(BUNDLE_UUID)
        }
    }

    override fun fetch() {
        super.fetch()
        viewModel.getCoinDetail(uuid)
    }

    override fun observe() {
        super.observe()
        viewModel.getCoinDetailLiveData.observeData(
            lifecycleOwner = viewLifecycleOwner,
            success = {
                activity?.title = it?.data?.symbol
                binding.coinDetail.setup(null,it?.data)
                viewModel.getCoinPriceHistory(uuid)
            },
        )
        viewModel.getCoinPriceHistoryLiveData.observeData(
            lifecycleOwner = viewLifecycleOwner,
            success = {
                binding.coinDetail.updateItems(it?.getCoinHistoryResult())
            }
        )
    }

    companion object {
        fun newInstance(
            uuid: String?
        ) = DetailFragment().apply {
            arguments = Bundle().apply {
                putString(BUNDLE_UUID,uuid)
            }
        }
    }
}