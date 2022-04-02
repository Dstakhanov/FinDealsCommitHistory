package com.dstakhanov.findealscommithistory.presentation.info

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dstakhanov.findealscommithistory.databinding.FragmentInstrumentInfoPriceListBinding
import com.dstakhanov.findealscommithistory.domain.info.InstrumentInfo
import com.dstakhanov.findealscommithistory.presentation.InstrumentApp
import com.dstakhanov.findealscommithistory.presentation.ViewModelFactory
import com.dstakhanov.findealscommithistory.presentation.info.adapters.InstrumentInfoAdapter
import javax.inject.Inject

class InstrumentInfoPriceListFragment : Fragment() {

    private lateinit var infoViewModel: InstrumentInfoViewModel

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
                    launchDetailFragment(instrumentPriceInfo.fromSymbol)
                }
            }
        binding.rvInstrumentPriceList.adapter = adapter
        binding.rvInstrumentPriceList.itemAnimator = null
        infoViewModel =
            ViewModelProvider(this, viewModelFactory)[InstrumentInfoViewModel::class.java]
        infoViewModel.instrumentInfoList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun launchDetailFragment(fromSymbol: String) {
        findNavController().navigate(
            InstrumentInfoPriceListFragmentDirections.actionNavHomeToInstrumentInfoDetailFragment(
                fromSymbol
            )
        )
    }

}