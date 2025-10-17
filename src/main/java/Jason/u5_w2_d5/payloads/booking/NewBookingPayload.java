package Jason.u5_w2_d5.payloads.booking;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NewBookingPayload {

    @NotNull
    private Long tripId;

    @NotNull
    private Long employeeId;

    @NotNull
    private LocalDate bookingDate;

    private String notes;
    private String preferences;
}

