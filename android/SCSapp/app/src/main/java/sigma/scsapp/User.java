package sigma.scsapp;

/**
 * Created by Niklas on 2017-09-12.
 */

public class User {

    // PROFILE
    private Integer userId;
    private String user_name;
    private Boolean approved;
    private Integer total_bookings_year;
    private Integer total_distance_year;

    // Password Field
    // Using EditText with "inputtype=textpassword" makes it *****
    private String password;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Integer getTotal_bookings_year() {
        return total_bookings_year;
    }

    public void setTotal_bookings_year(Integer total_bookings_year) {
        this.total_bookings_year = total_bookings_year;
    }

    public Integer getTotal_distance_year() {
        return total_distance_year;
    }

    public void setTotal_distance_year(Integer total_distance_year) {
        this.total_distance_year = total_distance_year;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
