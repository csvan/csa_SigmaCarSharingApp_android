package sigma.scsapp.booking;

/**
 * Created by Niklas on 2017-09-28.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;

import sigma.scsapp.R;
import sigma.scsapp.model.Booking;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivVechileImage;
    private TextView tvErrand;
    private TextView tvDestination;
    private TextView tvStartDate;
    private TextView tvEndDate;
    private TextView tvStartTime;
    private TextView tvEndTime;
    private TextView tvPurpose;
    private RatingBar rbMovieRating;
    private TextView tvCast;
    private TextView tvStory;
    private ProgressBar progressBar;

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
    String json = bundle.getString("booking"); // getting the model from MainActivity send via extras
    Booking booking = new Gson().fromJson(json, Booking.class);

    // Then later, when you want to display image

    /*ImageLoader.getInstance().displayImage(booking.getImage(), ivVechileImage, new ImageLoadingListener() {
        @Override
        public void onLoadingStarted(String imageUri, View view) {
        progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
        progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onLoadingCancelled(String imageUri, View view) {
        progressBar.setVisibility(View.GONE);
        }
    });*/

    tvErrand.setText(booking.getErrand());
    tvDestination.setText(booking.getDestination());
    tvPurpose.setText("Purpose:" + booking.getPurpose());
    tvStartDate.setText("Date from: " + booking.getStartingDate());
    tvEndDate.setText(" to "+ booking.getEndingDate());
    tvStartTime.setText("Time from:" + booking.getStartingTime());
    tvEndTime.setText("to " + booking.getEndingTime() +".");

    // rating bar
    //rbMovieRating.setRating(booking.getRating() / 2);

    /*StringBuffer stringBuffer = new StringBuffer();
    for(Booking.Cast cast : booking.get()){
    stringBuffer.append(cast.getName() + ", ");
    }*/

   /* tvCast.setText("Cast:" + stringBuffer);*/
    /* tvStory.setText(booking.***);*/

    }

    }

    private void setUpUIViews() {
    //ivVechileImage = (ImageView)findViewById(R.id.ivIcon);
    tvErrand = (TextView)findViewById(R.id.tvErrand);
    tvDestination = (TextView)findViewById(R.id.tvDestination);

    tvStartDate = (TextView)findViewById(R.id.tvDate);
    tvEndDate = (TextView)findViewById(R.id.tvEndDate);

    tvStartTime = (TextView)findViewById(R.id.tvStartTime);
    tvEndTime = (TextView)findViewById(R.id.tvEndTime);

    tvPurpose = (TextView)findViewById(R.id.tvPurpose);
    //rbMovieRating = (RatingBar)findViewById(R.id.rb);
    //tvCast = (TextView)findViewById(R.id.tvCast);
    //tvStory = (TextView)findViewById(R.id.tvStory);
    progressBar = (ProgressBar)findViewById(R.id.progressBar);
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
