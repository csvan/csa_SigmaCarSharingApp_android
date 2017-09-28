package sigma.scsapp.booking;

import android.widget.DatePicker;

/**
 * Created by Niklas on 2017-09-27.
 */

public class EndDatePickerFragment
    extends DatePickerFragment
    {


        @Override
        public void onDateSet(DatePicker datepicker, int year, int month, int day) {
        ((EndDateListener) getActivity().onEndDateSet(year, month, day));
        }

        public interface EndDateListener {
            void onEndTimeSet(int year, int month, int day);
        }
    }
