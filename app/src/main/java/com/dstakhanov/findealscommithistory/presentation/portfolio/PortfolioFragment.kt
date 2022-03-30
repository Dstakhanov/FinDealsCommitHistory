package com.dstakhanov.findealscommithistory.presentation.portfolio

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dstakhanov.findealscommithistory.databinding.FragmentPortfolioBinding
import com.dstakhanov.findealscommithistory.databinding.FragmentReportBinding
import com.dstakhanov.findealscommithistory.presentation.InstrumentApp
import com.dstakhanov.findealscommithistory.presentation.ViewModelFactory
import javax.inject.Inject

class PortfolioFragment : Fragment() {
    private lateinit var viewModel: PortfolioViewModel

    private var _binding: FragmentPortfolioBinding? = null
    private val binding: FragmentPortfolioBinding
        get() = _binding ?: throw RuntimeException("FragmentPortfolioBinding is null")


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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
        _binding = FragmentPortfolioBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(PortfolioViewModel::class.java)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}