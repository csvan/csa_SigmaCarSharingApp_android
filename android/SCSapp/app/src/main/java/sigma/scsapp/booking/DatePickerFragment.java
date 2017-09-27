package sigma.scsapp.booking;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Niklas on 2017-09-21.
 */

public class DatePickerFragment
        extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Log.i("tag for Date", "onDateSet activated in DatePickerFragment, should be in Bookingform?");

        // TODO: 2017-09-21 #1 Convert into object 
        // TODO: 2017-09-21 #2 Return object into json
        // TODO: 2017-09-21 #3 send json to the DB
    }
}
