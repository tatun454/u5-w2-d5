package Jason.u5_w2_d5.service;

import Jason.u5_w2_d5.entities.Trip;
import Jason.u5_w2_d5.exceptions.NotFoundException;
import Jason.u5_w2_d5.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    // inienzione nel costruttore del repository
    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    // Cerca per id e se non esiste lancia NotFoundException
    public Trip findById(Long id) {
        return tripRepository.findById(id).orElseThrow(() -> new NotFoundException("Trip not found"));
    }

    // Salva o aggiorna un viaggio
    public Trip save(Trip t) {
        return tripRepository.save(t);
    }

    // Restituisce tutti i viaggi
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }


    public Trip findByIdAndUpdate(Long id, Trip body) {
        Trip existing = findById(id);
        existing.setDestination(body.getDestination());
        existing.setDate(body.getDate());
        existing.setStatus(body.getStatus());
        return tripRepository.save(existing);
    }


    public void findByIdAndDelete(Long id) {
        Trip existing = findById(id);
        tripRepository.delete(existing);
    }
}
