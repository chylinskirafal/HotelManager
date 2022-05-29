package pl.chylu.domain.repository;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Repository {
    protected void saveAll() {

    }

    protected void readAll() {

    }
    protected void extractMethod(String name) {
        Path file = Paths.get(
                System.getProperty("user.home"),
                "reservation_system",
                name);
    }
}