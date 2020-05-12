package edu.cs.cs472.carerent.repository;

import edu.cs.cs472.carerent.model.Car;
import edu.cs.cs472.carerent.model.CarCopy;

import java.util.List;

public class CarCopyRepository {

    private List<CarCopy> carCopyList;

    public CarCopyRepository(List<CarCopy> carCopyList){
              this.carCopyList = carCopyList;}

    public List<CarCopy> getCarList() {
        return carCopyList;
    }

    public CarCopy addNewCarCopy(CarCopy newCarCopy){
        carCopyList.add(newCarCopy);
        return newCarCopy;
    }
}
