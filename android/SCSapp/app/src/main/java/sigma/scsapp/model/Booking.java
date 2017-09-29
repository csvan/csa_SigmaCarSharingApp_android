package sigma.scsapp.model;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Niklas on 2017-09-12.
 */

public class Booking {

    private long bookingId;
    private long userId;
    private String reg;
    private Date time_of_booking;
    // private Date starting_date;
    private Date starting_date;
    private Time starting_time;
    private Date ending_date;
    private Time ending_time;
    private String errand;
    private String destination;
    private String purpose;


    public List<User> getUser()
        {
        return user;
        }

    public void setUser(List<User> user)
        {
        this.user = user;
        }

    private List<User> user;

    public Booking() {
    }

    public Booking(long userId, String reg, Time time_of_booking, Date starting_date, Time starting_time, String errand, String destination, String purpose) {
        this.userId = userId;
        this.reg = reg;
        this.time_of_booking = time_of_booking;
        this.starting_date = starting_date;
        this.starting_time = starting_time;
        this.errand = errand;
        this.destination = destination;
        this.purpose = purpose;
    }

    public long getBookingId() {
        return bookingId;
    }

    public long getUserId() {
        return userId;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public Date getTime_of_booking() {
        return time_of_booking;
    }

    public void setTime_of_booking(Date time_of_booking) {
        this.time_of_booking = time_of_booking;
    }

    public Date getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(Date starting_date) {
        this.starting_date = starting_date;
    }

    public Time getStarting_time() {
        return starting_time;
    }

    public void setStarting_time(Time starting_time) {
        this.starting_time = starting_time;
    }

    public Date getEnding_date() {
        return ending_date;
    }

    public void setEnding_date(Date ending_date) {
        this.ending_date = ending_date;
    }

    public Time getEnding_time() {
        return ending_time;
    }

    public void setEnding_time(Time ending_time) {
        this.ending_time = ending_time;
    }

    public String getErrand() {
        return errand;
    }

    public void setErrand(String errand) {
        this.errand = errand;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }


}