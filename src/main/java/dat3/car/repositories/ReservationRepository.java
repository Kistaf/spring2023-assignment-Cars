package dat3.car.repositories;

import dat3.car.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "SELECT r FROM Reservation r WHERE r.member=?1")
    List<Reservation> findReservationsByUsername(String username);

    @Query(value = "SELECT COUNT(*) FROM Reservation r WHERE r.member=?1")
    int countReservationsByUsername(String username);
}
