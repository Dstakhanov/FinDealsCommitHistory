package com.dstakhanov.reports.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dstakhanov.reports.databinding.FragmentReportBinding
import com.dstakhanov.reports.di.ReportsComponent
import com.dstakhanov.utils.ViewModelFactory
import javax.inject.Inject

class ReportFragment : Fragment() {
    private lateinit var viewModel: ReportViewModel

    private var _binding: FragmentReportBinding? = null
    private val binding: FragmentReportBinding
        get() = _binding ?: throw RuntimeException("FragmentInstrumentReportBinding is null")


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var component: ReportsComponent

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ReportViewModel::class.java)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = it
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}