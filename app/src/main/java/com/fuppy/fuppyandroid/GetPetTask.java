package com.fuppy.fuppyandroid;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.mg2.petfinder.responseobjects.FindPets;
import com.mg2.petfinder.responseobjects.GetPet;
import com.mg2.petfinder.responseobjects.PetfinderResponse;
import com.mg2.petfinder.schemaobjects.Pet;
import com.google.gson.reflect.TypeToken;
import com.mg2.petfinder.PetfinderApi;

import static android.R.attr.type;


/**
 * Created by Ayumu on 16/10/25.
 */

class GetPetTask extends AsyncTask<Integer,Void,Pet>{
    private String key = "ca0c01f6164f3e8d18275625bf68c250";
    private String secret = "5f8426ed78bcd595d01076beee9de302";
    private PetfinderApi api;
    private Pet pet;

    @Override
    protected Pet doInBackground(Integer... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        try {

            //initialize api method
            api = new PetfinderApi(key, secret);
            //call GetPet(id)method
            pet = api.GetPet(params[0], new TypeToken<PetfinderResponse<GetPet<Pet>>>(){}.getType());


            Log.i("pet",pet.getName());
            Log.i("pet",""+pet.getId());
            Log.i("pet",pet.getSize().getValue());
            Log.i("pet",pet.getShortDescription());


            return pet;

        } catch (Exception e) {
            Log.e("error", e.getMessage());
            return null;
        }
    }

}
