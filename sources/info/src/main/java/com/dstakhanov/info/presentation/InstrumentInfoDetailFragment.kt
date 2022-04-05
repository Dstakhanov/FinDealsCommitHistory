package com.dstakhanov.info.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.dstakhanov.info.databinding.FragmentInstrumentInfoDetailBinding
import com.dstakhanov.info.di.InfoComponent
import com.dstakhanov.utils.ViewModelFactory

import com.squareup.picasso.Picasso
import javax.inject.Inject

class InstrumentInfoDetailFragment : Fragment() {

    private lateinit var infoViewModel: InstrumentInfoViewModel
    private val args by navArgs<InstrumentInfoDetailFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var component: InfoComponent

    private var _binding: FragmentInstrumentInfoDetailBinding? = null
    private val binding: FragmentInstrumentInfoDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentInstrumentDetailBinding is null")


    override fun onAttach(context: Context) {
        component.inject(this)
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
        val fromSymbol = args.fSym
        infoViewModel =
            ViewModelProvider(this, viewModelFactory)[InstrumentInfoViewModel::class.java]
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

}