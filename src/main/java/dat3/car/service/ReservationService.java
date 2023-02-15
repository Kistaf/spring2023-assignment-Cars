package dat3.car.service;

import dat3.car.dto.Car.CarResponse;
import dat3.car.dto.Member.MemberResponse;
import dat3.car.dto.Reservation.ReservationRequest;
import dat3.car.dto.Reservation.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repositories.CarRepository;
import dat3.car.repositories.MemberRepository;
import dat3.car.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;


    public ReservationService(ReservationRepository reservationRepository, MemberRepository memberRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
    }

    public ReservationResponse newReservation(ReservationRequest body) {
        Optional<Member> member = memberRepository.findById(body.getUsername());
        Optional<Car> optionalCar = carRepository.findById(body.getCarId());

        if (!member.isPresent() || !optionalCar.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Either the car data or member data provided was wrong.");
        }
        Car car = optionalCar.get();
        if (car.getReservations().stream().anyMatch(c -> c.getReservationDate().isEqual(body.getReservationDate()))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The car is unavailable on the specified day");
        }

        if (body.getReservationDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot reserve a car for a day in the past");
        }
        Reservation newReservation = new Reservation(body.getReservationDate(), body.getRentalDate(), member.get(), car);
        Reservation res = reservationRepository.save(newReservation);
        return new ReservationResponse(res);
    }
}
