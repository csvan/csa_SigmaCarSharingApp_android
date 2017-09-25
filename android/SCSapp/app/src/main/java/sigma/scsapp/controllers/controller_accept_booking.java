package sigma.scsapp.controllers;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;

import sigma.scsapp.R;
import sigma.scsapp.activities.UserProfileActivity;

import static sigma.scsapp.R.id.parent;

/**
 * Created by Niklas on 2017-09-18.
 */
/*
public class controller_accept_booking extends AppCompatActivity {


    ListView listofBooking = (ListView) findViewById(R.id.LV_listOfBooking);


// TODO: 2017-09-14 ^^^^^REMOVE WHEN DATABASE IS ONLINE


    // Below handels the List of all the current bookings.
       listofBooking.setOnItemClickListener(new AdapterView.OnItemClickListener() {


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Log.e("Tag for Listview", "You clicked on car with id: " + (position + 1));
            // post save the ID of student instead of the "position" from the array.
            int post = (position + 1);


            // Return the Value from Position (+1) and place it into the AlertDialog.
            HashMap<String, Object> obj = (HashMap<String, Object>) adapter.getItem(position);
            final String name = (String) obj.get("vechName");
            final TextView active_booking = (TextView) findViewById(R.id.TV_vech_name);
            Log.d("Yourtag", name);

            // When pressed on Booked Vehicle, the Alert Dialog will pop up to confirm booking.
            AlertDialog.Builder builder = new AlertDialog.Builder(UserProfileActivity.this);
            builder.setMessage("Accept this booking: " + name)
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Log.i("tag", "Accepted");
                            active_booking.setBackgroundColor(getResources().getColor(R.color.sigmaColorCyan));
                            active_booking.setTextColor(getResources().getColor(R.color.white));
                            active_booking.setText(name + " is ready! Click to start driving ");
                        }
                        boolean accepted = true;

                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();

                        }

                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    });
}
*/