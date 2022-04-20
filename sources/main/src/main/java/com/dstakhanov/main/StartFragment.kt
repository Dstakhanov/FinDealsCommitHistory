package com.dstakhanov.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dstakhanov.navigation.NavigationFlow
import com.dstakhanov.navigation.ToFlowNavigatable

class StartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
             (requireActivity() as ToFlowNavigatable).navigateToFlow(NavigationFlow.InfoFlow())
        }
    }
