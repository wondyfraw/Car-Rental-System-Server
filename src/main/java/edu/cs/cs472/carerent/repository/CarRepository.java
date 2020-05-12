package edu.cs.cs472.carerent.repository;

import edu.cs.cs472.carerent.model.Car;

import java.util.List;

public class CarRepository {

    private List<Car> carList;

    public CarRepository(List<Car> carList) {
        this.carList = carList;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public Car addNewCar(Car newCar){
        carList.add(newCar);
        return newCar;
    }
}
