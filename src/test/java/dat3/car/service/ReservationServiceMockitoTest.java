package dat3.car.service;

import dat3.car.dto.Reservation.ReservationRequest;
import dat3.car.dto.Reservation.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repositories.CarRepository;
import dat3.car.repositories.MemberRepository;
import dat3.car.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceMockitoTest {

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    MemberRepository memberRepository;

    @Mock
    CarRepository carRepository;

    ReservationService reservationService;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService(reservationRepository, memberRepository, carRepository);
    }

    @Test
    void newReservation() {
        Member member = new Member("Test_user", "Test", "Test@Test.test", "Test", "Test", "Test_Street", "Test", "Test");
        Car car = new Car();
        car.setId(1L);
        car.setBrand("Mercedes");
        car.setModel("C63 AMG");
        car.setPricePrDay(13000);
        car.setBestDiscount(800000);
        Mockito.when(memberRepository.findById(any(String.class))).thenReturn(Optional.of(member));
        Mockito.when(carRepository.findById(any(Long.class))).thenReturn(Optional.of(car));
        Reservation reservation = new Reservation(LocalDate.of(2023, 02, 15), LocalDate.now(), member, car);
        Mockito.when(reservationRepository.save(any())).thenReturn(reservation);
        ReservationRequest request = new ReservationRequest("Test_user", 1l, LocalDate.of(2023, 02, 15), LocalDate.now());
        ReservationResponse res = reservationService.newReservation(request);
        assertEquals(LocalDate.of(2023, 02, 15), res.getReservationDate());
    }

    @Test
    void testReservationBeforeCurrentDate() {
        Member member = new Member("Test_user", "Test", "Test@Test.test", "Test", "Test", "Test_Street", "Test", "Test");
        Car car = new Car();
        car.setId(1L);
        car.setBrand("Mercedes");
        car.setModel("C63 AMG");
        car.setPricePrDay(13000);
        car.setBestDiscount(800000);
        Mockito.when(memberRepository.findById(any(String.class))).thenReturn(Optional.of(member));
        Mockito.when(carRepository.findById(any(Long.class))).thenReturn(Optional.of(car));
        ReservationRequest request = new ReservationRequest(member.getUsername(), car.getId(), LocalDate.of(2023, 02, 12), LocalDate.now());
        assertThrows(ResponseStatusException.class, () -> reservationService.newReservation(request));
    }

    @Test
    void carUnavailable() {
        Member member = new Member("Test_user", "Test", "Test@Test.test", "Test", "Test", "Test_Street", "Test", "Test");
        Car car = new Car();
        car.setId(1L);
        car.setBrand("Mercedes");
        car.setModel("C63 AMG");
        car.setPricePrDay(13000);
        car.setBestDiscount(800000);
        Car carMocked = Mockito.mock(Car.class);
        Member memberMocked = Mockito.mock(Member.class);
        Mockito.when(memberRepository.findById(any(String.class))).thenReturn(Optional.of(memberMocked));
        Mockito.when(carRepository.findById(any(Long.class))).thenReturn(Optional.of(carMocked));
        Reservation reservation = new Reservation(LocalDate.of(2023, 02, 15), LocalDate.now(), member, car);
        Reservation reservation2 = new Reservation(LocalDate.of(2023, 02, 17), LocalDate.now(), member, car);
        Mockito.when(carMocked.getReservations()).thenReturn(List.of(reservation, reservation2));
        ReservationRequest request = new ReservationRequest(member.getUsername(), car.getId(), LocalDate.of(2023, 02, 15), LocalDate.now());
        assertThrows(ResponseStatusException.class, () -> reservationService.newReservation(request));
    }
}