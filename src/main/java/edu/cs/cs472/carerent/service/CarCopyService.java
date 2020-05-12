package edu.cs.cs472.carerent.service;

import edu.cs.cs472.carerent.model.Car;
import edu.cs.cs472.carerent.model.CarCopy;
import edu.cs.cs472.carerent.repository.CarCopyRepository;

import java.util.List;

public class CarCopyService {

    private CarCopyRepository carCopyRepository;

    public String getAllRegisteredCarCopyJSON() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        List<CarCopy> carCopies = carCopyRepository.getCarList();
        int count = 0;
        for(CarCopy carCopy : carCopies) {
            sb.append("{");
            sb.append(String.format("\"carCopyId\": %d, ", carCopy.getCarCopyId()));
            sb.append(String.format("\"quantity\": %d, ", carCopy.getQuantity()));
            sb.append(String.format("\"available\": \"%s\", \"plateNumber\": \"%s\",  ",
                    carCopy.isAvailable(), carCopy.getPlateNumber()));
            sb.append(String.format("\"rentPrice\": %d, ", carCopy.getRentPrice()));
            sb.append(String.format("\"carId\": %d, ", carCopy.getCar().getCarId()));
            sb.append("}");
            if(++count != carCopies.size()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void registerNewCarCopy(CarCopy carCopy){this.carCopyRepository.addNewCarCopy(carCopy);}
}
