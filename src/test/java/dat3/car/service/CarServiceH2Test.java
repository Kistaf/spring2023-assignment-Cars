package dat3.car.service;

import dat3.car.dto.Car.CarRequest;
import dat3.car.dto.Car.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repositories.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Due to the id of a car being of an auto generated value,
// the H2 database will after each test keep the current "auto increment state".
// Aka if the last test left off at ID=3 then for the next test an auto generated id
// would be of value 4. In other words, the application context associated with a test is dirty.
// We therefore reset the application context after each test using the following annotation...
// Tests following will instead use a fresh application context.
// As a consequence, the tests run quite a bit slower.
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DataJpaTest
class CarServiceH2Test {

    @Autowired
    private CarRepository carRepository;


    CarService carService;


    @BeforeEach
    void setUp() {
        Car car1 = new Car(1L, "Mercedes", "AMG ONE", 30000);
        Car car2 = new Car(2L, "Mercedes", "AMG GT", 20000);
        carRepository.save(car1);
        carRepository.save(car2);
        carService = new CarService(carRepository);
    }

    @Test
    void getCars() {
        List<CarResponse> cars = carService.getCars(false);
        assertEquals(2, cars.size());
    }

    @Test
    void getCarById() {
        CarResponse car = carService.getCarById(1L, false);
        assertEquals("AMG ONE", car.getModel());
    }

    @Test
    void addCar() {
        Car car = new Car();
        car.setBrand("Porsche");
        car.setModel("Taycan");
        car.setPricePrDay(20000);
        CarRequest request = new CarRequest(car);
        CarResponse response = carService.addCar(request);
        assertEquals("Taycan", response.getModel());
    }

    @Test
    void deleteCarById() {
        carService.deleteCarById(2L);
        // In order to test if the car was removed, we are forced
        // to dependent on an external method, even if this is not ideal.
        assertEquals(1, carService.getCars(false).size());
    }

    @Test
    void updateCarById() {
        Car car = new Car(1L, "Porsche", "Cayenne", 30000);
        CarRequest request = new CarRequest(car);
        // The ID is not parsed when we create a CarRequest
        request.setId(1L);
        CarResponse response = carService.updateCar(request);
        assertEquals("Cayenne", response.getModel());
    }
}