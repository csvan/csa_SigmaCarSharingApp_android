package sigma.scsapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import sigma.scsapp.booking.BookingActivity;

/**
 * Created by Niklas on 2017-10-06.
 */

public class NavigationActivity extends AppCompatActivity
    {

        public void StartLog(View v) {
        Intent intent = new Intent (this, LogActivity.class);
        startActivity(intent);
        }

        public void StartBooking (View v) {
        Intent intent = new Intent (this, BookingActivity.class);
        startActivity(intent);

        }

        public void StartMap(View v) {
       // Intent intent = new Intent (this, MapActivity.class);
       // startActivity(intent);
        Log.i("Class", "insertt map Activity");

        }

        public NavigationActivity(View view){

        };
    }
