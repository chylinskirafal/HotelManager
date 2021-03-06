package pl.chylu.domain.reservation;

import pl.chylu.domain.guest.Guest;
import pl.chylu.domain.room.Room;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository {
    Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to);

    void readAll();

    void saveAll();

    List<Reservation> getAll();

    void remove(long id);
}