package sigma.scsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class LogActivity extends AppCompatActivity {
    ArrayList<String> car;

    public void onCreate(Bundle saveInstanceState)
    {
        // TODO: 2017-09-11 Moduel + Controller

        super.onCreate(saveInstanceState);
        setContentView(R.layout.log_list);

        // Get the reference of car
        ListView moviesList=(ListView)findViewById(R.id.LV_list);

        car = new ArrayList<String>();

        // TODO replace data with array of data from JSON
        car.add("Volvo v70");
        car.add("Volvo 6100");
        car.add("Bubblan 19");
        car.add("Volvo v70");
        car.add("Volvo 6100");
        car.add("Bubblan 19");
        car.add("Volvo v70");
        car.add("Volvo 6100");
        car.add("Bubblan 19");
        car.add("Volvo v70");
        car.add("Volvo 6100");
        car.add("Bubblan 19");
        car.add("Volvo v70");
        car.add("Volvo 6100");
        car.add("Bubblan 19");



        // Create The Adapter with passing ArrayList as 3rd parameter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, car);
        // Set The Adapter
        moviesList.setAdapter(arrayAdapter);

        // register onClickListener to handle click events on each item
        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // argument position gives the index of item which is clicked
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3)
            {
                String selectedcar= car.get(position);
                Log.i("Car TAG", "You have selected " + selectedcar);
            }
        });
    }
}
