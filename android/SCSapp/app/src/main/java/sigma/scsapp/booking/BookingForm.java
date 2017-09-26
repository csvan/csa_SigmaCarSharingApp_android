package sigma.scsapp.booking;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;

import sigma.scsapp.R;

public class BookingForm
        extends FragmentActivity
        implements DatePickerDialog.OnDateSetListener, StartTimePickerFragment.StartTimeListener, EndTimePickerFragment.EndTimeListener {

    TimePickerFragment timePickerStart;
    TimePickerFragment timePickerEnd;

    DatePickerFragment datePickerStart;
    DatePickerFragment datePickerEnd;

    Button btn_time;

    private int startHour;
    private int startMinute;

    private int endHour;
    private int endMinute;

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
        timePickerStart = new StartTimePickerFragment();
        timePickerStart.show(getFragmentManager(), "timePickerStart");
    }

    public void showTimePickerDialogEnd(View v) {
        Log.i("Tag Time", "Clicked on End Time");
        timePickerEnd = new EndTimePickerFragment();
        timePickerEnd.show(getFragmentManager(), "timePickerEnd");
    }

    // DATE START AND END
    public void showDatePickerDialogStart(View v) {
        Log.i("Tag Date", "Clicked on Start Date");
        datePickerStart = new DatePickerFragment();
        datePickerStart.show(getFragmentManager(), "datePickerStart");
    }

    public void showDatePickerDialogEnd(View v) {
        Log.i("Tag Date", "Clicked on End Date");
        datePickerEnd = new DatePickerFragment();
        datePickerEnd.show(getFragmentManager(), "datePickerEnd");
    }

    @Override
    public void onDateSet(DatePicker view, int y, int m, int d) {
        // Kan skicka in y,m,d i Cal direkt.
        Log.i("Tag onDateSet ", "OnDateSet activated in the BookingForm");

        int year = y;
        int month = m;
        int day = d;

        setDate(y, m, d);
    }

    private void setDate(int year, int month, int day) {
        Log.i("Setting date", year + " " + month + " " + day);
        Calendar calendar = new GregorianCalendar(year, month, day);
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((TextView) findViewById(R.id.tv_bookingform_set_date_start)).setText(dateFormat.format(calendar.getTime()));
    }

  /*  String getDate = (year + " " + month + " " + day);
    TextView tv_date = (TextView) findViewById(R.id.tv_bookingform_set_date_start);
    tv_date.setText(getDate);
    }*/

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

    public void setTime(int hour, int min) {
        Log.i("Setting time", hour + " " + min);
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        int hour2 = hour;
        int min2 = min;
        ((TextView) findViewById(R.id.tv_bookingform_set_time_start)).setText(hour2 + " : " + min2);
    }

    @Override
    public void onEndTimeSet(int hour, int minutes) {
        Log.i("Booking", "Setting end time: " + hour + ":" + minutes);
        endHour = hour;
        endMinute = minutes;
        ((TextView) findViewById(R.id.tv_bookingform_set_time_end)).setText(endHour + " : " + endMinute);
    }

    @Override
    public void onStartTimeSet(int hour, int minutes) {
        Log.i("Booking", "Setting start time: " + hour + ":" + minutes);
        startHour = hour;
        startMinute = minutes;
        ((TextView) findViewById(R.id.tv_bookingform_set_time_start)).setText(startHour + " : " + startMinute);
    }
}



