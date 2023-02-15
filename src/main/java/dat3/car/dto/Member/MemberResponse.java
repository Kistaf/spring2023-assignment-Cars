package dat3.car.dto.Member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.dto.Reservation.ReservationResponse;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {
    private String username; //Remember this is the primary key
    private String email;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime edited;
    private Integer ranking;
    private Boolean approved;
    private List<ReservationResponse> reservations;

    //Convert Member Entity to Member DTO
    public MemberResponse(Member m, boolean includeAll) {
        this.username = m.getUsername();
        this.email = m.getEmail();
        this.street = m.getStreet();
        this.firstName = m.getFirstName();
        this.lastName = m.getLastName();
        this.city = m.getCity();
        this.zip = m.getZip();
        if (includeAll) {
            this.created = m.getCreated();
            this.edited = m.getLastEdited();
            this.approved = m.isApproved();
            this.ranking = m.getRanking();
        }
        if (m.getReservations().size() > 0) {
            List<ReservationResponse> reservations = m.getReservations().stream().map(res -> new ReservationResponse(res)).toList();
            this.reservations = reservations;
        }
    }
}
