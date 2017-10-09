package sigma.scsapp.activities;

/**
 * Created by Niklas on 2017-09-28.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;

import sigma.scsapp.R;
import sigma.scsapp.model.Booking;

public class DetailActivity extends AppCompatActivity {

    private TextView tvId;
    private TextView tvTimeOfBooking;
    private TextView tvStartDate;
    private TextView tvStartTime;
    private TextView tvEndDate;
    private TextView tvEndTime;
    private TextView tvIsConfirmed;
    private TextView tvErrand;
    private TextView tvDestination;
    private TextView tvPurpose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.bookingform_detail);

    // Showing and Enabling clicks on the Home/Up button
    if(getSupportActionBar() != null) {
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    // setting up text views and stuff
    setUpUIViews();

    // recovering data from MainActivity, sent via intent
    Bundle bundle = getIntent().getExtras();
    if(bundle != null){
    // TODO: 2017-09-29 Kolla vad booking är i Main activity
    String bundleJsonFromBookingKey = bundle.getString("bookingkey");
    Log.i("DetailActivity Log", "Printing out BundleJsonFromBookingKey" + bundleJsonFromBookingKey);
    // getting the model from MainActivity send via extras
    Booking booking = new Gson().fromJson(bundleJsonFromBookingKey, Booking.class);

   // tvId.setText(booking.getId() + "");
    tvTimeOfBooking.setText(booking.getTimeOfBooking() + "");
    tvStartDate.setText(booking.getStartingDate()+ "");
    tvStartTime.setText(booking.getStartingTime()+ "");
    tvEndDate.setText(booking.getEndingDate()+ "");
    tvEndTime.setText(booking.getEndingTime() +"");
    tvIsConfirmed.setText(booking.getIsConfirmed()+ "");
    tvErrand.setText(booking.getErrand()+ "");
    tvDestination.setText(booking.getDestination()+"");
    tvPurpose.setText(booking.getPurpose()+"");


    }

    }
    private void setUpUIViews() {
    // tvId = (TextView)findViewById(R.id.tvId);
    tvTimeOfBooking = (TextView)findViewById(R.id.tvTimeOfBooking);
    tvStartDate = (TextView)findViewById(R.id.tvStartDate);
    tvEndDate = (TextView)findViewById(R.id.tvEndDate);
    tvStartTime = (TextView)findViewById(R.id.tvStartTime);
    tvEndTime = (TextView)findViewById(R.id.tvEndTime);
    tvErrand = (TextView)findViewById(R.id.tvErrand);
    tvDestination = (TextView)findViewById(R.id.tvDestination);
    tvPurpose = (TextView)findViewById(R.id.tvPurpose);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    if (id == android.R.id.home) {
    finish();
    }

    return super.onOptionsItemSelected(item);
    }

}
