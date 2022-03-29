package com.dstakhanov.findealscommithistory.presentation.reports

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dstakhanov.findealscommithistory.databinding.FragmentInstrumentItemBinding
import com.dstakhanov.findealscommithistory.databinding.FragmentInstrumentReportBinding
import com.dstakhanov.findealscommithistory.domain.item.InstrumentItem
import com.dstakhanov.findealscommithistory.presentation.InstrumentApp
import com.dstakhanov.findealscommithistory.presentation.ViewModelFactory
import com.dstakhanov.findealscommithistory.presentation.item.InstrumentItemFragmentDirections
import com.dstakhanov.findealscommithistory.presentation.item.InstrumentItemViewModel
import com.dstakhanov.findealscommithistory.presentation.item.adapters.InstrumentItemListAdapter
import javax.inject.Inject

class InstrumentReportFragment : Fragment() {
    private lateinit var viewModel: InstrumentReportViewModel

    private var _binding: FragmentInstrumentReportBinding? = null
    private val binding: FragmentInstrumentReportBinding
        get() = _binding ?: throw RuntimeException("FragmentInstrumentReportBinding is null")


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
        _binding = FragmentInstrumentReportBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(InstrumentReportViewModel::class.java)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}