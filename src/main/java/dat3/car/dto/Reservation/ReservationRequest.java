package dat3.car.dto.Reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import dat3.car.entity.Reservation;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {
    private String username;
    private Long carId;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate reservationDate;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate rentalDate;

    public ReservationRequest(String username, Long carId, LocalDate reservationDate, LocalDate rentalDate) {
        this.username = username;
        this.carId = carId;
        this.reservationDate = reservationDate;
        this.rentalDate = rentalDate;
    }
}
