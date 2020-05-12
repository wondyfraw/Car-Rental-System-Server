package edu.cs.cs472.carerent.model;

import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

public class Car {

    private Integer carId;
    private String carBrand;
    private String carModel;
    private Integer carProductionYear;
    private Integer mileage;
    private String carColor;
    private String transmission;

    public Car(Integer carId,String careBrand, String careModel, Integer careProductionYear, Integer mileage, String carColor,
               String transmission){
          this.carId = carId;
          this.carBrand =careBrand;
          this.carModel = careModel;
          this.carProductionYear = careProductionYear;
          this.mileage = mileage;
          this.carColor = carColor;
          this.transmission =transmission;
    }

    public Car(){this(0,null,null,0,0,null,null);}
    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getCarProductionYear() {
        return carProductionYear;
    }

    public void setCarProductionYear(Integer carProductionYear) {
        this.carProductionYear = carProductionYear;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public  Integer getCarId() {
        return  Car.class.hashCode();
    }
    public void setCarId(Integer carId){
        this.carId = carId;
    }

    public String toString(){
        return new StringJoiner(",",Car.class.getSimpleName()+ "[", "]")
                .add("carID='" + carId)
                .add("carBrand='" + carBrand + "'")
                .add("carModel='" + carModel + "'")
                .add("carProductionYear='" + carProductionYear)
                .add("mileage='" + mileage)
                .add("carColor='" + carColor + "'")
                .add("transmission='" + transmission)
                .toString();
    }
}
