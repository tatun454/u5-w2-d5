package Jason.u5_w2_d5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name = "bookings", uniqueConstraints = {
        @UniqueConstraint(name = "uk_employee_bookingdate", columnNames = {"employee_id", "booking_date"})
})
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    private String notes;
    private String preferences;

}
