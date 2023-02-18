package dat3.car.api;

import dat3.car.dto.Reservation.ReservationRequest;
import dat3.car.dto.Reservation.ReservationResponse;
import dat3.car.service.ReservationService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping(path = "/{username}")
    public List<ReservationResponse> allByUsername(@PathVariable String username) {
        return reservationService.findAllByMember(username);
    }

    @GetMapping("/count/{username}")
    int getReservationsCountByUsername(@PathVariable String username) {
        return reservationService.countMemberReservations(username);
    }
}
