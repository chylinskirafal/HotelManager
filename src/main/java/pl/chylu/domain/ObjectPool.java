package pl.chylu.domain;

import pl.chylu.domain.guest.GuestDatabaseRepository;
import pl.chylu.domain.guest.GuestRepository;
import pl.chylu.domain.guest.GuestService;
import pl.chylu.domain.reservation.ReservationDatabaseRepository;
import pl.chylu.domain.reservation.ReservationRepository;
import pl.chylu.domain.reservation.ReservationService;
import pl.chylu.domain.room.RoomDatabaseRepository;
import pl.chylu.domain.room.RoomRepository;
import pl.chylu.domain.room.RoomService;

public class ObjectPool {
    private final static RoomService roomService = new RoomService();
    private final static ReservationService reservationService = new ReservationService();
    private ObjectPool() {
    }
    public static GuestService getGuestService() {
        return GuestService.getInstance();
    }
    public static GuestRepository getGuestRepository() {
        return GuestDatabaseRepository.getInstance();
    }
    public static RoomService getRoomService() {
        return roomService;
    }
    public static RoomRepository getRoomRepository() {
//        return RoomFileRepository.getInstance();
        return RoomDatabaseRepository.getInstance();
    }
    public static ReservationService getReservationService() {
        return reservationService;
    }
    public static ReservationRepository getReservationRepository() {
        return ReservationDatabaseRepository.getInstance();
    }
}