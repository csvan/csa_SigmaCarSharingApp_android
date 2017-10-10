package sigma.scsapp.utility;

import java.util.List;

import sigma.scsapp.model.Vehicle;

/**
 * Created by Niklas on 2017-10-09.
 */


public interface AsyncResponseVehicle {
    void processFinish(List<Vehicle> output);
}