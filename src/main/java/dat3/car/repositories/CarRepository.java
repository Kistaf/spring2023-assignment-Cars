package dat3.car.repositories;

import dat3.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT c FROM Car c WHERE c.brand=?1 AND c.model=?2")
    List<Car> findCarsByBrandAndModel(String brand, String model);

    @Query(value = "SELECT c FROM Car c WHERE c.id NOT IN (SELECT r.reserved_car FROM Reservation r)")
    List<Car> findAllCarsWithEmptyReservations();

    @Query(value = "SELECT c FROM Car c WHERE c.bestDiscount > ?1")
    List<Car> findAllByDiscountGreaterThan(int lowerBound);

}
