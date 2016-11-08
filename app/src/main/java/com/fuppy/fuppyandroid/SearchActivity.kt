package com.fuppy.fuppyandroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.fuppy.fuppyandroid.model.SearchRequest
import com.fuppy.fuppyandroid.widgets.SpinnerListener

/**
 * Starting activity that starts a pet searching page
 */
class SearchActivity(var searchRequest: SearchRequest = SearchRequest()) : AppCompatActivity() {

    /**
     * This is a convenient util method to bootstrap a spinner component
     */
    fun initializeSpinner(uiId: Int, valueSource: Int, func: (String) -> Unit) {
        val spinner = findViewById(uiId) as Spinner
        val adapter = ArrayAdapter.createFromResource(this, valueSource, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = SpinnerListener(func)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initializeSpinner(R.id.animal_type, R.array.animal_type_spinner_values, { x -> searchRequest.type = x})
        initializeSpinner(R.id.animal_size, R.array.animal_size_spinner_values, { x -> searchRequest.size = x})
        initializeSpinner(R.id.animal_sex, R.array.animal_sex_spinner_values, { x -> searchRequest.sex = x})
        initializeSpinner(R.id.animal_age, R.array.animal_age_spinner_values, { x -> searchRequest.age = x})

        //TODO dynamically populate breed list, requires a API call
    }

    fun submitSearch(view: View) {
        val intent = Intent(this, DisplaySearchResultActivity::class.java)
        searchRequest.location = (findViewById(R.id.zipcode) as TextView).text.toString()
        intent.putExtra("request", searchRequest)
        startActivity(intent)
    }
}
