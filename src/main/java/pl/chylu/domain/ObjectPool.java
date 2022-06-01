package pl.chylu.domain;

import pl.chylu.domain.guest.GuestRepository;
import pl.chylu.domain.guest.GuestService;
import pl.chylu.domain.reservation.ReservationRepository;
import pl.chylu.domain.reservation.ReservationService;
import pl.chylu.domain.room.RoomRepository;
import pl.chylu.domain.room.RoomService;

public class ObjectPool {
    private ObjectPool() {

    }
    public static GuestService getGuestService() {
        return GuestService.getInstance();
    }

    public static GuestRepository getGuestRepository() {
        return GuestRepository.getInstance();
    }

    public static RoomService getRoomService() {
        return RoomService.getInstance();
    }

    public static RoomRepository getRoomRepository() {
        return RoomRepository.getInstance();
    }

    public static ReservationService getReservationService() {
        return ReservationService.getInstance();
    }

    public static ReservationRepository getReservationRepository() {
        return ReservationRepository.getInstance();
    }

}