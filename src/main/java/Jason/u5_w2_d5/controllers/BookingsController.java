package Jason.u5_w2_d5.controllers;

import Jason.u5_w2_d5.entities.Booking;
import Jason.u5_w2_d5.exceptions.BadRequestException;
import Jason.u5_w2_d5.payloads.NewBookingPayload;
import Jason.u5_w2_d5.payloads.NewBookingResponseDTO;
import Jason.u5_w2_d5.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingsController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewBookingResponseDTO createBooking(@RequestBody @Validated NewBookingPayload body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Booking b = bookingService.createBooking(body.getTripId(), body.getEmployeeId(), body.getBookingDate(), body.getNotes(), body.getPreferences());
        return new NewBookingResponseDTO(b.getId());
    }

    @GetMapping("")
    public List<Booking> getAll() {
        return bookingService.findAll();
    }

}

