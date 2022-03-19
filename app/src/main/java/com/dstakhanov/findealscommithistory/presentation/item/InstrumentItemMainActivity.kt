package com.dstakhanov.findealscommithistory.presentation.item

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.dstakhanov.findealscommithistory.R
import com.dstakhanov.findealscommithistory.databinding.ActivityInstrumentItemMainBinding
import com.dstakhanov.findealscommithistory.presentation.InstrumentApp
import com.dstakhanov.findealscommithistory.presentation.ViewModelFactory
import javax.inject.Inject

class InstrumentItemMainActivity : AppCompatActivity(), InstrumentItemFragment.OnEditingFinishedListener {
    private lateinit var viewModel: InstrumentItemMainViewModel
    private lateinit var instrumentListAdapter: InstrumentItemListAdapter
    private lateinit var binding: ActivityInstrumentItemMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val component by lazy{
        (application as InstrumentApp).component
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityInstrumentItemMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        viewModel = ViewModelProvider(this, viewModelFactory).get(InstrumentItemMainViewModel::class.java)
        viewModel.instrumentList.observe(this) {
            instrumentListAdapter.submitList(it)
        }
        binding.buttonAddInstrumentItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = InstrumentItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(InstrumentItemFragment.newInstanceAddItem())
            }
        }
    }

    private fun isOnePaneMode(): Boolean {
        return binding.instrumentItemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.instrument_item_container, fragment)
            .addToBackStack(null)
            .commit()
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
                viewModel.deleteInstrumentItem(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvInstrumentList)
    }

    private fun setupClickListener() {
        instrumentListAdapter.onInstrumentItemClickListener = {
            if (isOnePaneMode()) {
                val intent = InstrumentItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(InstrumentItemFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun setupLongClickListener() {
        instrumentListAdapter.onInstrumentItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

    override fun onEditingFinished() {
        Toast.makeText(this@InstrumentItemMainActivity, "Success", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }
}