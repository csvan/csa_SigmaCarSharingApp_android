package sigma.scsapp.activities;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sigma.scsapp.R;
import sigma.scsapp.model.Booking;
import sigma.scsapp.booking.TimePickerFragment;
import sigma.scsapp.booking.*;


public class BookingFormActivity extends FragmentActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    public BookingFormActivity()
        {
        }

    // TIME START AND END
    public void showTimePickerDialogStart(View v) {
    Log.i("Tag Time", "Clicked on Start Time");
    DialogFragment newFragment = new StartTimePickerFragment();
    newFragment.show(getFragmentManager(), "timePickerStart");
    }
    public void showTimePickerDialogEnd(View v) {
    Log.i("Tag Time", "Clicked on End Time");
    DialogFragment newFragment = new EndTimePickerFragment();
    newFragment.show(getFragmentManager(), "timePickerEnd");
    }

    // DATE START AND END
    public void showDatePickerDialogStart(View v) {
    Log.i("Tag Date", "Clicked on Start Date");
    DialogFragment newFragment = new StartDatePickerFragment();
    newFragment.show(getFragmentManager(), "datePickerStart");
    }
    public void showDatePickerDialogEnd(View v) {
    Log.i("Tag Date", "Clicked on End Date");
    DialogFragment newFragment = new EndDatePickerFragment();
    newFragment.show(getFragmentManager(), "datePickerEnd");
    }



    @Override
    public void onDateSet(DatePicker view, int y, int m, int d) {
    // Kan skicka in y,m,d i Cal direkt.
    Log.i("Tag onDateSet ", "OnDateSet activated in the BookingFormActivity");
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

    @Override
    public void onTimeSet(TimePicker view, int i, int i1) {
    // TODO: 2017-09-25 Fixa en if-sats checkpoint för knapparna (starttime och endtime)
    Log.i("Tag on Booking", "onTimeSet activated in the BookingFormActivity");
    int hour = view.getHour();
    int min = view.getMinute();
    setTime(hour, min);

    }

    public void setTime(int hour, int min){
    final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
    int hour2 = hour;
    int min2 = min;
    ((TextView) findViewById(R.id.tv_bookingform_set_time_start)).setText(hour2 +" : " + min2);
    }

}



