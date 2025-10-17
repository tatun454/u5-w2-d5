package Jason.u5_w2_d5.repositories;

import Jason.u5_w2_d5.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByDestinationContainingIgnoreCase(String destination);
    List<Trip> findByDate(LocalDate date);
}
