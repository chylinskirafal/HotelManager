package pl.chylu.domain.reservation;

import pl.chylu.domain.ObjectPool;
import pl.chylu.domain.guest.Guest;
import pl.chylu.domain.guest.GuestService;
import pl.chylu.domain.reservation.dto.ReservationDTO;
import pl.chylu.domain.room.Room;
import pl.chylu.domain.room.RoomService;
import pl.chylu.util.SystemUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private final RoomService roomService = ObjectPool.getRoomService();
    private final GuestService guestService = ObjectPool.getGuestService();
    private final ReservationRepository repository = ObjectPool.getReservationRepository();

    private final static ReservationService instance = new ReservationService();

    private ReservationService() {

    }

    public static ReservationService getInstance() {
        return instance;
    }

    public Reservation createNewReservation(LocalDate from, LocalDate to, int roomId, int guestId) throws IllegalArgumentException {

        //TODO: handle null room
        Room room = this.roomService.getRoomById(roomId);
        //TODO: handle null guest
        Guest guest = this.guestService.getGuestById(guestId);

        LocalDateTime fromWithTime = from.atTime(SystemUtils.HOTEL_NIGHT_START_HOUR, SystemUtils.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime toWithTime = to.atTime(SystemUtils.HOTEL_NIGHT_END_HOUR, SystemUtils.HOTEL_NIGHT_END_MINUTE);

        if (toWithTime.isBefore(fromWithTime)) {
            throw new IllegalArgumentException();
        }
        ;

        return this.repository.createNewReservation(room, guest, fromWithTime, toWithTime);
    }

    public void readAll() {
        this.repository.readAll();
    }

    public void saveAll() {
        this.repository.saveAll();
    }

    public List<ReservationDTO> getReservationsAsDTO() {

        List<ReservationDTO> result = new ArrayList<>();

        List<Reservation> reservations = this.repository.getAll();

        for(Reservation reservation : reservations) {
            ReservationDTO dto = reservation.getAsDTO();
            result.add(dto);
        }

        return result;
    }

    public void removeReservation(int id) {
        this.repository.remove(id);
    }
}
