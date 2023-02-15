package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repositories.CarRepository;
import dat3.car.repositories.MemberRepository;
import dat3.car.repositories.ReservationRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class DeveloperData implements ApplicationRunner {

    CarRepository carRepository;
    MemberRepository memberRepository;
    ReservationRepository reservationRepository;

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("DeveloperData has been run!");
        Car car1 = new Car();
        car1.setBrand("Mercedes");
        car1.setModel("C63 AMG");
        car1.setPricePrDay(13000);
        car1.setBestDiscount(800000);
        carRepository.save(car1);

        Car car2 = new Car();
        car2.setBrand("Tesla");
        car2.setModel("Model X");
        car2.setPricePrDay(10000);
        car2.setBestDiscount(600000);
        carRepository.save(car2);

        Member member = new Member("Test1", "Test", "Test@Test.test", "Test", "Test", "Test_Street", "Test", "Test");
        Member member2 = new Member("Test2", "Test", "Test@Test.test", "Test", "Test", "Test_Street", "Test", "Test");
        memberRepository.save(member);
        memberRepository.save(member2);
        Reservation reservation = new Reservation(LocalDate.now(), LocalDate.now(), member, car1);
        Reservation reservation2 = new Reservation(LocalDate.now(), LocalDate.now(), member2, car2);
        reservationRepository.save(reservation);
        reservationRepository.save(reservation2);
    }
}
