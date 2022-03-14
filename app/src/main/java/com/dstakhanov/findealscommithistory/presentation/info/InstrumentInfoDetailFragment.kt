package com.dstakhanov.findealscommithistory.presentation.info

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dstakhanov.findealscommithistory.databinding.FragmentInstrumentInfoDetailBinding
import com.dstakhanov.findealscommithistory.presentation.ViewModelFactory
import com.squareup.picasso.Picasso

class InstrumentInfoDetailFragment : Fragment() {

    private lateinit var infoViewModel: InstrumentInfoViewModel

    lateinit var viewModelFactory: ViewModelFactory


    private var _binding: FragmentInstrumentInfoDetailBinding? = null
    private val binding: FragmentInstrumentInfoDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentInstrumentDetailBinding is null")


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstrumentInfoDetailBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromSymbol = getSymbol()
        infoViewModel = ViewModelProvider(this, viewModelFactory)[InstrumentInfoViewModel::class.java]
        infoViewModel.getDetailInfo(fromSymbol).observe(viewLifecycleOwner) {
            with(binding) {
                tvPrice.text = it.price
                tvMinPrice.text = it.lowDay
                tvMaxPrice.text = it.highDay
                tvLastMarket.text = it.lastMarket
                tvLastUpdate.text = it.lastUpdate
                tvFromSymbol.text = it.toSymbol
                tvToSymbol.text = it.fromSymbol
                Picasso.get().load(it.imageUrl).into(ivLogoInstrument)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getSymbol(): String {
        return requireArguments().getString(EXTRA_FROM_SYMBOL, EMPTY_SYMBOL)
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""
        fun newInstance(fromSymbol: String): Fragment {
            return InstrumentInfoDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, fromSymbol)
                }
            }
        }
    }
}