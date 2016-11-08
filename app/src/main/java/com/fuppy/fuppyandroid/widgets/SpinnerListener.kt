package com.fuppy.fuppyandroid.widgets

import android.view.View
import android.widget.AdapterView

/**
 * This listener class allow binding the selected value back to the data class property
 */
class SpinnerListener(val func: (String) -> Unit) : AdapterView.OnItemSelectedListener{

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        func(parent.getItemAtPosition(position).toString())
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        parent.getItemIdAtPosition(0)
    }
}