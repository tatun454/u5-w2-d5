package Jason.u5_w2_d5.payloads.trips;

import Jason.u5_w2_d5.entities.TripStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NewTripPayload {

    @NotBlank
    private String destination;

    @NotNull
    private LocalDate date;

    @NotNull
    private TripStatus status;
}

