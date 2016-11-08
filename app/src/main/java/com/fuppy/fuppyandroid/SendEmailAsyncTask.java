package com.fuppy.fuppyandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Ayumu on 16/11/7.
 */

class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private Context context;

    public SendEmailAsyncTask(Context context){
        this.context = context;
    }
    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            GMailSender sender = new GMailSender("fuppytest@gmail.com", "fuppy123");
            sender.sendMail("This is Subject",
                    "This is Body",
                    "fuppytest@gmail.com",
                    "fuppyrecipient@gmail.com");
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result)
            Toast.makeText(context,"Send Email Successfully",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context,"Send Email Failure",Toast.LENGTH_LONG).show();
    }
}
