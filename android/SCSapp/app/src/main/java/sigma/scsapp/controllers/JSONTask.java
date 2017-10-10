package sigma.scsapp.controllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

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
import java.util.List;

import sigma.scsapp.R;
import sigma.scsapp.activities.DetailActivity;
import sigma.scsapp.model.BookingString;
import sigma.scsapp.utility.AsyncResponse;

/**
 * Created by Niklas on 2017-10-09.
 */

public class JSONTask extends AsyncTask<String, String, List<BookingString>>
    {
        public AsyncResponse delegate = null;

        private final static String URL_TO_HIT = "";
        private ProgressDialog dialog;
        private ListView lvBookings;
        public Activity activity;


        @Override
        protected void onPreExecute()
            {
            Log.i("JSONTask", "Start the JSONTask with url: " + URL_TO_HIT);
            super.onPreExecute();
//            dialog.show();
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
        public List<BookingString> getJSONArray() {
        return doInBackground();
        }

        @Override
        protected  void onPostExecute(final List<BookingString> result) {
        Log.i("OnPostExec", "result from OnPostExec" + result);
        delegate.processFinish(result);

        }
    }
