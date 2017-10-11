package sigma.scsapp.utility;

import java.util.List;

import sigma.scsapp.model.Booking;

/**
 * Created by Niklas on 2017-10-09.
 */


public interface AsyncResponseBooking {
    void processFinishBooking(List<Booking> output);
}