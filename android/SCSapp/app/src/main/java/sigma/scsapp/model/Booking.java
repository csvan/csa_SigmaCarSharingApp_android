package sigma.scsapp.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by Niklas on 2017-09-12.
 */

public class Booking {

    @SerializedName("id")
    private long id;
    private long userId;
    @SerializedName("reg")
    private String reg;
    @SerializedName("timeOfBooking")
    private Date timeOfBooking;
    // private Date startingDate;
    @SerializedName("startingDate")
    private Date startingDate;
    @SerializedName("startingTime")
    private Date startingTime;
    @SerializedName("endingDate")
    private Date endingDate;
    @SerializedName("endingTime")
    private Date endingTime;
    @SerializedName("errand")
    private String errand;
    @SerializedName("destination")
    private String destination;
    @SerializedName("purpose")
    private String purpose;
    @SerializedName("confirmed")
    private Boolean isConfirmed;
    @SerializedName("vehicle")
    private Vehicle vehicle;

    private List<User> userlist;

    public List<User> getUserlist()
        {
        return userlist;
        }

    public void setUserlist(List<User> userlist)
        {
        this.userlist = userlist;
        }

    public Booking() {
    }

    public Booking(long userId, String reg, Time timeOfBooking, Date startingDate, Time startingTime, String errand, String destination, String purpose) {
        this.userId = userId;
        this.reg = reg;
        this.timeOfBooking = timeOfBooking;
        this.startingDate = startingDate;
        this.startingTime = startingTime;
        this.errand = errand;
        this.destination = destination;
        this.purpose = purpose;
    }

    public long getId()
        {
        return id;
        }

    public void setId(long id)
        {
        this.id = id;
        }

    public long getUserId()
        {
        return userId;
        }

    public void setUserId(long userId)
        {
        this.userId = userId;
        }

    public String getReg()
        {
        return reg;
        }

    public void setReg(String reg)
        {
        this.reg = reg;
        }

    public Date getTimeOfBooking()
        {
        return timeOfBooking;
        }

    public void setTimeOfBooking(Date timeOfBooking)
        {
        this.timeOfBooking = timeOfBooking;
        }

    public Date getStartingDate()
        {
        return startingDate;
        }

    public void setStartingDate(Date startingDate)
        {
        this.startingDate = startingDate;
        }

    public Date getStartingTime()
        {
        return startingTime;
        }

    public void setStartingTime(Date startingTime)
        {
        this.startingTime = startingTime;
        }

    public Date getEndingDate()
        {
        return endingDate;
        }

    public void setEndingDate(Date endingDate)
        {
        this.endingDate = endingDate;
        }

    public Date getEndingTime()
        {
        return endingTime;
        }

    public void setEndingTime(Date endingTime)
        {
        this.endingTime = endingTime;
        }

    public String getErrand()
        {
        return errand;
        }

    public void setErrand(String errand)
        {
        this.errand = errand;
        }

    public String getDestination()
        {
        return destination;
        }

    public void setDestination(String destination)
        {
        this.destination = destination;
        }

    public String getPurpose()
        {
        return purpose;
        }

    public void setPurpose(String purpose)
        {
        this.purpose = purpose;
        }

    public Boolean getConfirmed()
        {
        return isConfirmed;
        }

    public void setConfirmed(Boolean confirmed)
        {
        isConfirmed = confirmed;
        }

    public Vehicle getVehicle()
        {
        return vehicle;
        }

    public void setVehicle(Vehicle vehicle)
        {
        this.vehicle = vehicle;
        }


}
