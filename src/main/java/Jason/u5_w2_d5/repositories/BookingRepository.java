package Jason.u5_w2_d5.repositories;

import Jason.u5_w2_d5.entities.Booking;
import Jason.u5_w2_d5.entities.Employee;
import Jason.u5_w2_d5.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByEmployee(Employee employee);
    List<Booking> findByTrip(Trip trip);
    boolean existsByEmployeeAndBookingDate(Employee employee, LocalDate bookingDate);
}
