package dat3.car.dto.Car;

import dat3.car.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarRequest {
    private Long id;
    private String brand;
    private String model;
    private double pricePrDay;


    public static Car getCarEntity(CarRequest c) {
        return new Car(c.getId(), c.getBrand(), c.getModel(), c.getPricePrDay());
    }

    public CarRequest(Car c) {
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
    }
}

