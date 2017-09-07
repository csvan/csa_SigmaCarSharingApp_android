package sigma.scsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Avaliable text
        EditText getEmail;
        ImageView getImage;

        // Button in the menu
        Button booking;
        Button log;
        Button map;

        // Card for current car
        CardView user_booking_car;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
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
        user_booking_car = (CardView) findViewById(R.id.active_booking);
        user_booking_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Tag", "You pressed your current booking ------------------ ");
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
                   startActivity(new Intent(UserProfileActivity.this,ConfirmBookingActivity.class));

            }
        });

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


                // Check Database for profile-info together with the inputted Email.
                Log.i("test", "hi" );
                // TextView  = (TextView)findViewById(R.id.string_profile_email);

                // Check database for profile-name
               // TextView txtView2 = (TextView)findViewById(R.id.text_profile_name);

                // check database for profile-number
                TextView txtView3 = (TextView)findViewById(R.id.text_profile_phone);



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
