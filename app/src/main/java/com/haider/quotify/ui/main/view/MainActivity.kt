package com.haider.quotify.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.haider.quotify.data.api.QuoteService
import com.haider.quotify.data.api.RetrofitHelper
import com.haider.quotify.data.model.Result
import com.haider.quotify.data.repository.QuoteRepository
import com.haider.quotify.databinding.ActivityMainBinding
import com.haider.quotify.ui.main.viewmodel.QuotifyViewModel
import com.haider.quotify.ui.main.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: QuotifyViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var result: List<Result>
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)

        val repository = QuoteRepository(quoteService)
        viewModel = ViewModelProvider(this,ViewModelFactory(repository)).get(QuotifyViewModel::class.java)

        binding.next.setOnClickListener {
            onNext()
        }

        binding.previous.setOnClickListener {
            onPrevious()
        }


        viewModel.quotes.observe(this, Observer {
            result = it.results
            setQuote(result[index])
        })

    }

    private fun onPrevious() {
        if (index != 0) {
            setQuote(result[--index])
        } else {
            Toast.makeText(this,"Your are at First",Toast.LENGTH_LONG).show()
        }

    }

    private fun onNext() {
        if (index < result.size - 1) {
            setQuote(result[++index])
        }
    }

    private fun setQuote(result: Result) {
        binding.quote.text = result.content
        binding.quoteAuthor.text = result.author
    }
}