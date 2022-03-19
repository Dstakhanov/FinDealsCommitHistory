package com.dstakhanov.findealscommithistory.presentation.info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dstakhanov.findealscommithistory.R
import com.dstakhanov.findealscommithistory.databinding.ActivityInstrumentInfoPriceListBinding
import com.dstakhanov.findealscommithistory.domain.info.InstrumentInfo
import com.dstakhanov.findealscommithistory.presentation.InstrumentApp
import com.dstakhanov.findealscommithistory.presentation.ViewModelFactory
import com.dstakhanov.findealscommithistory.presentation.info.adapters.InstrumentInfoAdapter
import javax.inject.Inject

class InstrumentInfoPriceListActivity : AppCompatActivity() {

    private lateinit var infoViewModel: InstrumentInfoViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val binding by lazy{
        ActivityInstrumentInfoPriceListBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as InstrumentApp).component
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = InstrumentInfoAdapter(this)
        adapter.onInstrumentClickListener = object : InstrumentInfoAdapter.OnInstrumentClickListener {
            override fun onInstrumentClick(instrumentPriceInfo: InstrumentInfo) {
                if(isOnePaneMode()){
                    launchDetailAcitivity(instrumentPriceInfo.fromSymbol)
                }else{
                    launchDetailFragment(instrumentPriceInfo.fromSymbol)
                }
            }
        }
        binding.rvInstrumentPriceList?.adapter = adapter
        binding.rvInstrumentPriceList?.itemAnimator = null
        infoViewModel = ViewModelProvider(this, viewModelFactory)[InstrumentInfoViewModel::class.java]
        infoViewModel.instrumentInfoList.observe(this)  {
            adapter.submitList(it)
        }

    }

    private fun isOnePaneMode() = binding.fragmentContainer == null

    private fun launchDetailAcitivity(fromSymbol: String){
        val intent = InstrumentInfoDetailActivity.newIntent(
            this@InstrumentInfoPriceListActivity,
            fromSymbol
        )
        startActivity(intent)
    }

    private fun launchDetailFragment(fromSymbol: String){
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, InstrumentInfoDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()

    }

}