package sigma.scsapp.utility;

import java.util.List;

import sigma.scsapp.model.User;

/**
 * Created by Niklas on 2017-10-09.
 */


public interface AsyncResponseUser {
    void processFinish(List<User> output);
}