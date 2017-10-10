package sigma.scsapp.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sigma.scsapp.R;
import sigma.scsapp.controllers.JSONTaskVehicle;
import sigma.scsapp.model.Vehicle;
import sigma.scsapp.utility.AsyncResponseVehicle;
import sigma.scsapp.utility.BottomNavigationViewHelper;

public class UserProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AsyncResponseVehicle
    {
        private final String URL_TO_HIT = "http://10.0.2.2:8000/servertestvehicle.json";
        private boolean accepted = true;
        ListView lvVehicle;
        JSONTaskVehicle myJsonTask = new JSONTaskVehicle();


        @Override
        protected void onCreate(Bundle savedInstanceState)
            {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.user_drawer);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            myJsonTask.delegate = this;
            myJsonTask.execute(URL_TO_HIT);


            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
            BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
            Menu menu = bottomNavigationView.getMenu();
            MenuItem menuItem = menu.getItem(0);
            menuItem.setChecked(true);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item)
                        {
                        switch (item.getItemId())
                            {

                            case R.id.ic_books:
                                Intent intent2 = new Intent(UserProfileActivity.this, BookingActivity.class);
                                startActivity(intent2);
                                break;

                            case R.id.ic_center_focus:
                                // Intent intent3 = new Intent(UserProfileActivity.this, LogActivity.class);
                                // startActivity(intent3);
                                break;

                            case R.id.ic_backup:
                                Intent intent4 = new Intent(UserProfileActivity.this, LogActivity.class);
                                startActivity(intent4);
                                break;
                            }


                        return false;
                        }
                });

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            Log.i("Tag", "You pressed the naviagion drawer --------------");
            drawer.setDrawerListener(toggle);
            toggle.syncState();


            // SET UP PROFILE
            String newString;

            // TODO Generate content based on Database
            if (savedInstanceState == null)
                {

                Bundle extras = getIntent().getExtras();
                if (extras == null)
                    {
                    newString = null;
                    } else
                    {

                    Log.i("test", "Setting up profile");
                    TextView profile_userId = (TextView) findViewById(R.id.text_profile_name);
                    profile_userId.setText(getIntent().getStringExtra("userName"));

                    // TextView profile_userName = (TextView)findViewById(R.id.text_profile_name);
                    //profile_userName.setText(getIntent().getStringExtra("profileUserName"));

                    // TextView profile_userPhone = (TextView)findViewById(R.id.text_profile_phone);

                    // Check database for profile-picture

                    // query for databasecheck for picture
                    String imageURL = "http://www.seosmarty.com/wp-content/uploads/2011/08/profile-picture.jpg";


                    // BUTTONS

                    }
                } else
                {
                newString = (String) savedInstanceState.getSerializable("extra_email");
                }

            }


        @Override
        public void onBackPressed()
            {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START))
                {
                drawer.closeDrawer(GravityCompat.START);
                } else
                {
                super.onBackPressed();
                }
            }

        @Override
        public boolean onCreateOptionsMenu(Menu menu)
            {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.user_profile, menu);
            return true;
            }

        @Override
        public boolean onOptionsItemSelected(MenuItem item)
            {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();


            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings)
                {
                return true;
                }

            return super.onOptionsItemSelected(item);
            }


        @Override
        public void processFinish(final List<Vehicle> output)
            {
            Log.i("Result tag", " Result from JSONTASK: " + output);
            Log.i("OnPostExecute", " Trying to finish up with Row into the List with result: " + output);
            if (output != null)
                {
                // the Adapter takes the Row-Layout, inserting the result into it.
                VehicleAdapter adapter = new VehicleAdapter(UserProfileActivity.this, R.layout.list_item_vehicle, output);
                // the ListView (lvBooking) takes the adapter, in this case the Row (with the result) and add it into the ListView.
                ListView lvVehicle = (ListView) findViewById(R.id.lv_listOfCurrentBookings);

                lvVehicle.setAdapter(adapter);
                lvVehicle.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {  // list item click opens a new detailed activity
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                            {
                            Vehicle booking = output.get(position); // getting the model
                            Intent intent = new Intent(UserProfileActivity.this, DetailActivity.class);
                            //intent.putExtra("bookingkey", new Gson().toJson(booking)); // converting model json into string type and sending it via intent
                           // startActivity(intent);
                            Toast.makeText(UserProfileActivity.this, "You clicked on your active booking", Toast.LENGTH_SHORT).show();
                            }
                    });
                } else
                {
                Toast.makeText(UserProfileActivity.this, "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
                }

            }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item)
            {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_camera)
                {
                // TODO Add camera posiblities, read: http://www.codepool.biz/take-a-photo-from-android-camera-and-upload-it-to-a-remote-php-server.html for example

                // Handle the camera action
                } else if (id == R.id.nav_manage)
                {
                // go back to profile-view
                startActivity(new Intent(UserProfileActivity.this, AdminActivity.class));


                } else if (id == R.id.nav_share)
                {

                } else if (id == R.id.toolbar)
                {
                startActivity(new Intent(UserProfileActivity.this, UserProfileActivity.class));
                }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
            }


        public class VehicleAdapter extends ArrayAdapter
            {


                private List<Vehicle> bookingList;
                private int resource;
                private LayoutInflater inflater;

                public VehicleAdapter(Context context, int resource, List<Vehicle> objects)
                    {
                    super(context, resource, objects);
                    bookingList = objects;
                    Log.i("VehicleAdapter", "bookingList got info: " + bookingList);
                    this.resource = resource;
                    inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    }

                @Override
                public View getView(int position, View convertView, ViewGroup parent)
                    {
                    Log.i("VehicleAdapter", "Starting the VehicleAdapter");
                    ViewHolder holder = null;
                    if (convertView == null)
                        {
                        holder = new ViewHolder();
                        convertView = inflater.inflate(resource, null);
                        // holder.tvId = (TextView) convertView.findViewById(R.id.tvId);
                        //holder.vehicleId = (TextView) convertView.findViewById(R.id.tvVehicleId);
                        //holder.reg = (TextView) convertView.findViewById(R.id.tvReg);
                        holder.year = (TextView) convertView.findViewById(R.id.tv_item_name);
                       /* holder.mileage = (TextView) convertView.findViewById(R.id.tvMileage);
                        holder.body = (TextView) convertView.findViewById(R.id.tvBody);
                        holder.equipment = (TextView) convertView.findViewById(R.id.tvEquipment);
                        holder.model = (TextView) convertView.findViewById(R.id.tvModel);
                        holder.fuel = (TextView) convertView.findViewById(R.id.tvFuel);
                        holder.site = (TextView) convertView.findViewById(R.id.tvSite);
                        holder.responsible = (TextView) convertView.findViewById(R.id.tvResponsible);
                        holder.vehicleImage = (ImageView) convertView.findViewById(R.id.tvPurpose);
                        holder.isAvalible = (TextView) convertView.findViewById(R.id.tvIsAvalible);
                        TODO: 2017-10-10 fix imagelink
                        holder.vehicleImageLink = (TextView) convertView.findViewById(R.id.tvVehicleImageLink);*/

                        convertView.setTag(holder);

                        } else
                        {
                        holder = (ViewHolder) convertView.getTag();
                        }
                    //   holder.tvId.setText("Id" + bookingList.get(position).getId());
                  //  holder.vehicleId.setText(bookingList.get(position).getVehicleId());
                  //  holder.reg.setText(bookingList.get(position).getReg());
                    holder.year.setText(bookingList.get(position).getYear());
                 /* holder.mileage.setText(bookingList.get(position).getMileage());
                    holder.body.setText(bookingList.get(position).getBody());
                    holder.equipment.setText(bookingList.get(position).getEquipment());
                    holder.model.setText(bookingList.get(position).getModel());
                    holder.fuel.setText(bookingList.get(position).getFuel());
                    holder.site.setText(bookingList.get(position).getSite());
                    holder.responsible.setText(bookingList.get(position).getResponsible());
                    holder.vehicleImage.setimage(bookingList.get(position).getVehicleImage());
                    holder.vehicleImageLink.setText(bookingList.get(position).getVehicleImageLink());*/


                    return convertView;

                    }

                class ViewHolder
                    {
                        private TextView vehicleId;
                        private TextView reg;
                        private TextView year;
                        private TextView mileage;
                        private TextView body;
                        private TextView equipment;
                        private TextView model;
                        private TextView fuel;
                        private TextView site;
                        private TextView responsible;
                        private ImageView vehicleImage;
                        private TextView isAvalible;
                        private TextView vehicleImageLink;
                    }
            }

    }
