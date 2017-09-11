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
public class Car_Model extends AppCompatActivity {


    // URL to get contacts JSON
    private static String url = "http://10.0.2.2:8080/studentServlet?format=json&id=all";
    ArrayList<HashMap<String, String>> CarList;
    private String TAG = Car_Model.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // SETUP
        // Create a studentList
        CarList = new ArrayList<>();

        // Generate the Listview with layout
        lv = (ListView) findViewById(R.id.list);

        // Create new Listview and call the GetCar method.
        new GetCar().execute();



        /* Depening on what Car id you click, you will send the car ID with "posit"
        into the Main2Activity (courses) that checks the car ID for it's info.
         */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "You clicked on car with id: " + (position + 1));
                // TODO Generate and create the Car_Info class with information about the car etc. This will show all the info for the car.
           //     Intent intent = new Intent(Car_Model.this, Car_info.class);

                // post save the ID of student instead of the "position" from the array.
                int post = (position + 1);
                String postString = String.valueOf(post);

                // Sending the Student ID as postString into the the next activity
              //  intent.putExtra(EXTRA_MESSAGE, postString);
              //  startActivity(intent);
            }
        });

    }


    // AsyncTask to deal with the datafetching etc.
    private class GetCar extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Visar processdialog för main activity!
            pDialog = new ProgressDialog(Car_Model.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        // create a method that return the data from database and parse it to json.
        @Override
        protected Void doInBackground(Void... arg0) {
            Server_Connection HH = new Server_Connection();

            // Making a request to url and getting response
            String jsonStr = HH.requestURL(url);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Request JSON object/array/list
                   // JSONArray student = jsonObj.getJSONArray("students");
                    JSONArray car = jsonObj.getJSONArray("car");


                    // loop all cars
                    for (int i = 0; i < car.length(); i++) {
                        JSONObject cars = car.getJSONObject(i);

                        // TODO Change CarID and CarName into the correct JSon title from the DB
                        String id = cars.getString("CarID");
                        String name = cars.getString("CarName");


                        // For each car, put it into a hashmap.
                        HashMap<String, String> CarHashMap = new HashMap<>();

                        // adding each child node to HashMap key => value
                        CarHashMap.put("CarId", id);
                        CarHashMap.put("CarName", name);

                        // adding the Hashmap into an Arraylist (studentlist)
                        CarList.add(CarHashMap);
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

            // Updating parsed JSON data into ListView
            ListAdapter adapter = new SimpleAdapter(Car_Model.this, CarList, R.layout.list_item_car,
                    new String[]{"CarName", "CarID"},
                    new int[]{R.id.CarName, R.id.CarID});

            lv.setAdapter(adapter);
        }

    }
}

