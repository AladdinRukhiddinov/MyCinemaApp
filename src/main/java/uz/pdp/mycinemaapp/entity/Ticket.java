package uz.pdp.mycinemaapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.mycinemaapp.entity.enums.TicketStatus;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private MovieSession movieSession;

    @OneToOne
    private Seat seat;

    @OneToOne
    private Attachment qrCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @OneToOne
    private Cart cart;
}
