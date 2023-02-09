package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Car {

    public Car(Long id, String brand, String model, double pricePrDay) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_brand", nullable = false, columnDefinition = "varchar(50)")
    private String brand;

    @Column(name = "car_model", nullable = false, columnDefinition = "varchar(60)")
    private String model;

    @Column(name = "rental_price_day")
    private double pricePrDay;

    @Column(name = "max_discount")
    private int bestDiscount;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp()
    private LocalDateTime lastEdited;
}
