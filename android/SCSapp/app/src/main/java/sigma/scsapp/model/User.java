package sigma.scsapp.model;

/**
 * Created by Niklas on 2017-09-12.
 */

public class User {

    // PROFILE
    private Long id;
    private String userName;
    private Boolean isApproved;
    private Integer totalBookingsYear;
    private Double totalDistanceYear;

    // Password Field
    // Using EditText with "inputtype=textpassword" makes it *****
    private String password;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Integer getTotalBookingsYear() {
        return totalBookingsYear;
    }

    public void setTotalBookingsYear(Integer totalBookingsYear) {
        this.totalBookingsYear = totalBookingsYear;
    }

    public Double getTotalDistanceYear() {
        return totalDistanceYear;
    }

    public void setTotalDistanceYear(Double totalDistanceYear) {
        this.totalDistanceYear = totalDistanceYear;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
