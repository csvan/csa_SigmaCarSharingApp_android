package sigma.scsapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sigma.scsapp.R;
import sigma.scsapp.booking.BookingForm;
import sigma.scsapp.booking.DetailActivity;
import sigma.scsapp.booking.TimePickerFragment;
import sigma.scsapp.model.Booking;
import sigma.scsapp.model.BookingString;

/**
 * Created by Niklas on 2017-10-03.
 */

public class MainActivity extends ActionBarActivity
    {
        private final String URL_TO_HIT = "http://10.0.2.2:8000/servertest.json";
        private TextView tvData;
        private ListView lvBookings;
        private ProgressDialog dialog;
        TimePickerFragment timepickerfrag;

        // Git error fix - http://stackoverflow.com/questions/16614410/android-studio-checkout-github-error-createprocess-2-windows

        @Override
        protected void onCreate(Bundle savedInstanceState)
            {
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

            lvBookings = (ListView) findViewById(R.id.lvBooking);


            // To start fetching the data when app start, uncomment below line to start the async task.
            new JSONTask().execute(URL_TO_HIT);
            }


        public class JSONTask extends AsyncTask<String, String, List<BookingString>>
            {

                @Override
                protected void onPreExecute()
                    {
                    Log.i("JSONTask", "Start the JSONTask with url: " + URL_TO_HIT);
                    super.onPreExecute();
                    dialog.show();
                    }

                @Override
                protected List<BookingString> doInBackground(String... params)
                    {
                    HttpURLConnection connection = null;
                    BufferedReader reader = null;
                    Log.i("JSONTask", "Will try connect to URL ...");

                    try
                        {
                        URL url = new URL(params[0]);
                        connection = (HttpURLConnection) url.openConnection();
                        connection.connect();
                        Log.i("JSONTask", "Still trying to connect ... ");
                        InputStream stream = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(stream));

                        StringBuffer buffer = new StringBuffer();
                        String line = "";

                        while ((line = reader.readLine()) != null)
                            {
                            buffer.append(line);
                            }

                        String finalJson = buffer.toString();
                        Log.i("JSONTask", "FinalJson is now: " + finalJson);

                        JSONObject parentObject = new JSONObject(finalJson);
                        JSONArray parentArray = parentObject.getJSONArray("bookings");

                        Log.i("JSONTask", "Trying to fetch Array from Object with param: " + parentArray);
                        List<BookingString> bookingList = new ArrayList<>();

                        Gson gson = new Gson();
                        for (int i = 0; i < parentArray.length(); i++)
                            {
                            long id = 1;
                            JSONObject finalobject = parentArray.getJSONObject(i);
                            //Booking bookingtest = new Booking(id, "köpa käk", "destination", "purpose", true );
                            BookingString bookingGson = gson.fromJson(finalobject.toString(), BookingString.class); // a single line json parsing using Gson
                            bookingList.add(bookingGson);
                            Log.i("JSONTask", "Returning the List from JSONtask");

                            }
                        return bookingList;
                        } catch (MalformedURLException e)
                        {
                        e.printStackTrace();
                        } catch (IOException e)
                        {
                        e.printStackTrace();
                        } catch (JSONException e)
                        {
                        e.printStackTrace();
                        } finally
                        {
                        if (connection != null)
                            {
                            connection.disconnect();
                            }
                        try
                            {
                            if (reader != null)
                                {
                                reader.close();
                                }
                            } catch (IOException e)
                            {
                            e.printStackTrace();
                            }
                        }
                    return null;
                    }

                @Override
                protected void onPostExecute(final List<BookingString> result)
                    {
                    super.onPostExecute(result);
                    Log.i("OnPostExecute", " Trying to finish up with Row into the List with result: " + result);
                    dialog.dismiss();
                    if (result != null)
                        {
                        // the Adapter takes the Row-Layout, inserting the result into it.
                        BookingAdapter adapter = new BookingAdapter(getApplicationContext(), R.layout.bookingform_row, result);
                        // the ListView (lvBooking) takes the adapter, in this case the Row (with the result) and add it into the ListView.
                        lvBookings.setAdapter(adapter);
                        lvBookings.setOnItemClickListener(new AdapterView.OnItemClickListener()
                            {  // list item click opens a new detailed activity
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                                    {
                                    BookingString booking = result.get(position); // getting the model
                                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                    Log.i("OnPost in OnItemClick", "Trying to send the Gson.toJson into PutExtra");
                                    intent.putExtra("bookingkey", new Gson().toJson(booking)); // converting model json into string type and sending it via intent
                                    startActivity(intent);
                                    }
                            });
                        } else
                        {
                        Toast.makeText(getApplicationContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
                        }
                    }
            }


        public class BookingAdapter extends ArrayAdapter
            {


                private List<BookingString> bookingList;
                private int resource;
                private LayoutInflater inflater;

                public BookingAdapter(Context context, int resource, List<BookingString> objects)
                    {
                    super(context, resource, objects);
                    bookingList = objects;
                    Log.i("BookingAdapter", "bookingList got info: " + bookingList);
                    this.resource = resource;
                    inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    }

                @Override
                public View getView(int position, View convertView, ViewGroup parent)
                    {
                    Log.i("BookingAdapter", "Starting the BookingAdapter");
                    ViewHolder holder = null;
                    if (convertView == null)
                        {
                        holder = new ViewHolder();
                        convertView = inflater.inflate(resource, null);
                        holder.tvId = (TextView) convertView.findViewById(R.id.tvId);
                        holder.tvTimeOfBooking = (TextView) convertView.findViewById(R.id.tvTimeOfBooking);
                        holder.tvStartDate = (TextView) convertView.findViewById(R.id.tvStartDate);
                        holder.tvStartTime = (TextView) convertView.findViewById(R.id.tvStartTime);
                        holder.tvEndDate = (TextView) convertView.findViewById(R.id.tvEndDate);
                        holder.tvEndTime = (TextView) convertView.findViewById(R.id.tvEndTime);
                        holder.tvIsConfirmed = (TextView) convertView.findViewById(R.id.tvIsConfirmed);
                        holder.tvErrand = (TextView) convertView.findViewById(R.id.tvErrand);
                        holder.tvDestination = (TextView) convertView.findViewById(R.id.tvDestination);
                        holder.tvPurpose = (TextView) convertView.findViewById(R.id.tvPurpose);
                        convertView.setTag(holder);

                        } else
                        {
                        holder = (ViewHolder) convertView.getTag();
                        }
                    holder.tvId.setText("Id" + bookingList.get(position).getId());
                    holder.tvTimeOfBooking.setText("Time of booking" + bookingList.get(position).getTimeOfBooking());
                    holder.tvStartDate.setText("StartDate: " + bookingList.get(position).getStartingDate());
                    holder.tvStartTime.setText("Start Time: " + bookingList.get(position).getStartingTime());
                    holder.tvEndDate.setText("EndDate:" + bookingList.get(position).getEndingDate());
                    holder.tvEndTime.setText("End Time: " + bookingList.get(position).getEndingTime());
                    holder.tvIsConfirmed.setText("Is confirmed? : " + bookingList.get(position).getIsConfirmed());
                    holder.tvErrand.setText("Errand " + bookingList.get(position).getErrand());
                    holder.tvDestination.setText(" Destination : " + bookingList.get(position).getDestination());
                    holder.tvPurpose.setText("Purpose:" + bookingList.get(position).getPurpose());

                    return convertView;

                    }

                class ViewHolder
                    {
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
                    }
            }
        @Override
        public boolean onCreateOptionsMenu(Menu menu)
            {
            Log.i("OnCreateOption", "Clickable menu");

            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_confirm_booking, menu);
            return true;
            }

        @Override
        public boolean onOptionsItemSelected(MenuItem item)
            {
            int id = item.getItemId();
            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings)
                {
                new JSONTask().execute(URL_TO_HIT);
                return true;
                }

            return super.onOptionsItemSelected(item);
            }


    }