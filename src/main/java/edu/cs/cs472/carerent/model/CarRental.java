package edu.cs.cs472.carerent.model;

import java.time.LocalDate;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

public class CarRental {
    private static final AtomicInteger carRentId = new AtomicInteger(0);
    private LocalDate pickUpDate;
    private LocalDate returnDate;
    private CarCopy carCopy;

    public CarRental(LocalDate pickUpDate, LocalDate returnDate, CarCopy carCopy) {
        this.pickUpDate = pickUpDate;
        this.returnDate = returnDate;
        this.carCopy = carCopy;
    }

    public CarRental(){this(null,null,null);}

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public CarCopy getCarCopy() {
        return carCopy;
    }

    public String toString(){
        return new StringJoiner(",",Car.class.getSimpleName()+ "[", "]")
                .add("quantity='" + pickUpDate + "'")
                .add("available='" + returnDate + "'")
                .add("carCopyId='" + carCopy.getCarCopyId())
                .toString();
    }
}
