package sigma.scsapp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Niklas on 2017-09-12.
 */

class Booking {

    private long bookingId;
    private long userId;
    private String reg;
    private LocalDateTime time_of_booking;
   // private Date starting_date;
    private LocalDate starting_date;
    private LocalTime starting_time;
    private LocalDate ending_date;
    private LocalTime ending_time;
    private String errand;
    private String destination;
    private String purpose;

    private List<Booking> listOfBookings;


    public Booking() {
    }

    public Booking(long userId, String reg, LocalDateTime time_of_booking, LocalDate starting_date, LocalTime starting_time, String errand, String destination, String purpose) {
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

    public LocalDateTime getTime_of_booking() {
        return time_of_booking;
    }

    public void setTime_of_booking(LocalDateTime time_of_booking) {
        this.time_of_booking = time_of_booking;
    }

    public LocalDate getStarting_date() {
        return starting_date;
    }

    public void setStarting_date(LocalDate starting_date) {
        this.starting_date = starting_date;
    }

    public LocalTime getStarting_time() {
        return starting_time;
    }

    public void setStarting_time(LocalTime starting_time) {
        this.starting_time = starting_time;
    }

    public LocalDate getEnding_date() {
        return ending_date;
    }

    public void setEnding_date(LocalDate ending_date) {
        this.ending_date = ending_date;
    }

    public LocalTime getEnding_time() {
        return ending_time;
    }

    public void setEnding_time(LocalTime ending_time) {
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

    public List<Booking> getListOfBookings() {
        return listOfBookings;
    }

    public void setListOfBookings(List<Booking> listOfBookings) {
        this.listOfBookings = listOfBookings;
    }

    public void addBooking(Booking booking) {
        return;
    }
}
