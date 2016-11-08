package com.fuppy.fuppyandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mg2.petfinder.schemaobjects.Pet;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DisplayPetDetailActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private Bitmap[] photoList;
    private ImageView imageView;
    private TextView petName,petAge,petGender,petSize,petDescription;
    private int current_photo;
    private ImageButton button_next,button_previous;
    private ArrayList<String> petPhotoUrlList;
    private Pet pet;
    private String shelterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_detail);
        Intent intent = getIntent();

        //receive the intent message of petID
        int petId = intent.getIntExtra("petId",11);
        Log.d("petId",""+petId);
        try {
            //call petfinder api GetPet(id) for fetching pet's information
            pet = new GetPetTask().execute(petId).get();
            Log.d("pet",pet.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        petName = (TextView) findViewById(R.id.petName);
        petAge = (TextView)findViewById(R.id.petAge);
        petGender = (TextView)findViewById(R.id.petGender);
        petSize = (TextView)findViewById(R.id.petSize);
        petDescription = (TextView)findViewById(R.id.petDescription);

        petPhotoUrlList = pet.getMedia().getPhotos().getXLargePhotoUrlList();
        photoList = new Bitmap[petPhotoUrlList.size()];

        button_next = (ImageButton) findViewById(R.id.button_next);
        button_previous = (ImageButton)findViewById(R.id.button_previous);

        shelterId = pet.getShelterId();

        //set basic pet information
        petName.append("Name:\t"+pet.getName());
        petAge.append("Age:\t"+pet.getAge().getValue());
        //need to modify getSex() function in jar.
        petGender.append("Gender:\t"+pet.getSex());
        petSize.append("Size:\t"+pet.getSize().getValue());
        petDescription.append("Description:\n"+pet.getDescription());
        imageView = (ImageView)findViewById(R.id.DetailImageView);

        try {
            //show the first photo, and set count=1;
            for (int i=0;i<petPhotoUrlList.size();i++) {
                bitmap = new ShowURL().execute(petPhotoUrlList.get(i)).get();
                photoList[i] = bitmap;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        current_photo=0;
        imageView.setImageBitmap(photoList[0]);

        //click next button to show next photo
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if it is final photo, reset count
                if (current_photo < petPhotoUrlList.size()-1) {
                    current_photo++;
                    imageView.setImageBitmap(photoList[current_photo]);
                }
                else{
                    current_photo=0;
                    imageView.setImageBitmap(photoList[current_photo]);
                }
            }
        });

        //show the previous photo
        button_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_photo > 0) {
                    current_photo --;
                    imageView.setImageBitmap(photoList[current_photo]);
                } else {
                    current_photo = petPhotoUrlList.size()-1;
                    imageView.setImageBitmap(photoList[current_photo]);
                }
            }
        });
    }

    //click and show Shelter Page
    public void showShelter(View view) throws IOException{
        Intent goShelter = new Intent(this,ShelterInfoActivity.class);
        goShelter.putExtra("shelterId",shelterId);
        startActivity(goShelter);
    }

}
