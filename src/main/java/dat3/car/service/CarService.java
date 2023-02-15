package dat3.car.service;

import dat3.car.dto.Car.CarRequest;
import dat3.car.dto.Car.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repositories.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarResponse> getCars(boolean includeAll) {
        return carRepository.findAll().stream().map(c -> new CarResponse(c, includeAll)).toList();
    }

    public CarResponse getCarById(Long id, boolean includeAll) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            return new CarResponse(optionalCar.get(), includeAll);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No car with the given ID exists");
    }

    public CarResponse addCar(CarRequest body) {
        // A valid question:
        // Do we even need to check for duplicates?
        // A Car shop usually has multiple cars of the same type.
        // The usual way of identifying a car would be through the car reference number
        // or a license plate and not a system-given id.

        /*if (carRepository.existsById(body.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID already exist");
        }*/

        Car carToBeAdded = CarRequest.getCarEntity(body);
        carToBeAdded = carRepository.save(carToBeAdded);
        return new CarResponse(carToBeAdded, false);
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }


    public CarResponse updateCar(CarRequest body) {
        // Use Optional in the future to only change the values of a car, where the value has been changed.

        if (!carRepository.existsById(body.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No car with the given ID exists");
        }
        Car toChange = CarRequest.getCarEntity(body);
        toChange = carRepository.save(toChange);
        return new CarResponse(toChange, false);
    }

}
