package sigma.scsapp.booking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sigma.scsapp.R;


public class BookingActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ArrayList<String> car;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_activityview);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.ex_ListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        ListView carlist = (ListView) findViewById(R.id.booking_listOfCars);
        car = new ArrayList<String>();
        // TODO replace data with array of data from JSON
        car.add("Volvo v70");
        car.add("Volvo 6100");
        car.add("Bubblan 19");

        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, car);
        // Set The Adapter
        carlist.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        carlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                String selectedcar = car.get(position);
                Log.i("Car TAG", "You have selected " + selectedcar);

                Intent booking_form = new Intent();
                startActivity(new Intent(BookingActivity.this, BookingForm.class));
                booking_form.putExtra("carname", selectedcar);
                // TODO: 2017-09-21 request a timer for completion of the booking.
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Site");
        // To add another:
        //listDataHeader.add("cars");


        // Adding child data
        List<String> locations = new ArrayList<String>();
        locations.add("Gothenburg");
        locations.add("Stockholm");
        locations.add("Malmö");
        locations.add("Jonkoping");
        locations.add("Oslo");
        locations.add("London");
        locations.add("Berlin");

        // Can add more children with the same method. List<String> cars.
        // This can create a new list that shows up AFTER you checked location.


        listDataChild.put(listDataHeader.get(0), locations); // Header, Child data
        // To add another child into the list.
        // listDataChild.put(listDataHeader.get(1), cars);


        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                int position = (childPosition + 1);
                // getCarsfor(childPosition);
                String postString = String.valueOf(position);
                TextView selectedRegion = (TextView) findViewById(R.id.tv_bookingactivity_selected_region);
                selectedRegion.setText(postString);
                Log.e("Child click", "You clicked on vanue with name: " + (postString));

                expListView.collapseGroup(groupPosition);

                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        // Listview Group expanded listener
        // TODO: 2017-09-20 resolve the expandablelistview to differ from the custome class.

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }


}

