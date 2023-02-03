package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repositories.CarRepository;
import dat3.car.repositories.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DeveloperData implements ApplicationRunner {

    CarRepository carRepository;
    MemberRepository memberRepository;
    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
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

        Member member = new Member("Test2", "Test", "Test@Test.test", "Test", "Test", "Test_Street", "Test", "Test");

        // A relation table of name "member_favorite_car_colors" was added.
        // This way the primary key of a member can be used to select the favorite colors for the given member
        // from the newly created table, which contains a foreign key (member primary key).
        member.addFavoriteColor("Yellow");
        member.addFavoriteColor("Green");

        // The same as above changed.
        // A relation table of name "member_phones" was added.
        // Once again, we can use the same approach as above. We can select the phone numbers of a member through the
        // primary key, which has been stored as a foreign key in the "member_phones" table.
        member.addPhoneNumber("Mobile", "12345");
        member.addPhoneNumber("Work", "45678");
        memberRepository.save(member);
    }
}
