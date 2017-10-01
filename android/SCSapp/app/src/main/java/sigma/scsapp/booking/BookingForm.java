package sigma.scsapp.booking;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import sigma.scsapp.R;
import sigma.scsapp.model.Booking;
import sigma.scsapp.booking.TimePickerFragment;
import sigma.scsapp.booking.*;


public class BookingForm extends FragmentActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private final String URL_TO_HIT = "https://10.0.2.2:8000/servertext.txt";
    private TextView tvData;
    private ListView lvBookings;
    private ProgressDialog dialog;
    TimePickerFragment timepickerfrag;

    // Git error fix - http://stackoverflow.com/questions/16614410/android-studio-checkout-github-error-createprocess-2-windows

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.bookingform_list);

    dialog = new ProgressDialog(this);
    dialog.setIndeterminate(true);
    dialog.setCancelable(false);
    dialog.setMessage("Loading. Please wait..."); // showing a dialog for loading the data
    // Create default options which will be used for every
    //  displayImage(...) call if no options will be passed to this method
    DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .build();
    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
            .defaultDisplayImageOptions(defaultOptions)
            .build();
    ImageLoader.getInstance().init(config); // Do it on Application start

    lvBookings = (ListView)findViewById(R.id.lvBooking);


    // To start fetching the data when app start, uncomment below line to start the async task.
    new JSONTask().execute(URL_TO_HIT);
    }


    public class JSONTask extends AsyncTask<String,String, List<Booking> >
        {

            @Override
            protected void onPreExecute() {
            Log.i("JSONTask","Start the JSONTask with url: " + URL_TO_HIT);
            super.onPreExecute();
            dialog.show();
            }

            @Override
            protected List<Booking> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            Log.i("JSONTask", "Will try connect to URL ...");

            try {
            URL url = new URL(params[0]);
            Log.i("JSONTask on Background","...Url: " +url);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            Log.i("JSONTask", "Still trying to connect ... ");
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line ="";
            while ((line = reader.readLine()) != null){
            buffer.append(line);
            }

            String finalJson = buffer.toString();
            Log.i("JSONTask", "Result from URL connection: " + finalJson);

            JSONObject parentObject = new JSONObject(finalJson);
            // Hämtar "bookings"
            JSONArray parentArray = parentObject.getJSONArray("bookings");
            Log.i("JSONTask","Trying to fetch Array from Object with param: "+ parentArray);
            List<Booking> BookingList = new ArrayList<>();

            Gson gson = new Gson();
            for(int i=0; i<parentArray.length(); i++) {
            JSONObject finalObject = parentArray.getJSONObject(i);

            // Gson take FromJson, the finalObject into String and "parse" it with Booking.class.
            Booking booking = gson.fromJson(finalObject.toString(), Booking.class); // a single line json parsing using Gson

            // adding the final object in the list
            BookingList.add(booking);
            }
            Log.i("JSONTask","Returning the List from JSONtask");
            return BookingList;

            } catch (MalformedURLException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
            } catch (JSONException e) {
            e.printStackTrace();
            } finally {
            if(connection != null) {
            connection.disconnect();
            }
            try {
            if(reader != null) {
            reader.close();
            }
            } catch (IOException e) {
            e.printStackTrace();
            }
            }
            return  null;
            }

            @Override
            protected void onPostExecute(final List<Booking> result) {
            super.onPostExecute(result);
            Log.i("OnPostExecute"," Trying to finish up with Row into the List");
            dialog.dismiss();
            if(result != null) {
            // the Adapter takes the Row-Layout, inserting the result into it.
            BookingAdapter adapter = new BookingAdapter(getApplicationContext(), R.layout.bookingform_row, result);
            // the ListView (lvBooking) takes the adapter, in this case the Row (with the result) and add it into the ListView.
            lvBookings.setAdapter(adapter);
            lvBookings.setOnItemClickListener(new AdapterView.OnItemClickListener() {  // list item click opens a new detailed activity
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Booking booking = result.get(position); // getting the model
                Intent intent = new Intent(BookingForm.this, DetailActivity.class);
                Log.i("OnPost in OnItemClick","Trying to send the Gson.toJson into PutExtra");
                intent.putExtra("booking", new Gson().toJson(booking)); // converting model json into string type and sending it via intent
                startActivity(intent);
                }
            });
            } else {
            Toast.makeText(getApplicationContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }
            }
        }



    public class BookingAdapter extends ArrayAdapter
        {

            private List<Booking> bookingList;
            private int resource;
            private LayoutInflater inflater;
            public BookingAdapter(Context context, int resource, List<Booking> objects) {
            super(context, resource, objects);
            bookingList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
            Log.i("BookingAdapter", "Starting the BookingAdapter");

            ViewHolder holder = null;
            if(convertView == null){
            Log.i("BookingAdapter", "Filling the TV for the first time");

            holder = new ViewHolder();
            convertView = inflater.inflate(resource, null);
            //holder.ivBookingImage = (ImageView)convertView.findViewById(R.id.ivIcon);
            holder.tvErrand = (TextView)convertView.findViewById(R.id.tvErrand);
            holder.tvDestination = (TextView)convertView.findViewById(R.id.tvDestination);
            holder.tvPurpose = (TextView)convertView.findViewById(R.id.tvPurpose);

            holder.tvStartDate = (TextView)convertView.findViewById(R.id.tvDate);
            holder.tvEndDate = (TextView)convertView.findViewById(R.id.tvStartTime);

            holder.tvStartTime = (TextView)convertView.findViewById(R.id.tvCast);
            holder.tvEndTime = (TextView)convertView.findViewById(R.id.tvStory);
            convertView.setTag(holder);

            } else {
            holder = (ViewHolder) convertView.getTag();
            }

            //final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressBar);

            // Then later, when you want to display image
            /*final ViewHolder finalHolder = holder;
            ImageLoader.getInstance().displayImage(bookingList.get(position).getImage(), holder.ivBookingImage, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
                finalHolder.ivBookingImage.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progressBar.setVisibility(View.GONE);
                finalHolder.ivBookingImage.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(View.GONE);
                finalHolder.ivBookingImage.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                progressBar.setVisibility(View.GONE);
                finalHolder.ivBookingImage.setVisibility(View.INVISIBLE);
                }
            });*/

            holder.tvErrand.setText(bookingList.get(position).getErrand());
            holder.tvDestination.setText(bookingList.get(position).getDestination());
            holder.tvStartTime.setText("Start Time: " + bookingList.get(position).getStartingTime());
            holder.tvEndTime.setText("End Time: " + bookingList.get(position).getEndingTime());
            holder.tvStartDate.setText("StartDate: " + bookingList.get(position).getStartingDate());
            holder.tvEndDate.setText("EndDate:" + bookingList.get(position).getEndingDate());
            holder.tvPurpose.setText("Purpose:" + bookingList.get(position).getPurpose());

            // rating bar
            //holder.rbMovieRating.setRating(bookingList.get(position).getRating()/2);

           /* StringBuffer stringBuffer = new StringBuffer();
            for(Booking. cast : bookingList.get(position).getCastList()){
            stringBuffer.append(cast.getName() + ", ");
            }

            holder.tvStartTime.setText("Cast:" + stringBuffer);
            holder.tvEndTime.setText(bookingList.get(position).getStory());*/
            return convertView;
            }


            class ViewHolder{

                private TextView tvErrand;
                private TextView tvDestination;
                private TextView tvPurpose;

                private TextView tvStartDate;
                private TextView tvEndDate;

                private TextView tvStartTime;
                private TextView tvEndTime;
            }

        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    Log.i("OnCreateOption", "Clickable menu");

    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_confirm_booking, menu);
    return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
    new JSONTask().execute(URL_TO_HIT);
    return true;
    }

    return super.onOptionsItemSelected(item);
    }


    // TIME START AND END
    public void showTimePickerDialogStart(View v) {
    Log.i("Tag Time", "Clicked on Start Time");
    DialogFragment newFragment = new StartTimePickerFragment();
    newFragment.show(getFragmentManager(), "timePickerStart");
    }
    public void showTimePickerDialogEnd(View v) {
    Log.i("Tag Time", "Clicked on End Time");
    DialogFragment newFragment = new EndTimePickerFragment();
    newFragment.show(getFragmentManager(), "timePickerEnd");
    }

    // DATE START AND END
    public void showDatePickerDialogStart(View v) {
    Log.i("Tag Date", "Clicked on Start Date");
    DialogFragment newFragment = new StartDatePickerFragment();
    newFragment.show(getFragmentManager(), "datePickerStart");
    }
    public void showDatePickerDialogEnd(View v) {
    Log.i("Tag Date", "Clicked on End Date");
    DialogFragment newFragment = new EndDatePickerFragment();
    newFragment.show(getFragmentManager(), "datePickerEnd");
    }



    @Override
    public void onDateSet(DatePicker view, int y, int m, int d) {
    // Kan skicka in y,m,d i Cal direkt.
    Log.i("Tag onDateSet ", "OnDateSet activated in the BookingForm");
    int year = y;
    int month = m;
    int day = d;
    Calendar cal = new GregorianCalendar(year, month, day);
    setDate(cal);
    }

    private void setDate(final Calendar calendar){
    final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
    ((TextView) findViewById(R.id.tv_bookingform_set_date_start)).setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    public void onTimeSet(TimePicker view, int i, int i1) {
    // TODO: 2017-09-25 Fixa en if-sats checkpoint för knapparna (starttime och endtime)
    Log.i("Tag on Booking", "onTimeSet activated in the BookingForm");
    int hour = view.getHour();
    int min = view.getMinute();
    setTime(hour, min);

    }

    public void setTime(int hour, int min){
    final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
    int hour2 = hour;
    int min2 = min;
    ((TextView) findViewById(R.id.tv_bookingform_set_time_start)).setText(hour2 +" : " + min2);
    }

}



