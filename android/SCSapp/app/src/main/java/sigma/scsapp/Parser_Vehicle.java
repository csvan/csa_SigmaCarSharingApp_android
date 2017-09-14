package sigma.scsapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


// This class will Get students and Parse them with Json.
public class Parser_Vehicle extends AppCompatActivity {


    // URL to get contacts JSON
    public static String url = "http://echo.jsontest.com/vechName/volvo/one/two";
    ArrayList<HashMap<String, String>> VehicleList;
    private String TAG = Parser_Vehicle.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_of_booking);
        Log.i("Tag", "Starting Parser-vech activity");

        // SETUP
        // Create a userList
        VehicleList = new ArrayList<>();

        // Generate the Listview with layout
        // Using the layout User_list_of_booking and Res listofBooking.
        lv = (ListView) findViewById(R.id.LV_listOfBooking);

        // Create new Listview and call the GetVehicle method.
        new GetVehicle().execute();



        /* Depening on what Car id you click, you will send the car ID with "posit"
        into the Main2Activity (courses) that checks the car ID for it's info.
         */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "You clicked on car with id: " + (position + 1));
                // TODO Generate and create the User_Model class with information about the car etc. This will show all the info for the car.
           //     Intent intent = new Intent(Parser_Vehicle.this, Car_info.class);

                // post save the ID of student instead of the "position" from the array.
                int post = (position + 1);
                String postString = String.valueOf(post);

                // Sending the vehicle id as postString into the the next activity
              //  intent.putExtra(EXTRA_MESSAGE, postString);
              //  startActivity(intent);
            }
        });

    }


    // AsyncTask to deal with the datafetching etc.
    private class GetVehicle extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Visar processdialog för main activity!
            pDialog = new ProgressDialog(Parser_Vehicle.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        // create a method that return the data from database and parse it to json.
        @Override
        protected Void doInBackground(Void... arg0) {
            Server_Connection ServerCon = new Server_Connection();
            Log.e(TAG, "Response before json on URL:" + url);
            // Making a request to url and getting response
            String jsonStr = ServerCon.requestURL(url);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);


                    // Request JSON object/array/list
                    JSONArray vehicle = jsonObj.getJSONArray("one");
                    Log.i(TAG, "value in -one- " + vehicle);


                    // loop all vehicles
                    for (int i = 0; i < vehicle.length(); i++) {
                        JSONObject vehicles = vehicle.getJSONObject(i);

                        // TODO Change vehiclesname and vehiclesid into the correct JSon title from the DB
                       // String id = vehicles.getString("vehicleId");
                        String name = vehicles.getString("vechName");


                        // For each vehicle, put it into a hashmap.
                        HashMap<String, String> VehicleHashMap = new HashMap<>();

                        // adding each child node to HashMap key => value
                      //  VehicleHashMap.put("vehicleId", id);
                        VehicleHashMap.put("vechName", name);

                        // adding the Hashmap into an Arraylist (studentlist)
                        VehicleList.add(VehicleHashMap);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    Log.e(TAG, "Error in Main Activity");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json(student) from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server.",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Dismiss the progress dialog
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
// TODO: 2017-09-12 Depening on what the server got for CarName/CarID VechName/VechId
            // Updating parsed JSON data into ListView
            // Inser the info into the list_item_vehicle and put it into the Log_activity
            ListAdapter adapter = new SimpleAdapter(Parser_Vehicle.this, VehicleList, R.layout.list_item_vehicle,
                    new String[]{"vechName"},
                    new int[]{R.id.TV_vech_name});

            lv.setAdapter(adapter);
        }

    }
}

