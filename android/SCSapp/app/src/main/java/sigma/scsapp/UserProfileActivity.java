package sigma.scsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class UserProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // TODO Visa vilka aktiva bokningar som finns. Visa CardView som är aktuell, annars visa inget.

        // Avaliable text
        ImageView getImage;


        // Button in the menu
        Button booking;
        Button log;
        Button map;
        Button bt_acceptBooking;


        // Card for current car
        CardView user_booking_car;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



        // Listener to the booking button
        booking = (Button) findViewById(R.id.button_booking);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Tag", "You pressed booking ------------------ ");
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();

            }
        });

        // Listener to the Log button
        log = (Button) findViewById(R.id.button_log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Tag", "You pressed your log ------------------ ");
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
                startActivity(new Intent(UserProfileActivity.this,LogActivity.class));

            }
        });







        // TEST TEST  TEST TEST  TEST TEST  TEST TEST  TEST TEST  TEST TEST
        ArrayList<HashMap<String, String>> cars;
        cars = new ArrayList<>();

        final HashMap<String, String> carHashMap = new HashMap<>();
        // adding each child node to HashMap key => value
        carHashMap.put("vechName", "Volvo");
        carHashMap.put("vechName", "Volvo v70");
        carHashMap.put("vechId", "#02");
        carHashMap.put("vechId", "#05");
        // adding the Hashmap into an Arraylist (studentlist)
        cars.add(carHashMap);

        ListAdapter adapter = new SimpleAdapter(UserProfileActivity.this, cars, R.layout.list_item_vehicle,
                new String[]{"vechName"},
                new int[]{R.id.tv_vech_name});

        ListView listofBooking = (ListView) findViewById(R.id.LV_listOfBooking);
        listofBooking.setAdapter(adapter);

        View myLayout = findViewById( R.id.LV_listOfBooking ); // root View id from that link
        ListView myView = myLayout.findViewById( R.id.tv_vech_name ); // id of a view contained in the included file

        myView = (ListView) findViewById(R.id.LV_listOfBooking);

        myView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Tag for Listview", "You clicked on student with id: " + (position + 1));
                // post save the ID of student instead of the "position" from the array.
                int post = (position + 1);
                String postString = String.valueOf(post);
            }
        });



        //  TEST TEST  TEST TEST  TEST TEST  TEST TEST  TEST TEST  TEST TEST

        bt_acceptBooking = (Button) findViewById(R.id.bt_accept_booking);
        bt_acceptBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Tag", "You pressed accept your booking ------------------ ");
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
                startActivity(new Intent(UserProfileActivity.this,Parser_Vehicle.class));

            }
        });

        map = (Button) findViewById(R.id.button_map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Tag", "You pressed your map ------------------ ");
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
                //   startActivity(new Intent(UserProfileActivity.this,Confirm_booking.class));

            }
        });

        // Fix the CardView on the car. Make it "stick" to all the classes.
        // TODO Rename the card_view_2 to something else.

        Button listOfBooking;
        listOfBooking = (Button) findViewById(R.id.bt_listOfBooking);
        listOfBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Tag", "You pressed your List of Bookings ------------------ ");
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
                   startActivity(new Intent(UserProfileActivity.this,Parser_Vehicle.class));

            }
        });


              /*  LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Tag", "You pressed your current booking ------------------ ");
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
                   startActivity(new Intent(UserProfileActivity.this,Parser_Vehicle.class));

            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                Log.i("Tag", "You pressed the naviagion drawer --------------");
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // SET UP PROFILE
        String newString;

        // TODO Generate content based on Database
        if (savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {

                /* TODO: 2017-09-11 Find profile in database and Json it into an object.
                    Create a profile and insert it into the textviews. Place the textviews whereever we need to find a profile-info.
                 */

                Log.i("test", "Setting up profile" );
                TextView profile_userId  = (TextView)findViewById(R.id.text_profile_name);
                profile_userId.setText(getIntent().getStringExtra("mUserIdView"));

                // TextView profile_userName = (TextView)findViewById(R.id.text_profile_name);
                //profile_userName.setText(getIntent().getStringExtra("profileUserName"));

                // TextView profile_userPhone = (TextView)findViewById(R.id.text_profile_phone);

                // Check database for profile-picture

                // query for databasecheck for picture
                String imageURL = "http://www.seosmarty.com/wp-content/uploads/2011/08/profile-picture.jpg";


                // BUTTONS

            }
        } else {
            newString= (String) savedInstanceState.getSerializable("extra_email");
        }

    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // TODO Add camera posiblities, read: http://www.codepool.biz/take-a-photo-from-android-camera-and-upload-it-to-a-remote-php-server.html for example

            // Handle the camera action
        } else if (id == R.id.nav_manage) {
            // go back to profile-view
          //  startActivity(new Intent(UserProfileActivity.this,AdminActivity.class));


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.toolbar ) {
            startActivity(new Intent(UserProfileActivity.this, UserProfileActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
