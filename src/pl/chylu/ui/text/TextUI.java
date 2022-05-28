package pl.chylu.ui.text;


import pl.chylu.exception.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {
    public void showSystemInfo(String hotelName, double systemVersion, boolean isDeveloperVersion) {
        System.out.println("Witam w systemie rezerwacji dla hotelu " + hotelName +
                "\nAktualna wersja systemu: " + systemVersion +
                "\nWersja developerska: " + isDeveloperVersion +
                "\n=========================\n"
        );
    }

    public void showMainMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Trwa ładowanie danych...");

        try {
            performAction(input);
        } catch (WrongOptionException | OnlyNumberException e) {
            System.out.println(
                    "Wystąpił niespodziewany błąd" +
                    "\nKod błędu: " + e.getCode() +
                    "Komunikat błędu: " + e.getMessage()
                    );
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(
                    "Wystąpił niespodziewany błąd" +
                            "\nNieznany kod błędu" +
                            "Komunikat błędu: " + e.getMessage()
            );
            e.printStackTrace();
        }
    }
    private void performAction(Scanner input) {
        int option = -1;
        while (option != 0) {
            option = getActionFromUser(input);
            switch (option) {
                //case 1 -> readNewGuestData(input);
                //case 2 -> readNewRoomData(input);
                //case 3 -> showAllGuests();
                //case 4 -> showAllRoom();
                //case 5 -> findGuest();
                //case 6 -> findRoom();
                //case 7 -> saveAllData();
                case 0 -> {
                    System.out.println("Wychodzę z aplikacji. Zapisuję dane.");
                    //saveAllData();
                    System.exit(0);
                }
                case default -> throw new WrongOptionException("Zły wybór opcji w menu.");
            }
        }
    }
    private static int getActionFromUser(Scanner in) {
        System.out.println(
                """
                        1 - Dodaj nowego gościa.
                        2 - Dodaj nowy pokój.
                        3 - Wypisz wszystkich gości.
                        4 - Wypisz wszystkie pokoje.
                        5 - Znajdź swojego gościa
                        6 - Znajdź swój pokój
                        7 - Zapisz dotychczasowe postępy.
                        0 - Wyjście z aplikacji
                        Wybierz opcję:"""
        );
        int option;
        try {
            option = in.nextInt();
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Użyj tylko liczb w menu wyboru.");
        }
        return option;
    }
}
