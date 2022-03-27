package com.dstakhanov.findealscommithistory.presentation.info

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dstakhanov.findealscommithistory.R
import com.dstakhanov.findealscommithistory.databinding.FragmentInstrumentInfoPriceListBinding
import com.dstakhanov.findealscommithistory.domain.info.InstrumentInfo
import com.dstakhanov.findealscommithistory.presentation.InstrumentApp
import com.dstakhanov.findealscommithistory.presentation.ViewModelFactory
import com.dstakhanov.findealscommithistory.presentation.info.adapters.InstrumentInfoAdapter
import javax.inject.Inject

class InstrumentInfoPriceListFragment : Fragment() {

    private lateinit var infoViewModel: InstrumentInfoViewModel
    private val args by navArgs<InstrumentInfoPriceListFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var _binding: FragmentInstrumentInfoPriceListBinding? = null
    private val binding: FragmentInstrumentInfoPriceListBinding
        get() = _binding ?: throw RuntimeException("FragmentInstrumentInfoPriceListBinding is null")

    private val component by lazy {
        (requireActivity().application as InstrumentApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstrumentInfoPriceListBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = InstrumentInfoAdapter(requireContext())
        adapter.onInstrumentClickListener =
            object : InstrumentInfoAdapter.OnInstrumentClickListener {
                override fun onInstrumentClick(instrumentPriceInfo: InstrumentInfo) {
                    if (isOnePaneMode()) {
                        launchDetailOnePaneFragment(instrumentPriceInfo.fromSymbol)
                    } else {
                        launchDetailTwoPaneFragment(instrumentPriceInfo.fromSymbol)
                    }
                }
            }
        binding.rvInstrumentPriceList.adapter = adapter
        binding.rvInstrumentPriceList.itemAnimator = null
        infoViewModel =
            ViewModelProvider(this, viewModelFactory)[InstrumentInfoViewModel::class.java]
        infoViewModel.instrumentInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun isOnePaneMode() =
        getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT

    private fun launchDetailOnePaneFragment(fromSymbol: String) {
        findNavController().navigate(
            InstrumentInfoPriceListFragmentDirections.actionNavHomeToInstrumentInfoDetailFragment(
                fromSymbol
            )
        )

    }

    private fun launchDetailTwoPaneFragment(fromSymbol: String) {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, InstrumentInfoDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()

    }

}