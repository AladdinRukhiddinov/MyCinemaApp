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

    @ManyToOne
    private MovieSession movieSession;

    @ManyToOne
    private Seat seat;

    @OneToOne
    private Attachment qrCode;

    private Double price;

    @Enumerated(EnumType.STRING)
    private TicketStatus status = TicketStatus.NEW;

    @ManyToOne
    private User user;

    public Ticket(MovieSession movieSession, Seat seat, Double price, User user) {
        this.movieSession = movieSession;
        this.seat = seat;
        this.price = price;
        this.user = user;
    }

}
