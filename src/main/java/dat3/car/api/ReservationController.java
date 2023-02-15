package dat3.car.api;

import dat3.car.dto.Reservation.ReservationRequest;
import dat3.car.dto.Reservation.ReservationResponse;
import dat3.car.service.ReservationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ReservationResponse newReservation(@RequestBody ReservationRequest body) {
        return reservationService.newReservation(body);
    }
}
