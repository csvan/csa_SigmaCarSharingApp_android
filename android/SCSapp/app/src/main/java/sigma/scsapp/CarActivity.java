package sigma.scsapp;

import android.app.ProgressDialog;
import android.content.Intent;
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

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class CarActivity extends AppCompatActivity {


    // URL to get contacts JSON
    private static String url = "";

    ArrayList<HashMap<String, String>> CarList;
    private String TAG = CarActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO Set to another view with list
     //   setContentView(R.layout.activity_main3);


        // Create a CarList
        CarList = new ArrayList<>();

        // Generate the Listview with layout
        // TODO setup the list with cars
      //  lv = (ListView) findViewById(R.id.list);
        // Create new Listview and call the GetCar method.
        new GetCar().execute();



        /* Depening on what Student id you click, you will send the student ID with "posit"
        into the Main2Activity (courses) that checks the student ID for his/her courses.
         */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "You clicked on student with id: " + (position + 1));
                // TODO Click Car to get car information
               // Intent intent = new Intent(CarActivity.this, CarInfoActivity.class);

                // post save the ID of student instead of the "position" from the array.
                int post = (position + 1);
                String postString = String.valueOf(post);

                // Sending the Student ID as postString into the the next activity
                // TODO Fix so Clicked Car will show Car Info instead
                //intent.putExtra(EXTRA_MESSAGE, postString);
                //startActivity(intent);
            }
        });

    }


    // AsyncTask to deal with the datafetching etc.
    private class GetCar extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Visar processdialog för main activity!
            pDialog = new ProgressDialog(CarActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        // create a method that return the data from database and parse it to json.
        @Override
        protected Void doInBackground(Void... arg0) {
            Rest rest = new Rest();

            // Making a request to url and getting response
            String jsonStr = rest.requestURL(url);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Request JSON object/array/list
                    JSONArray student = jsonObj.getJSONArray("students");

                    // loop all students
                    for (int i = 0; i < student.length(); i++) {
                        JSONObject stud = student.getJSONObject(i);

                        String id = stud.getString("studentID");
                        String name = stud.getString("studentName");


                        // For each students, put it into a hashmap.
                        HashMap<String, String> studentHashMap = new HashMap<>();

                        // adding each child node to HashMap key => value
                        studentHashMap.put("studentID", id);
                        studentHashMap.put("studentName", name);

                        // adding the Hashmap into an Arraylist (studentlist)
                        CarList.add(studentHashMap);
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
           /* ListAdapter adapter = new SimpleAdapter(CarActivity.this, CarList, R.layout.list_item,
                    new String[]{"studentName", "studentID"},
                    new int[]{R.id.studentName, R.id.studentID});

            lv.setAdapter(adapter);*/
        }

    }
}

