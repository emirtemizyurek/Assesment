package com.provision.emirhan.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.provision.emirhan.R
import com.provision.emirhan.viewModel.SearchActivityViewModel
import android.content.Intent
import android.widget.*
import androidx.lifecycle.ViewModelProvider

class SearchActivity : AppCompatActivity() {

    lateinit var editTextSearch: EditText
    lateinit var buttonNext: Button
    private lateinit var viewModel: SearchActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initThis()

        viewModel = ViewModelProvider(this).get(SearchActivityViewModel::class.java)

        initClickableItems()

    }

    private fun initThis(){
        editTextSearch = findViewById(R.id.et_search)
        buttonNext = findViewById(R.id.button_next)
    }

    private fun initClickableItems(){
        buttonNext.setOnClickListener {
            if (editTextSearch.text!!.isNotEmpty())
                viewModel.searchLocation(editTextSearch.text.toString())


            val intent = Intent(this, ListMovieActivity::class.java)
            intent.putExtra("Search", editTextSearch.text.toString())
            startActivity(intent)
        }
    }
}
