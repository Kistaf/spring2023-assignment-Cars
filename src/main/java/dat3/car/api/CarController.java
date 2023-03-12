package dat3.car.api;

import dat3.car.dto.Car.CarRequest;
import dat3.car.dto.Car.CarResponse;
import dat3.car.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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

    // TEMP REMOVE ONCE SECURITY IS IMPLEMENTED
    @GetMapping("admin")
    List<CarResponse> getTempAdminCars() {
        return carService.getCars(true);
    }

    // TEMP REMOVE ONCE SECURITY IS IMPLEMENTED
    @GetMapping("admin/{id}")
    CarResponse getTempAdminCarById(@PathVariable Long id) {
        return carService.getCarById(id, true);
    }

    // ADMIN
    @GetMapping("/{id}")
    CarResponse getCarById(@PathVariable Long id) {
        return carService.getCarById(id, false);
    }

    @GetMapping("/filter")
    List<CarResponse> getCarsByBrandAndId(@RequestParam("brand") String brand, @RequestParam("model") String model) {
        return carService.carsByBrandAndModel(brand, model);
    }

    @GetMapping("not-reserved")
    List<CarResponse> getCarsNotReserved() {
        return carService.carsNotReserved();
    }

    @GetMapping("bestDiscount")
    List<CarResponse> getCarsByBestDiscount(@RequestParam int lowerBound) {
        return carService.findCarsByBestDiscount(lowerBound);
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
