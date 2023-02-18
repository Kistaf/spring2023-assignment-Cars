package dat3.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate reservationDate;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate rentalDate;

    public Reservation(LocalDate reservationDate, LocalDate rentalDate, Member m, Car c) {
        this.reservationDate = reservationDate;
        this.rentalDate = rentalDate;
        this.member = m;
        this.reserved_car = c;
    }

    @ManyToOne
    private Member member;

    @ManyToOne
    private Car reserved_car;
}
