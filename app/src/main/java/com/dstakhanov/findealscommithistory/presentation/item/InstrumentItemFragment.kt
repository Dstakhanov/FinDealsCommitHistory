package com.dstakhanov.findealscommithistory.presentation.item

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dstakhanov.findealscommithistory.R
import com.dstakhanov.findealscommithistory.databinding.FragmentInstrumentItemBinding
import com.dstakhanov.findealscommithistory.domain.item.InstrumentItem
import com.dstakhanov.findealscommithistory.presentation.InstrumentApp
import com.dstakhanov.findealscommithistory.presentation.ViewModelFactory
import com.dstakhanov.findealscommithistory.presentation.item.adapters.InstrumentItemListAdapter
import javax.inject.Inject

class InstrumentItemFragment : Fragment() {
    private lateinit var viewModel: InstrumentItemViewModel
    private lateinit var instrumentListAdapter: InstrumentItemListAdapter

    private var _binding: FragmentInstrumentItemBinding? = null
    private val binding: FragmentInstrumentItemBinding
        get() = _binding ?: throw RuntimeException("FragmentInstrumentItemBinding is null")


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
        _binding = FragmentInstrumentItemBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(InstrumentItemViewModel::class.java)
        viewModel.instrumentList.observe(this) {
            instrumentListAdapter.submitList(it)
        }
        binding.buttonAddInstrumentItem.setOnClickListener {
            launchDetailFragment(InstrumentItem.UNDEFINED_ID, MODE_ADD)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchDetailFragment(instrumentItemId: Int, screenMode: String) {
        findNavController().navigate(
            InstrumentItemFragmentDirections.actionInstrumentItemListToInstrumentItemDetailFragment(
                instrumentItemId,
                screenMode
            )
        )

    }


    private fun setupRecyclerView() {

        with(binding.rvInstrumentItemList) {
            instrumentListAdapter = InstrumentItemListAdapter()
            adapter = instrumentListAdapter
            recycledViewPool.setMaxRecycledViews(
                InstrumentItemListAdapter.VIEW_TYPE_ENABLED,
                InstrumentItemListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                InstrumentItemListAdapter.VIEW_TYPE_DISABLED,
                InstrumentItemListAdapter.MAX_POOL_SIZE
            )
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(binding.rvInstrumentItemList)
    }

    private fun setupClickListener() {
        instrumentListAdapter.onInstrumentItemClickListener = {
            launchDetailFragment(it.id, MODE_EDIT)
        }
    }

    private fun setupSwipeListener(rvInstrumentList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = instrumentListAdapter.currentList[viewHolder.adapterPosition]
                AlertDialog.Builder(requireContext()).apply {
                    setTitle(getString(R.string.dialog_label))
                    setMessage(getString(R.string.dialog_question))
                    setPositiveButton(android.R.string.ok) { dialog, which ->
                        viewModel.deleteInstrumentItem(item)
                    }
                    setNegativeButton(android.R.string.cancel) { dialog, which ->
                        instrumentListAdapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    setOnCancelListener { dialog ->
                        instrumentListAdapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    show()
                }
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvInstrumentList)
    }


    private fun setupLongClickListener() {
        instrumentListAdapter.onInstrumentItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

    companion object {

        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"

    }

}