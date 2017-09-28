package sigma.scsapp.model;


import android.widget.ImageView;

/**
 * Created by Niklas on 2017-09-14.
 */

public class Vehicle {

    private Integer vehicleId;
    private String reg;
    private Integer year;
    private Double mileage;
    private String body;
    private String equipment;
    private String model;
    private String fuel;
    private String site;
    private String responsible;
    private ImageView vehicleImage;
    private boolean isAvalible;
    private String vehicleImageLink;


    public Vehicle() {
    }

    // GETTERS AND SETTERS


    public Integer getVehicleId()
        {
        return vehicleId;
        }


    public String getReg()
        {
        return reg;
        }

    public void setReg(String reg)
        {
        this.reg = reg;
        }

    public Integer getYear()
        {
        return year;
        }

    public void setYear(Integer year)
        {
        this.year = year;
        }

    public Double getMileage()
        {
        return mileage;
        }

    public void setMileage(Double mileage)
        {
        this.mileage = mileage;
        }

    public String getBody()
        {
        return body;
        }

    public void setBody(String body)
        {
        this.body = body;
        }

    public String getEquipment()
        {
        return equipment;
        }

    public void setEquipment(String equipment)
        {
        this.equipment = equipment;
        }

    public String getModel()
        {
        return model;
        }

    public void setModel(String model)
        {
        this.model = model;
        }

    public String getFuel()
        {
        return fuel;
        }

    public void setFuel(String fuel)
        {
        this.fuel = fuel;
        }

    public String getSite()
        {
        return site;
        }

    public void setSite(String site)
        {
        this.site = site;
        }

    public String getResponsible()
        {
        return responsible;
        }

    public void setResponsible(String responsible)
        {
        this.responsible = responsible;
        }

    public ImageView getVehicleImage()
        {
        return vehicleImage;
        }

    public void setVehicleImage(ImageView vehicleImage)
        {
        this.vehicleImage = vehicleImage;
        }

    public boolean isAvalible()
        {
        return isAvalible;
        }

    public void setAvalible(boolean avalible)
        {
        isAvalible = avalible;
        }

    public String getVehicleImageLink()
        {
        return vehicleImageLink;
        }

    public void setVehicleImageLink(String vehicleImageLink)
        {
        this.vehicleImageLink = vehicleImageLink;
        }
}
