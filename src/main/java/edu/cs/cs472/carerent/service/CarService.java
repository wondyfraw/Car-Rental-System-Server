package edu.cs.cs472.carerent.service;

import edu.cs.cs472.carerent.model.Car;
import edu.cs.cs472.carerent.repository.CarRepository;

import java.util.List;

public class CarService {
  private CarRepository carRepository;

  public CarService(List<Car> carList){this.carRepository = new CarRepository(carList);}

    public String getAllRegisteredCarsJSON() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        List<Car> cars = carRepository.getCarList();
        int count = 0;
        for(Car car : cars) {
            sb.append("{");
            sb.append(String.format("\"carID\": %d, ", car.getCarId()));
            sb.append(String.format("\"carBrand\": \"%s\", \"carModel\": \"%s\",  ",
                    car.getCarBrand(), car.getCarModel()));
            sb.append(String.format("\"carProductionYear\": %d, ", car.getCarProductionYear()));
            sb.append(String.format("\"mileage\": %d, ", car.getMileage()));
            sb.append(String.format("\"carColor\": \"%s\",  ",
                    car.getCarColor()));
            sb.append(String.format("\"transmission\": \"%s\"",
                    car.getTransmission()));
            sb.append("}");
            if(++count != cars.size()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void registerNewCar(Car car){this.carRepository.addNewCar(car);}
}
