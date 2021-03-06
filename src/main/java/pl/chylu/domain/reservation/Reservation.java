package pl.chylu.domain.reservation;

import pl.chylu.domain.guest.Guest;
import pl.chylu.domain.reservation.dto.ReservationDTO;
import pl.chylu.domain.room.Room;

import java.time.LocalDateTime;

public class Reservation {

    private final long id;
    private final Room room;
    private final Guest guest;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Reservation(long id, Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        this.id = id;
        this.room = room;
        this.guest = guest;
        this.from = from;
        this.to = to;
    }

    String toCSV() {
        return String.format("%s,%s,%s,%s,%s%s",
                this.id,
                this.room.getId(),
                this.guest.getId(),
                this.from.toString(),
                this.to.toString(),
                System.getProperty("line.separator"));
    }

    public long getId() {
        return this.id;
    }

    public ReservationDTO getAsDTO() {
        return new ReservationDTO(this.id, this.from,
                this.to, this.room.getId(),
                this.room.getNumber(), this.guest.getId(),
                this.guest.getFirstName() + " " + this.guest.getLastName());
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public Room getRoom() {
        return this.room;
    }

    public LocalDateTime getTo() {
        return this.to;
    }
}