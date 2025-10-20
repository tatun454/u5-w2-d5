package Jason.u5_w2_d5.controllers;

import Jason.u5_w2_d5.entities.Booking;
import Jason.u5_w2_d5.payloads.booking.NewBookingPayload;
import Jason.u5_w2_d5.payloads.booking.NewBookingResponseDTO;
import Jason.u5_w2_d5.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingsController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewBookingResponseDTO createBooking(@RequestBody @Valid NewBookingPayload body) {
        // Mappa payload, chiama service che valida e crea la prenotazione; ritorna DTO con id
        Booking b = bookingService.createBooking(body.getTripId(), body.getEmployeeId(), body.getBookingDate(), body.getNotes(), body.getPreferences());
        return new NewBookingResponseDTO(b.getId());
    }

    @GetMapping("")
    public List<Booking> getAll() {

        return bookingService.findAll();
    }

}
