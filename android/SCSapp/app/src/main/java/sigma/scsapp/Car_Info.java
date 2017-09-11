package sigma.scsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class Car_Info extends AppCompatActivity {

    ArrayList<HashMap<String, String>> studentList;
    private String TAG = Car_Info.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    // URL för specifik sudentID
    // Måste spara studentID på mainA och sedan skicka hit.
    private String hamtaIDString;
    private String url = "http://10.0.2.2:8080/studentServlet?format=json&id=";


    // Getters and Setters for the ID position, couldn't find an easier way cause i'm lazy
    public String getHamtaIDString() {
        return hamtaIDString;
    }

    public void setHamtaIDString(String hamtaIDString) {
        this.hamtaIDString = hamtaIDString;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Not sure if Layout on both Car_Model and Info will make this crash. Perhaps create an empty layout as default.
        setContentView(R.layout.activity_login);

        // Here we fetch the intent from the StudentActivity and also fetching the data that was sent. Also send a LOG.
        Intent intent = getIntent();
        setHamtaIDString(intent.getStringExtra(EXTRA_MESSAGE));
        Log.e(TAG, "Hamta ID har:" + hamtaIDString);

        studentList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        // Skapar nya listan och exec
        new GetStudent().execute();
    }


    // SKAPA EGEN KLASS YAO ?
    private class GetStudent extends AsyncTask<Void, Void, Void> {

        // This will judge if it's true that the List will return value or not futher down.
        boolean FoundInfo;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Visar processdialog för main activity!
            pDialog = new ProgressDialog(Car_Info.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        // skapa en bakgrunds-thread
        @Override
        protected Void doInBackground(Void... arg0) {

            // Create a Http worker
            Server_Connection HH = new Server_Connection();

            // Making a request to url and getting response.
            // The URL is the same from MainActivity but this time we add the Data from Main into the Query. instead of "all" we now have the "getHamtaIdString".
            String jsonStr = HH.requestURL(url + getHamtaIDString());
            Log.e(TAG, "Response from url: " + jsonStr);

            // If the respons from the URL is filled with Data (bigger length then 1) then the "if" will run. Otherwise the "else" will run with no value added.
            // Not sure if the "else" needs to return a hashmap with no value or not.
            if (jsonStr.length() > 1) {
                FoundInfo = true;
                Log.e(TAG, "Initializing json-converting...");

                try {
                    Log.e(TAG, "Starting converting to json");
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray courses = jsonObj.getJSONArray("courses");

                    // Loop through all courses avaliable
                    for (int i = 0; i < courses.length(); i++) {
                        Log.e(TAG, "At position: " + i);
                        JSONObject stud = courses.getJSONObject(i);
                        String id = stud.getString("courseID");
                        String course = stud.getString("CourseCode");

                        // Save result into a HashMap
                        HashMap<String, String> coursesHashMap = new HashMap<>();

                        // adding each child node to HashMap key => value
                        coursesHashMap.put("CourseID", id);
                        coursesHashMap.put("CourseCode", course);

                        // adding contact to contact list
                        studentList.add(coursesHashMap);
                    }
                    Log.e(TAG, "FINISHED!");
                } catch (final JSONException e) { // final


                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    Log.e(TAG, "Error in Main2Activity");

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
                FoundInfo = false;
                Log.e(TAG, "Else return a new HashMap with no Course value...");
                HashMap<String, String> NocoursesHashMap = new HashMap<>();
                NocoursesHashMap.put("No courses", "No courses found");
                studentList.add(NocoursesHashMap);

                Log.e(TAG, "Couldn't get json(courses) from server.");
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
            if (pDialog.isShowing())
                pDialog.dismiss();


            // Depending on Hashmap, the following if-else will run and return the Courses (or not).
            if (FoundInfo == true) {
                ListAdapter adapter = new SimpleAdapter(
                        Car_Info.this, studentList,
                        R.layout.list_item_car,
                        new String[]{"CarID", "CarInfo"},
                        new int[]{R.id.CarID, R.id.CarInfo});

                lv.setAdapter(adapter);
            } else {

                ListAdapter adapter2 = new SimpleAdapter(
                        Car_Info.this, studentList,
                        R.layout.activity_login,
                        new String[]{"No courses"},
                        new int[]{R.id.CarInfo});
                lv.setAdapter(adapter2);
            }

        }

    }
}

