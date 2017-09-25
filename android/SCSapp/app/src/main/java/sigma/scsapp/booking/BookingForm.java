package sigma.scsapp.booking;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.support.v4.app.FragmentManager;


import java.sql.Time;
import java.text.DateFormat;


import sigma.scsapp.R;

public class BookingForm extends FragmentActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    Button btn_time;
    private int hour;
    private int min;
    private String format = "";
    private TextView time;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookingform_activity);



    }

    // TIME START AND END
    public void showTimePickerDialogStart(View v) {
        Log.i("Tag Time", "Clicked on Start Time");
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePickerStart");
    }
    public void showTimePickerDialogEnd(View v) {
    Log.i("Tag Time", "Clicked on End Time");
    DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePickerEnd");
    }

    // DATE START AND END
    public void showDatePickerDialogStart(View v) {
        Log.i("Tag Date", "Clicked on Start Date");
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePickerStart");
    }
    public void showDatePickerDialogEnd(View v) {
        Log.i("Tag Date", "Clicked on End Date");
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePickerEnd");
    }



    @Override
    public void onDateSet(DatePicker view, int y, int m, int d) {
        // Kan skicka in y,m,d i Cal direkt.
        Log.i("Tag onDateSet ", "OnDateSet activated in the BookingForm");
        int year = y;
        int month = m;
        int day = d;
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    private void setDate(final Calendar calendar){
    final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
    ((TextView) findViewById(R.id.tv_bookingform_set_date_start)).setText(dateFormat.format(calendar.getTime()));
    }

  /*  String getDate = (year + " " + month + " " + day);
    TextView tv_date = (TextView) findViewById(R.id.tv_bookingform_set_date_start);
    tv_date.setText(getDate);
    }*/

    @Override
    public void onTimeSet(TimePicker view, int i, int i1) {
    // TODO: 2017-09-25 Fixa en if-sats checkpoint för knapparna (starttime och endtime)
        Log.i("Tag on Booking", "onTimeSet activated in the BookingForm");
        hour = view.getHour();
        min = view.getMinute();
        setTime(hour, min);

    }

    /*public void showTime(int hour, int min) {
    if (hour == 0) {
    hour += 12;
    format = "AM";
    } else if (hour == 12) {
    format = "PM";
    } else if (hour > 12) {
    hour -= 12;
    format = "PM";
    } else {
    format = "AM";
    }

    time.setText(new StringBuilder().append(hour).append(" : ").append(min)
            .append(" ").append(format));
    }*/

    public void setTime(int hour, int min){
    final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
    int hour2 = hour;
    int min2 = min;
    ((TextView) findViewById(R.id.tv_bookingform_set_time_start)).setText(hour2 +" : " + min2);
    }

}



