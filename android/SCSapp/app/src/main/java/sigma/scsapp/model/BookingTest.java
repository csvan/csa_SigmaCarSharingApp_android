package sigma.scsapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Niklas on 2017-09-12.
 */

public class BookingTest {
    private long id;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private Date timeOfBooking;
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date startingDate;
    @JsonFormat(pattern = "HH:mm:ss")
    private Date startingTime;
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date endingDate;
    @JsonFormat(pattern = "HH:mm:ss")
    private Date endingTime;
    private String errand;
    private String destination;
    private String purpose;
    private Boolean isConfirmed;

    @SerializedName("users")
    private List<User> userlist;

    public BookingTest() {
    }

    public BookingTest(long id, String errand, String destination, String purpose, Boolean isConfirmed) {
        this.id = id;
        this.errand = errand;
        this.destination = destination;
        this.purpose = purpose;
        this.isConfirmed = isConfirmed;
    }

    public BookingTest(long id, Date timeOfBooking, Date startingDate, Date startingTime, Date endingDate, Date endingTime, String errand, String destination, String purpose, Boolean isConfirmed) {
        this.id = id;
        this.timeOfBooking = timeOfBooking;
        this.startingDate = startingDate;
        this.startingTime = startingTime;
        this.endingDate = endingDate;
        this.endingTime = endingTime;
        this.errand = errand;
        this.destination = destination;
        this.purpose = purpose;
        this.isConfirmed = isConfirmed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimeOfBooking() {
        return timeOfBooking;
    }

    public void setTimeOfBooking(Date timeOfBooking) {
        this.timeOfBooking = timeOfBooking;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public Date getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
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

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public List<User> getUserlist() {

        return userlist;
    }

    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
    }

    @Override
    public String toString() {
        return "BookingTest{" +
                "id=" + id +
                ", timeOfBooking=" + timeOfBooking +
                ", startingDate=" + startingDate +
                ", startingTime=" + startingTime +
                ", endingDate=" + endingDate +
                ", endingTime=" + endingTime +
                ", errand='" + errand + '\'' +
                ", destination='" + destination + '\'' +
                ", purpose='" + purpose + '\'' +
                ", isConfirmed=" + isConfirmed +
                ", userlist=" + userlist +
                '}';
    }
}

