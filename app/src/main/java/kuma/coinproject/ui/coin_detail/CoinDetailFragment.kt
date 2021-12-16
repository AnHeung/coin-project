package kuma.coinproject.ui.coin_detail

import android.text.method.ScrollingMovementMethod
import androidx.navigation.fragment.navArgs
import kuma.coinproject.R
import kuma.coinproject.databinding.FragmentCoinDetailBinding
import kuma.coinproject.ui.base.BaseFragment
import kuma.coinproject.utils.navigateUp
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinDetailFragment(override var layoutId: Int = R.layout.fragment_coin_detail) : BaseFragment<FragmentCoinDetailBinding>() {

    private val navArgs: CoinDetailFragmentArgs by navArgs()
    private val coinId: String by lazy { navArgs.coinId }
    private val coinName: String by lazy { navArgs.coinName }
    private val coinDetailViewModel:CoinDetailViewModel by viewModel()

    override fun bindingLiveData() {
        coinDetailViewModel.apply {
            println("coinID: $coinId")
            getCoinDetailItem(coinId)
        }
    }

    override fun initBinding() {
        dataBinding.apply {
            viewModel = coinDetailViewModel
            coinDetailDescriptionTxt.movementMethod = ScrollingMovementMethod()
            detailToolBar.apply {
                setNavigationOnClickListener { navigateUp() }
                title = "$coinName(${coinId})"
            }
        }
    }
}