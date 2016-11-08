package com.fuppy.fuppyandroid;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.mg2.petfinder.PetfinderApi;
import com.mg2.petfinder.responseobjects.GetPet;
import com.mg2.petfinder.responseobjects.PetfinderResponse;
import com.mg2.petfinder.schemaobjects.Pet;
import com.mg2.petfinder.schemaobjects.Shelter;

/**
 * Created by Ayumu on 16/10/30.
 */

class GetShelterTask extends AsyncTask<String,Void,Shelter> {
    private String key = "ca0c01f6164f3e8d18275625bf68c250";
    private String secret = "5f8426ed78bcd595d01076beee9de302";
    private PetfinderApi api;
    private Shelter shelter;

    @Override
    protected Shelter doInBackground(String... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        try {

            //initialize api method
            api = new PetfinderApi(key, secret);
            //call GetPet(id)method
            shelter = api.GetShelter(params[0]);

//            Log.i("shelter",shelter.getName());
//            Log.i("shelter",""+shelter.getCity());
//            Log.i("shelter",""+shelter.latitude);

            return shelter;

        } catch (Exception e) {
            Log.e("error", e.getMessage());
            return null;
        }
    }

}
