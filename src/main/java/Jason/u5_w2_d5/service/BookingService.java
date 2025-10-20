package Jason.u5_w2_d5.service;

import Jason.u5_w2_d5.entities.Booking;
import Jason.u5_w2_d5.entities.Employee;
import Jason.u5_w2_d5.entities.Trip;
import Jason.u5_w2_d5.exceptions.BadRequestException;
import Jason.u5_w2_d5.exceptions.NotFoundException;
import Jason.u5_w2_d5.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private TripService tripService;

    // Crea una prenotazione: valida employee/trip, evita duplicati per data
    public Booking createBooking(Long tripId, Long employeeId, LocalDate bookingDate, String notes, String preferences) {
        Employee employee = employeeService.findById(employeeId);
        Trip trip = tripService.findById(tripId);

        // Controlla se esiste già una prenotazione per lo stesso dipendente e data
        boolean exists = bookingRepository.existsByEmployeeAndBookingDate(employee, bookingDate);
        if (exists) {
            throw new BadRequestException("Employee already has a booking for this date");
        }

        Booking b = new Booking();
        b.setEmployee(employee);
        b.setTrip(trip);
        b.setBookingDate(bookingDate);
        b.setNotes(notes);
        b.setPreferences(preferences);

        return bookingRepository.save(b);
    }


    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }


    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new NotFoundException("Booking not found"));
    }
}
