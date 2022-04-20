package com.dstakhanov.item.presentation

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dstakhanov.item.R
import com.dstakhanov.item.databinding.FragmentInstrumentItemDetailBinding
import com.dstakhanov.item.di.ItemComponent
import com.dstakhanov.utils.DateUtility
import com.dstakhanov.utils.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class InstrumentItemDetailFragment : BottomSheetDialogFragment() {

    private lateinit var detailViewModel: InstrumentItemDetailViewModel
    private lateinit var onEditingFinishedListener: OnEditingFinishedListener
    private val args by navArgs<InstrumentItemDetailFragmentArgs>()

    private var _binding: FragmentInstrumentItemDetailBinding? = null
    private val binding: FragmentInstrumentItemDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentInstrumentItemDetailBinding == null")

    private var screenMode: String = MODE_UNKNOWN
    private var instrumentItemId: Int = com.dstakhanov.item.domain.InstrumentItem.UNDEFINED_ID

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var dateUtility: DateUtility

    private lateinit var component: ItemComponent

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
        if (context is OnEditingFinishedListener) {
            onEditingFinishedListener = context
        } else {
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
        _binding = FragmentInstrumentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel =
            ViewModelProvider(this, viewModelFactory)[InstrumentItemDetailViewModel::class.java]

        addTextChangeListeners()
        launchRightMode()
        observeViewModel()
        setupDatePicker()
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
    }

    private fun observeViewModel() {
        detailViewModel.errorInputCount.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            binding.tilCount.error = message
        }
        detailViewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            binding.tilName.error = message
        }
        detailViewModel.errorInputPrice.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_price)
            } else {
                null
            }
            binding.tilPrice.error = message
        }
        detailViewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            onEditingFinishedListener.onEditingFinished()
            findNavController().popBackStack()
        }
    }

    private fun addTextChangeListeners() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                detailViewModel.resetErrorInputName()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.etCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                detailViewModel.resetErrorInputCount()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.etPrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                detailViewModel.resetErrorInputPrice()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

    }

    private fun launchEditMode() {
        detailViewModel.getInstrumentItem(instrumentItemId)
        detailViewModel.instrumentItem.observe(viewLifecycleOwner) {
            with(binding) {
                etName.setText(it.symbol)
                etCount.setText(it.count.toString())
                etPrice.setText(it.price.toString())
                etDate.setText(it.createDate)
                if (it.direction == com.dstakhanov.item.presentation.InstrumentItemDetailFragment.SELL_DIRECTION)
                    rbSell.isChecked = true else rbBuy.isChecked = true
            }
        }
        binding.saveButton.setOnClickListener {
            detailViewModel.editInstrumentItem(
                binding.etName.text?.toString(),
                binding.etCount.text?.toString(),
                binding.etPrice.text?.toString(),
                binding.etDate.text?.toString(),
                if (binding.rbSell.isChecked) SELL_DIRECTION else BUY_DIRECTION
            )
        }
    }

    private fun launchAddMode() {
        binding.saveButton.setOnClickListener {
            detailViewModel.addInstrumentItem(
                binding.etName.text?.toString(),
                binding.etCount.text?.toString(),
                binding.etPrice.text?.toString(),
                binding.etDate.text?.toString(),
                if (binding.rbSell.isChecked) SELL_DIRECTION else BUY_DIRECTION
            )
        }
    }

    private fun parseParams() {

        val mode = args.screenMode
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            instrumentItemId = args.instrumentItemId
        }
    }

    interface OnEditingFinishedListener {
        fun onEditingFinished()
    }

    fun setupDatePicker() {

        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = dateUtility.patternDate
            val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
            binding.etDate.setText(sdf.format(cal.time))
        }

        binding.etDate.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                val dialog = DatePickerDialog(requireContext(), dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH))
                dialog.datePicker.maxDate = dateUtility.getCurrentDateInMills()
                dialog.show()
            }
        }

    }

    companion object {

        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""
        private const val BUY_DIRECTION = 1
        private const val SELL_DIRECTION = -1

    }
}