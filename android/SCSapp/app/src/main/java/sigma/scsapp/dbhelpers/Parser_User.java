package sigma.scsapp.dbhelpers;


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

import sigma.scsapp.activities.LogActivity;
import sigma.scsapp.R;
import sigma.scsapp.remote.request;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


// This class will Get students and Parse them with Json.
public class Parser_User extends AppCompatActivity {


    // URL to get contacts JSON
    private static String url = "http://http://0.0.0.0:8000/testdb.json";

    ArrayList<HashMap<String, String>> userList;
    private String TAG = Parser_User.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Maybe set to empty layout?
        setContentView(R.layout.login_activityview);


        // Create a User
        userList = new ArrayList<>();
        // Generate the Listview with layout
        lv = (ListView) findViewById(R.id.LV_list);
        // Create new Listview and call the GetCar method.
        new GetCar().execute();



        /* Depening on what user-name you click, you will send the car-name with "posit"
        into the Main2Activity (courses) that checks the student ID for his/her courses.
         */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "You clicked on user with name: " + (position + 1));
                Intent intent = new Intent(Parser_User.this, LogActivity.class);

                // post save the ID of student instead of the "position" from the array.
                int post = (position + 1);
                String postString = String.valueOf(post);

                // Sending the Student ID as postString into the the next activity
                intent.putExtra(EXTRA_MESSAGE, postString);
                startActivity(intent);
            }
        });

    }


    // AsyncTask to deal with the datafetching etc.
    private class GetCar extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Visar processdialog för main activity!
            pDialog = new ProgressDialog(Parser_User.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        // create a method that return the data from database and parse it to json.
        @Override
        protected Void doInBackground(Void... arg0) {
            request ServerCon = new request();

            // Making a request to url and getting response
            String jsonStr = ServerCon.requestURL(url);
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Request JSON object/array/list
                    JSONArray user = jsonObj.getJSONArray("userName");

                    // loop all students
                    for (int i = 0; i < user.length(); i++) {
                        JSONObject users = user.getJSONObject(i);

                        // For each user, put it into a hashmap.
                        // adding each child node to HashMap key => value
                        String name = users.getString("userName");
                        HashMap<String, String> studentHashMap = new HashMap<>();
                        studentHashMap.put("userName", name);

                        // adding the Hashmap into an Arraylist (studentlist)
                        userList.add(studentHashMap);
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
                Log.e(TAG, "Couldn't get json(user) from server.");
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
            ListAdapter adapter = new SimpleAdapter(Parser_User.this, userList, R.layout.list_item_vehicle,
                    new String[]{"userName"},
                    new int[]{R.id.userName});

            lv.setAdapter(adapter);
        }

    }
}

