package me.thekusch.ozancaseproject.presentation.home

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import com.google.android.material.snackbar.Snackbar
import me.thekusch.ozancaseproject.core.BaseFragment
import me.thekusch.ozancaseproject.databinding.FragmentHomeBinding
import me.thekusch.ozancaseproject.presentation.ext.dismiss
import me.thekusch.ozancaseproject.presentation.ext.observeData
import me.thekusch.ozancaseproject.presentation.ext.show
import me.thekusch.ozancaseproject.presentation.home.components.CoinList
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModel<HomeViewModel>()

    private val timer = object: CountDownTimer(2000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            binding.progressBar.show()
        }
        override fun onFinish() {
            viewModel.getCoinList()
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ): FragmentHomeBinding? {
        return FragmentHomeBinding.inflate(inflater, container, attachToRoot)
    }

    override fun initView() {
        super.initView()
        binding.nestedScrollView.setOnScrollChangeListener { v: NestedScrollView, _, scrollY, _, _ ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                if(!viewModel.isRequestProcessing) {
                    viewModel.isRequestProcessing = true
                    timer.start()
                }
            }
        }
        binding.coinList.onSortSelectListener = { pos ->
            viewModel.clearOffset()
            viewModel.setNewOrderBy(pos)
            binding.coinList.clearAll()
            binding.progressBar.show()
            viewModel.getCoinList()
        }
        binding.coinList.onItemClickListener = { id, entity ->
            navigation.navigateToDetailPage(id)
        }
    }

    override fun fetch() {
        super.fetch()
        viewModel.getCoinList()
    }

    override fun observe() {
        super.observe()
        viewModel.getCoinsLiveData.observeData(
            lifecycleOwner = viewLifecycleOwner,
            loading = {
                binding.progressBar.show()
            },
            success = {
                binding.progressBar.dismiss()
                if (it?.data.isNullOrEmpty()) {
                    setSnackbar("It seems there is no more data")
                    viewModel.rollBackTheOffset()
                } else {
                    binding.coinList.setup(it?.getCoinResult())
                }
                viewModel.isRequestProcessing = false
            },
            fail = {
                binding.progressBar.dismiss()
                viewModel.rollBackTheOffset()
                viewModel.isRequestProcessing = false
                setSnackbar("It seems there is an error!")
            }
        )
    }

    private fun setSnackbar(
        snackbarMessage: String,
        snackbarAction: (Snackbar.() -> Snackbar)? = null
    ) {
        val snackbar = Snackbar
            .make(binding.root, snackbarMessage, Snackbar.LENGTH_LONG)
        if (snackbarAction != null) {
            snackbar.snackbarAction()
        }
        snackbar.show()

    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}