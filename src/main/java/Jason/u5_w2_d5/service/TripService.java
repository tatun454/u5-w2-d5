package Jason.u5_w2_d5.service;

import Jason.u5_w2_d5.entities.Trip;
import Jason.u5_w2_d5.exceptions.NotFoundException;
import Jason.u5_w2_d5.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public Trip findById(Long id) {
        return tripRepository.findById(id).orElseThrow(() -> new NotFoundException("Trip not found"));
    }

    public Trip save(Trip t) {
        return tripRepository.save(t);
    }

    public List<Trip> findAll() {
        return tripRepository.findAll();
    }
}

