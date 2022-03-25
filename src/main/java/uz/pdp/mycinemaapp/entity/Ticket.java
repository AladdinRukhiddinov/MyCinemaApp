package uz.pdp.mycinemaapp.entity;

import lombok.*;
import uz.pdp.mycinemaapp.entity.baseEntity.AbsEntity;
import uz.pdp.mycinemaapp.entity.enums.TicketStatus;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "tickets")
public class Ticket extends AbsEntity {

    @OneToOne
    private MovieAnnouncement movieAnnouncement;

    @OneToOne
    private Seat seat;

    @OneToOne
    private Attachment qrCode;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @OneToOne
    private Cart cart;
}
