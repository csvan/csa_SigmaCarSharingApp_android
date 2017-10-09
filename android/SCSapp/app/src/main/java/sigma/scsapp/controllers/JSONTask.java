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


// BookingString ska vara generisk klass eftersom man ska skicka med vilken typ av Model man vill ha en lista utav.
public class JSONTask extends AsyncTask<String, String, List<BookingString>>
    {
        public AsyncResponse delegate = null;

        // Class T förstår jag inte riktigt hur jag ska kunna "set" till en klass man skickar med.
        public static String URL_TO_HIT = "http://10.0.2.2/servertest.txt";
        public String fieldToFind = "bookings";
        public Class<T>;

        // Det är här jag inte vet hur jag ska göra. Hur ska jag kunna skicka med "BookingString.class" som en parameter och att sedan den används överallt?
        public JSONTask(String URL_TO_HIT, String fieldToFind, Class <>)
            {
            this.URL_TO_HIT = URL_TO_HIT;
            this.fieldToFind = fieldToFind;
            this.<T> = <T>;
            }

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
                JSONArray parentArray = parentObject.getJSONArray(fieldToFind);

                Log.i("JSONTask", "Trying to fetch Array from Object with param: " + parentArray);
                List<BookingString> bookingList = new ArrayList<>();

                Gson gson = new Gson();
                for (int i = 0; i < parentArray.length(); i++)
                    {
                    long id = 1;
                    JSONObject finalobject = parentArray.getJSONObject(i);
                    BookingString bookingGson = gson.fromJson(finalobject.toString(), BookingString.class); //  Här ska BookingString.class vara hämtat från konstruktorn.
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
        protected  void onPostExecute(final List<BookingString> result) {
        Log.i("OnPostExec", "result from OnPostExec" + result);
        delegate.processFinish(result);

        }
       /* @Override
        protected void onPostExecute(final List<BookingString> result)
            {
            onPostExecute(result);
            Log.i("OnPostExecute", " Trying to finish up with Row into the List with result: " + result);
            dialog.dismiss();
            if (result != null)
                {
                // the Adapter takes the Row-Layout, inserting the result into it.
                BookingAdapter adapter = new BookingAdapter(activity, R.layout.bookingform_row, result);
                // the ListView (lvBooking) takes the adapter, in this case the Row (with the result) and add it into the ListView.
                lvBookings.setAdapter(adapter);
                lvBookings.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {  // list item click opens a new detailed activity
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                            {
                            BookingString booking = result.get(position); // getting the model
                            Intent intent = new Intent(activity, DetailActivity.class);
                            //intent.putExtra("bookingkey", new Gson().toJson(booking)); // converting model json into string type and sending it via intent
                            startActivity(intent);
                            }
                    });
                } else
                {
                Toast.makeText(activity, "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
                }

            }*/
    }
