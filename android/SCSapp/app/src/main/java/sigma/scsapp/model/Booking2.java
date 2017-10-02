package sigma.scsapp.model;

/**
 * Created by Niklas on 2017-10-02.
 */

import java.util.HashMap;
import java.util.Map;

public class Booking2 {

    private int id;
    private String timeOfBooking;
    private String startingDate;
    private String startingTime;
    private String endingDate;
    private String endingTime;
    private String errand;
    private String destination;
    private String purpose;
    private String user;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Booking2() {
    }

    /**
     *
     * @param id
     * @param startingDate
     * @param timeOfBooking
     * @param errand
     * @param endingTime
     * @param startingTime
     * @param purpose
     * @param user
     * @param endingDate
     * @param destination
     */
    public Booking2(int id, String timeOfBooking, String startingDate, String startingTime, String endingDate, String endingTime, String errand, String destination, String purpose, String user) {
    super();
    this.id = id;
    this.timeOfBooking = timeOfBooking;
    this.startingDate = startingDate;
    this.startingTime = startingTime;
    this.endingDate = endingDate;
    this.endingTime = endingTime;
    this.errand = errand;
    this.destination = destination;
    this.purpose = purpose;
    this.user = user;
    }

    public int getId() {
    return id;
    }

    public void setId(int id) {
    this.id = id;
    }

    public String getTimeOfBooking() {
    return timeOfBooking;
    }

    public void setTimeOfBooking(String timeOfBooking) {
    this.timeOfBooking = timeOfBooking;
    }

    public String getStartingDate() {
    return startingDate;
    }

    public void setStartingDate(String startingDate) {
    this.startingDate = startingDate;
    }

    public String getStartingTime() {
    return startingTime;
    }

    public void setStartingTime(String startingTime) {
    this.startingTime = startingTime;
    }

    public String getEndingDate() {
    return endingDate;
    }

    public void setEndingDate(String endingDate) {
    this.endingDate = endingDate;
    }

    public String getEndingTime() {
    return endingTime;
    }

    public void setEndingTime(String endingTime) {
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

    public String getUser() {
    return user;
    }

    public void setUser(String user) {
    this.user = user;
    }

    public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
    }

}