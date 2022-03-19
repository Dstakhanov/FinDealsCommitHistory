package com.dstakhanov.findealscommithistory.presentation.item

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dstakhanov.findealscommithistory.R
import com.dstakhanov.findealscommithistory.databinding.FragmentInstrumentItemBinding
import com.dstakhanov.findealscommithistory.domain.item.InstrumentItem
import com.dstakhanov.findealscommithistory.presentation.InstrumentApp
import com.dstakhanov.findealscommithistory.presentation.ViewModelFactory
import javax.inject.Inject

class InstrumentItemFragment: Fragment() {

    private lateinit var mainViewModel: InstrumentItemViewModel
    private lateinit var onEditingFinishedListener: OnEditingFinishedListener

    private var _binding: FragmentInstrumentItemBinding? = null
    private val binding: FragmentInstrumentItemBinding
        get() = _binding?: throw RuntimeException("FragmentInstrumentItemBinding == null")

    private var screenMode: String = MODE_UNKNOWN
    private var instrumentItemId: Int = InstrumentItem.UNDEFINED_ID

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy{
        (requireActivity().application as InstrumentApp).component
    }
    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
        if(context is OnEditingFinishedListener){
            onEditingFinishedListener = context
        }else{
            throw RuntimeException("Activity must implement listener!")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstrumentItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(this, viewModelFactory)[InstrumentItemViewModel::class.java]
        addTextChangeListeners()
        launchRightMode()
        observeViewModel()
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun observeViewModel() {
        mainViewModel.errorInputCount.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            binding.tilCount.error = message
        }
        mainViewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            binding.tilName.error = message
        }
        mainViewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            onEditingFinishedListener.onEditingFinished()
        }
    }


    private fun addTextChangeListeners() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mainViewModel.resetErrorInputName()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mainViewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun launchEditMode() {
        mainViewModel.getInstrumentItem(instrumentItemId)
        binding.saveButton.setOnClickListener {
            mainViewModel.editInstrumentItem(
                binding.etName.text?.toString(),
                binding.etCount.text?.toString(),
                binding.etPrice.text?.toString(),
                binding.etDate.text?.toString()
            )
        }
    }

    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            mainViewModel.addInstrumentItem(
                binding.etName.text?.toString(),
                binding.etCount.text?.toString(),
                binding.etPrice.text?.toString(),
            )
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(INSTRUMENT_ITEM_ID)) {
                throw RuntimeException("Param instrument item id is absent")
            }
            instrumentItemId = args.getInt(INSTRUMENT_ITEM_ID, InstrumentItem.UNDEFINED_ID)
        }


    }

    interface OnEditingFinishedListener{
        fun onEditingFinished()
    }


    companion object {

        private const val SCREEN_MODE = "extra_mode"
        private const val INSTRUMENT_ITEM_ID = "extra_instrument_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddItem(): InstrumentItemFragment {
            return InstrumentItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(instrumentItemId: Int): InstrumentItemFragment {
            return InstrumentItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(INSTRUMENT_ITEM_ID, instrumentItemId)
                }
            }
        }
    }
}