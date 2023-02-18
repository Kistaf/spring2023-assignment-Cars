package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repositories.CarRepository;
import dat3.car.repositories.MemberRepository;
import dat3.car.repositories.ReservationRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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

        Car car3 = new Car();
        car3.setBrand("Porsche");
        car3.setModel("Taycan");
        car3.setPricePrDay(20000);
        car3.setBestDiscount(50000);
        carRepository.save(car3);

        Member member = new Member("Test1", "Test", "Test@Test1.test", "Test", "Test", "Test_Street", "Test", "Test");
        Member member2 = new Member("Test2", "Test", "Test@Test.test", "Test", "Test", "Test_Street", "Test", "Test");
        memberRepository.save(member);
        memberRepository.save(member2);
        Reservation reservation = new Reservation(LocalDate.now(), LocalDate.now(), member, car1);
        Reservation reservation2 = new Reservation(LocalDate.now(), LocalDate.now(), member2, car2);
        Reservation reservation3 = new Reservation(LocalDate.of(2023, 05, 05), LocalDate.now(), member, car2);
        reservationRepository.save(reservation);
        reservationRepository.save(reservation2);
        reservationRepository.save(reservation3);


        setupUserWithRoleUsers();
    }


    @Autowired
    UserWithRolesRepository userWithRolesRepository;

    private final String passwordUsedByAll = "test1234";

    private void setupUserWithRoleUsers() {
        UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
        UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
        UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
        UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
        user1.addRole(Role.USER);
        user1.addRole(Role.ADMIN);
        user2.addRole(Role.USER);
        user3.addRole(Role.ADMIN);
        //No Role assigned to user4
        userWithRolesRepository.save(user1);
        userWithRolesRepository.save(user2);
        userWithRolesRepository.save(user3);
        userWithRolesRepository.save(user4);
    }

}
