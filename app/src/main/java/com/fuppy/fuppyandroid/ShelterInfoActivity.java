package com.fuppy.fuppyandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mg2.petfinder.schemaobjects.Shelter;

import java.util.concurrent.ExecutionException;
import android.app.AlertDialog;

public class ShelterInfoActivity extends AppCompatActivity {

    private Shelter shelter;
    private TextView shelterId,shelterName,shelterEmail;
    private String shelter_Id;
    private final Context context = this;
    private boolean isSendSuccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_info);

        Intent intent = getIntent();
        shelter_Id = intent.getStringExtra("shelterId");

        Log.d("testsh",shelter_Id);
        shelterId = (TextView)findViewById(R.id.shelter_id);
        shelterName = (TextView)findViewById(R.id.shelter_name);
        shelterEmail = (TextView)findViewById(R.id.shelter_email);

        shelterId.setText(shelter_Id);


        try {
            shelter = new GetShelterTask().execute(shelter_Id).get();
            Log.d("shelter",shelter.getName());
            Log.d("shelter",shelter.email.toString());
            Log.d("shelter",shelter.longitude.toString());
            Log.d("shelter",shelter.latitude.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        shelterName.setText(shelter.getName());
    }

    public void sendEmail(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title
        alertDialogBuilder.setTitle("Make Appointment");

        // set dialog message
        alertDialogBuilder
                .setMessage("Are you sure to make an appointment with this shelter?!")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        SendEmailAsyncTask emailTask = new SendEmailAsyncTask(context);
                        emailTask.execute();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }

}
