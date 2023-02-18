package dat3.car.entity;

import dat3.security.entity.UserWithRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Member extends UserWithRoles {

    public Member(String user, String password, String email,
                  String firstName, String lastName, String street, String city, String zip) {
        super(user, password, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    //@Column(nullable = false)
    private String firstName;

    //@Column(nullable = false)
    private String lastName;

    //@Column(nullable = false)
    private String street;

    //@Column(nullable = false)
    private String city;

    //@Column(nullable = false)
    private String zip;

    //@Column(nullable = false)
    private boolean approved;

    //@Column(nullable = false)
    private int ranking;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp()
    private LocalDateTime lastEdited;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();

}
