package dat3.car.api;

import dat3.car.dto.Car.CarRequest;
import dat3.car.dto.Car.CarResponse;
import dat3.car.service.CarService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    private CarService carService;

    private CarController(CarService carService) {
        this.carService = carService;
    }

    // ADMIN
    @GetMapping
    List<CarResponse> getCars() {
        return carService.getCars(false);
    }

    // ADMIN
    @GetMapping("/{id}")
    CarResponse getCarById(@PathVariable Long id) {
        return carService.getCarById(id, false);
    }

    // ADMIN
    @PostMapping()
    CarResponse addCar(@RequestBody CarRequest body) {
        return carService.addCar(body);
    }

    // ADMIN
    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable Long id) {
        carService.deleteCarById(id);
    }

    // ADMIN
    @PutMapping()
    public CarResponse updateCarById(@RequestBody CarRequest body) {
        return carService.updateCar(body);
    }
}
