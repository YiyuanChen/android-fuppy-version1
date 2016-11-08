package com.fuppy.fuppyandroid

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

import com.fuppy.fuppyandroid.api_calls.PetfinderTask
import com.fuppy.fuppyandroid.model.SearchRequest
import com.mg2.petfinder.schemaobjects.Pet

class DisplaySearchResultActivity : ListActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_page_main)

        val request = intent.getParcelableExtra <SearchRequest>("request")
        PetfinderTask(request, { pets -> listAdapter = PetListAdapter(this, pets)}).execute()

        listAdapter = ArrayAdapter<Pet>(this, R.layout.activity_list_display)
        //initialize and set PetListAdapter
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {

        //set intent to call DisplayPetDetailActivity when items are clicked
        val intent = Intent(this, DisplayPetDetailActivity::class.java)
        //get selected items
        val selectedPet = listAdapter.getItem(position) as Pet
        Toast.makeText(this, selectedPet.name, Toast.LENGTH_SHORT).show()
        //pass petId to next activity
        intent.putExtra("petId", selectedPet.id)
        startActivity(intent)
    }
}
