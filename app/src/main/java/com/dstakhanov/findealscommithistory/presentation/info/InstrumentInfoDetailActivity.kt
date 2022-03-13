package com.dstakhanov.findealscommithistory.presentation.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dstakhanov.findealscommithistory.R
import com.dstakhanov.findealscommithistory.databinding.ActivityInstrumentDetailBinding

class InstrumentInfoDetailActivity : AppCompatActivity() {


    private val binding by lazy {
        ActivityInstrumentDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, InstrumentInfoDetailFragment.newInstance(fromSymbol))
                .commit()
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""
        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, InstrumentInfoDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}