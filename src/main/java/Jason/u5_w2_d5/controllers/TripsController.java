package Jason.u5_w2_d5.controllers;

import Jason.u5_w2_d5.entities.Trip;
import Jason.u5_w2_d5.entities.TripStatus;
import Jason.u5_w2_d5.exceptions.BadRequestException;
import Jason.u5_w2_d5.payloads.trips.NewTripPayload;
import Jason.u5_w2_d5.payloads.trips.NewTripResponseDTO;
import Jason.u5_w2_d5.payloads.trips.AssignEmployeePayload;
import Jason.u5_w2_d5.payloads.booking.NewBookingResponseDTO;
import Jason.u5_w2_d5.service.BookingService;
import Jason.u5_w2_d5.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripsController {

    @Autowired
    private TripService tripService;

    @Autowired
    private BookingService bookingService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewTripResponseDTO createTrip(@RequestBody @Valid NewTripPayload body) {
        Trip t = new Trip();
        t.setDestination(body.getDestination());
        t.setDate(body.getDate());
        t.setStatus(body.getStatus());
        Trip saved = tripService.save(t);
        return new NewTripResponseDTO(saved.getId());
    }

    @GetMapping("")
    public List<Trip> getAll() {
        return tripService.findAll();
    }

    @GetMapping("/{id}")
    public Trip getById(@PathVariable Long id) {
        return tripService.findById(id);
    }

    @PutMapping("/{id}")
    public Trip updateTrip(@PathVariable Long id, @RequestBody Trip body) {
        return tripService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrip(@PathVariable Long id) {
        tripService.findByIdAndDelete(id);
    }

    // Endpoint per aggiornare solo lo status del viaggio
    @PatchMapping("/{id}/status")
    public Trip updateStatus(@PathVariable Long id, @RequestParam TripStatus status) {
        Trip existing = tripService.findById(id);
        existing.setStatus(status);
        return tripService.save(existing);
    }

    // Assegna un dipendente al viaggio
    @PostMapping("/{id}/assign")
    @ResponseStatus(HttpStatus.CREATED)
    public NewBookingResponseDTO assignEmployee(@PathVariable Long id, @RequestBody @Valid AssignEmployeePayload body) {
        var b = bookingService.createBooking(id, body.getEmployeeId(), body.getBookingDate(), body.getNotes(), body.getPreferences());
        return new NewBookingResponseDTO(b.getId());
    }
}
