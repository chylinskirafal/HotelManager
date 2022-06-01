package pl.chylu.domain.reservation;

import pl.chylu.domain.guest.Guest;
import pl.chylu.domain.guest.GuestService;
import pl.chylu.domain.room.Room;
import pl.chylu.domain.room.RoomService;
import pl.chylu.exception.PersistenceToFileException;
import pl.chylu.util.Properties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepository {
    private static List<Reservation> reservations = new ArrayList<>();
    RoomService roomService = RoomService.getInstance();
    GuestService guestService = GuestService.getInstance();

    private static final ReservationRepository instance = new ReservationRepository();

    public static ReservationRepository getInstance() {
        return instance;
    }

    private ReservationRepository() {

    }

    public Reservation createNewReservation(Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        Reservation res = new Reservation(findNewId(), room, guest, from, to);
        reservations.add(res);
        return res;
    }

    private int findNewId() {
        int max = 0;
        for (Reservation reservation : reservations) {
            if (reservation.getId() > max) {
                max = reservation.getId();
            }
        }
        return max + 1;
    }

    public void readAll() {
        String name = "reservation.csv";
        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);
        if (!Files.exists(file)) {
            return;
        }
        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] reservationAsString = data.split(System.getProperty("line.separator"));

            for (String reservationAsStrings : reservationAsString) {
                String[] reservationData = reservationAsStrings.split(",");
                int reservationId = Integer.parseInt(reservationData[0]);
                int roomId = Integer.parseInt(reservationData[1]);
                int guestId = Integer.parseInt(reservationData[2]);
                String fromAsString = reservationData[3];
                String toAsString = reservationData[4];
                //TODO handle null guest/room
                addExitsReservation(reservationId, roomService.getRoomById(roomId),
                        guestService.getGuestById(guestId), LocalDateTime.parse(fromAsString),
                        LocalDateTime.parse(toAsString));
            }
        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "read", "guests data");
        }
    }

    private void addExitsReservation(int id, Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        Reservation res = new Reservation(id, room, guest, from, to);
        reservations.add(res);
    }

    public void saveAll() {
        String name = "reservation.csv";
        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);
        StringBuilder sb = new StringBuilder("");
        for (Reservation reservation : reservations) {
            sb.append(reservation.toCSV());
        }
        try {
            Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "write", "reservation data");
        }
    }

    public List<Reservation> getAll() {
        return reservations;
    }
}
