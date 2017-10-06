package sigma.scsapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sigma.scsapp.R;
import sigma.scsapp.booking.BookingForm;
import sigma.scsapp.booking.DetailActivity;
import sigma.scsapp.booking.TimePickerFragment;
import sigma.scsapp.model.Booking;
import sigma.scsapp.model.BookingString;

/**
 * Created by Niklas on 2017-10-03.
 */

public class MainActivity extends ActionBarActivity
    {
        private final String URL_TO_HIT = "http://10.0.2.2:8000/servertest.json";
        private TextView tvData;
        private ListView lvBookings;
        private ProgressDialog dialog;
        TimePickerFragment timepickerfrag;

        // Git error fix - http://stackoverflow.com/questions/16614410/android-studio-checkout-github-error-createprocess-2-windows

        @Override
        protected void onCreate(Bundle savedInstanceState)
            {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.bookingform_list);

            }
    }
