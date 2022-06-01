package pl.chylu.domain.reservation;

import pl.chylu.domain.ObjectPool;
import pl.chylu.domain.guest.Guest;
import pl.chylu.domain.guest.GuestService;
import pl.chylu.domain.reservation.dto.ReservationDTO;
import pl.chylu.domain.room.Room;
import pl.chylu.domain.room.RoomService;
import pl.chylu.util.Properties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private final RoomService roomService = ObjectPool.getRoomService();
    private final GuestService guestService = ObjectPool.getGuestService();
    private final ReservationRepository repository = ObjectPool.getReservationRepository();
    private final static ReservationService instance = new ReservationService();

    public static ReservationService getInstance() {
        return instance;
    }

    private ReservationService() {

    }
    public Reservation createNewReservation(LocalDate from, LocalDate to, int roomId, int guestId) {

        //TODO: handle null room
        Room room = roomService.getRoomById(roomId);
        //TODO: handle null guest
        Guest guest = guestService.getGuestById(guestId);

        LocalDateTime fromWithTime = from.atTime(Properties.HOTEL_NIGHT_START_HOUR, Properties.HOTEL_NIGHT_START_MINUTE);
        LocalDateTime toWithTime = to.atTime(Properties.HOTEL_NIGHT_END_HOUR, Properties.HOTEL_NIGHT_END_MINUTE);

        return repository.createNewReservation(room, guest, fromWithTime, toWithTime);
    }

    public void readAll() {
        this.repository.readAll();
    }

    public void saveAll() {
        this.repository.saveAll();
    }

    public List<ReservationDTO> getAllAsDTO() {
        List<ReservationDTO> result = new ArrayList<>();
        List<Reservation> allReservation = repository.getAll();
        for (Reservation reservation : allReservation) {
            ReservationDTO dto = reservation.generateDTO();
            result.add(dto);
            System.out.println(reservation.getId());
        }
        return result;
    }
}
