package dat3.car.dto.Reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.car.entity.Reservation;
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
public class ReservationResponse {
    private Long id;
    private String username;
    private Long carId;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate reservationDate;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate rentalDate;

    public ReservationResponse(Reservation res) {
        this.id = res.getId();
        this.username = res.getMember().getUsername();
        this.carId = res.getReserved_car().getId();
        this.reservationDate = res.getReservationDate();
        this.rentalDate = res.getRentalDate();
    }
}
