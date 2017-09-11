package sigma.scsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class ConfirmBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Accepted terms", "You accept the terms of coffee");
                // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
                startActivity(new Intent(ConfirmBookingActivity.this,UserProfileActivity.class));

            }
        });

        final Switch accept_car_switch;
        accept_car_switch = (Switch) findViewById(R.id.accept_car_switch);
        accept_car_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Snackbar.make(view, "You accepted the terms!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });
    }
}
