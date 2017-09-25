package sigma.scsapp.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.util.ArrayList;
import java.util.List;

import sigma.scsapp.activities.UserProfileActivity;
import sigma.scsapp.model.Booking;
import sigma.scsapp.R;

public class Controller_Confirm_Booking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Accepted terms", "You accept the terms of coffee");
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
                startActivity(new Intent(Controller_Confirm_Booking.this,UserProfileActivity.class));

            }
        });

        /*final Switch accept_car_switch;




       /* INSERT A SWITCH TO ACCEPT CAR
       accept_car_switch = (Switch) findViewById(R.id.accept_car_switch);
        accept_car_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Snackbar.make(view, "You accepted the terms!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });
*/
        SubsamplingScaleImageView imageView = (SubsamplingScaleImageView)findViewById(R.id.SSImageView);
        imageView.setImage(ImageSource.resource(R.drawable.conditionreport));
/* ... or ...
        imageView.setImage(ImageSource.asset("map.png"))
// ... or ...
        imageView.setImage(ImageSource.uri("/sdcard/DCIM/DSCM00123.JPG"));*/


    }

    protected List<Booking> getBookings(Long userName) {
        List<Booking> listOfBooking = new ArrayList<>();
        // TODO: 2017-09-12 Hämta alla bookings för UserID
        // TODO: 2017-09-12 If (return null) { print no bookings found };

        return listOfBooking;


    }
}
