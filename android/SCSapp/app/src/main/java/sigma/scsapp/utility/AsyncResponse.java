package sigma.scsapp.utility;

import java.util.List;

import sigma.scsapp.model.BookingString;

/**
 * Created by Niklas on 2017-10-09.
 */


public interface AsyncResponse {
    void processFinish(List<BookingString> output);
}