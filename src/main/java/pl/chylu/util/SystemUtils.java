package pl.chylu.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class SystemUtils {

    private final Properties props = new Properties();

    public static final String HOTEL_NAME = "TimeToSleep";
    public static String SYSTEM_VERSION;
    public static final boolean IS_DEVELOPER_VERSION = true;

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static final int HOTEL_NIGHT_START_HOUR = 15;
    public static final int HOTEL_NIGHT_START_MINUTE = 0;
    public static final int HOTEL_NIGHT_END_HOUR = 10;
    public static final int HOTEL_NIGHT_END_MINUTE = 0;

    public static final String SINGLE_BED = "Pojedyncze";
    public static final String DOUBLE_BED = "Podwójne";
    public static final String KING_SIZE = "Małżeńskie";

    public static final Path DATA_DIRECTORY = Paths.get(
            System.getProperty("user.home"),
            "reservation_system");
    public static final String FEMALE = "Kobieta";
    public static final String MALE = "Mężczyzna";

    public static void createDataDirectory() throws IOException {
        if(!Files.isDirectory(DATA_DIRECTORY)) {
            Files.createDirectory(DATA_DIRECTORY);
        }
    }

    public SystemUtils() {
        try {
            this.props.load(this.getClass().getClassLoader().getResourceAsStream(".properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SystemUtils.SYSTEM_VERSION = props.get("system.version").toString();
    }

    public void createDataBaseConnection() {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/reservationSystem", "test", "");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS ROOMS(ID INT PRIMARY KEY AUTO_INCREMENT, ROOM_NUMBER INT NOT NULL UNIQUE)");
            statement.execute("CREATE TABLE IF NOT EXISTS BEDS(ID INT PRIMARY KEY AUTO_INCREMENT, ROOM_ID INT NOT NULL, BED VARCHAR2(55), FOREIGN KEY (ROOM_ID) REFERENCES ROOMS(ID))");
            System.out.println("Udalo się nawiazac polaczenie z baza danych");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Blad przy tworzeniu polaczenia z baza danych " + e.getMessage());
            e.printStackTrace();
        }
    }
}
