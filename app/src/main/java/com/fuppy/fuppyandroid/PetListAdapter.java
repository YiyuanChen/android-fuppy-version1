package com.fuppy.fuppyandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mg2.petfinder.schemaobjects.Pet;

import java.util.concurrent.ExecutionException;

/**
 * Created by Ayumu on 16/10/25.
 */

//Pet List Adapter, used in ListView
public class PetListAdapter extends ArrayAdapter<Pet> {
    private Pet[] pets;
    private Context context;
    private Bitmap bitmap;

    public PetListAdapter(Context context, Pet[] pets){//initialize
        super(context,R.layout.activity_list_display,pets);
        this.context = context;
        this.pets = pets;
    }
    //For every pet, generate its view
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View listDisplayView = inflater.inflate(R.layout.activity_list_display, parent, false);

        //Set custom layout in activity_list_display
        TextView textView = (TextView) listDisplayView.findViewById(R.id.textViewList);
        TextView shortDescription = (TextView) listDisplayView.findViewById(R.id.shortDescription);
        ImageView imageView = (ImageView) listDisplayView.findViewById(R.id.imageViewList);
        textView.setText(pets[position].getName());
        shortDescription.setText(pets[position].getShortDescription());

        //get url
        String url = pets[position].getMedia().getPhotos().getFirstXLargeUrl();
        try {
            //Get First Photo of a pet
            bitmap = new ShowURL().execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Set the Photo
        imageView.setImageBitmap(bitmap);

        return listDisplayView;
    }
}
