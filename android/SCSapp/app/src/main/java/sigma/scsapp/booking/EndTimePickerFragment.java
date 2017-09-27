package sigma.scsapp.booking;

import android.widget.TimePicker;

public class EndTimePickerFragment
        extends TimePickerFragment {

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minutes) {
        ((EndTimeListener) getActivity()).onEndTimeSet(hour, minutes);
    }

    public interface EndTimeListener {
        void onEndTimeSet(int hour, int minutes);
    }
}