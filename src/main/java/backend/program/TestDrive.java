package backend.program;

import java.time.LocalDate;

/*
Управление записями на тест-драйв:
Просмотр списка записей на тест-драйв с указанием даты, времени и контактной информации клиента.
Возможность подтверждения или отмены записи на тест-драйв.
 */

public class TestDrive {
    private User user;
    private Auto auto;
    private LocalDate localDate;  //Дата и время записи на Тест Драйв

    public TestDrive(User user, Auto auto, LocalDate localDate) {
        this.user = user;
        this.auto = auto;
        this.localDate = localDate;
    }

    public User getUser() {
        return user;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }


    @Override
    public String toString() {
        return "Test Drive: " + "user: " + user.getName() + " " + user.getId() + " Auto: " + auto.getModel() + " " +
                auto.getVinCode() + " Date: " + localDate;
    }
}
