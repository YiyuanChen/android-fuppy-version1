package com.fuppy.fuppyandroid.api_calls

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.ListAdapter
import com.fuppy.fuppyandroid.PetListAdapter
import com.fuppy.fuppyandroid.model.SearchRequest

import com.google.gson.reflect.TypeToken
import com.mg2.petfinder.PetfinderApi
import com.mg2.petfinder.responseobjects.FindPets
import com.mg2.petfinder.responseobjects.PetfinderResponse
import com.mg2.petfinder.schemaobjects.Pet


internal class PetfinderTask(val request: SearchRequest, val func: (Array<Pet>) -> Unit) : AsyncTask<String, Void, Array<Pet>>() {

    private val key = "ca0c01f6164f3e8d18275625bf68c250"
    private val secret = "5f8426ed78bcd595d01076beee9de302"

    override fun doInBackground(vararg urls: String): Array<Pet>? {

        try {

            val api = PetfinderApi(key, secret)
            val pets = api.FindPets<Pet>(request.location, request.type, null,
                    request.size, request.sex, request.age, 0, 10,
                    object : TypeToken<PetfinderResponse<FindPets<Pet>>>() {}.type)
            Log.i("pets!!", pets.toString())
            return pets

        } catch (e: Exception) {
            Log.e("error", e.message)
            return null
        }
    }

    override fun onPostExecute(result: Array<Pet>) {
        func(result)
    }
}