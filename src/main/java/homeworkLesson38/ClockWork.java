package homeworkLesson38;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

public class ClockWork {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClockWork.class);



    public static void main(String[] args) {
        LOGGER.info("Task 1 started");
        task1();
        line();

        LOGGER.info("Task 2 started");
        task2();
        line();

        LOGGER.info("Task 3 started");
        task3();
        line();

        LOGGER.info("Task 4 started");
        task4();
        line();

        LOGGER.info("Task 5 started");
        task5();
        line();

        LOGGER.info("Task 6 started");
        task6();
        line();

        LOGGER.info("Task 7 started");
        task7();
        line();
    }

    /*
    Задача 1
    Напишите программу, которая создает объект LocalDate для сегодняшней даты,
    а затем создает другой объект LocalDate для даты вашего следующего дня рождения.
    Выведите обе даты на консоль.
     */
    public static void task1(){
        LocalDate todayDate = LocalDate.now();
        LocalDate birthdayDate = LocalDate.of(2024, 6, 29);

        LOGGER.info("Today: {}", todayDate);
        LOGGER.info("Next birthday: {}", birthdayDate);
    }

    /*
    Задача 2
    Создайте два объекта LocalTime: один для времени "07:30" утра, а другой для "08:45" вечера.
    Используйте методы isBefore и isAfter, чтобы определить, какое время раньше или позже,
    и выведите результат на консоль.
     */
    public static void task2(){
        LocalTime timeOne = LocalTime.of(7, 30);
        LocalTime timeTwo = LocalTime.of(20, 45);

        if (timeOne.isBefore(timeTwo)){
            LOGGER.info("Time {} is before {}", timeOne, timeTwo);
        }
        else if(timeOne.isAfter(timeTwo)){
            LOGGER.info("Time {} is after {}", timeOne, timeTwo);
        }
        else {
            LOGGER.info("Time {} is the same time as {}", timeOne, timeTwo);
        }
    }

    /*
    Задача 3
    Используя класс Period, рассчитайте разницу в днях, месяцах и годах между текущей датой
    и датой вашего последнего дня рождения. Выведите эту информацию на консоль.
     */
    public static void task3(){
        LocalDate now = LocalDate.now();
        LocalDate birthday = LocalDate.of(2023, 6, 29);
        Period period = Period.between(birthday, now);

        LOGGER.info("Период между датами составляет {} лет, {} месяцев, {} дней", period.getYears(), period.getMonths(), period.getDays());
    }

    /*
    Задача 4
    Создайте объект LocalDateTime для "2024-12-31T23:59".
    Используйте класс DateTimeFormatter для форматирования этой даты и времени в формате "dd/MM/yyyy HH:mm:ss".
    Выведите отформатированную строку на консоль.
     */
    public static void task4(){
        LocalDateTime localDateTime = LocalDateTime.of(2024, 12, 31, 23, 59);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formatted = formatter.format(localDateTime);
        LOGGER.info(formatted);
    }

    /*
    Задача 5
    Напишите программу, которая вычисляет, сколько дней, часов и минут осталось до следующего
    Нового Года от текущей даты и времени.
     */
    public static void task5(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextNewYear = LocalDateTime.of(now.getYear() + 1, 1, 1, 0, 0);
        Duration duration = Duration.between(now, nextNewYear);
        LOGGER.info("Time before New Year: {} days, {} hours, {} minutes", duration.toDays(), duration.toHours() % 24, duration.toMinutes() % 60);
    }

    /*
    Задача 6
    Напишите программу, которая определяет день недели для вашего следующего дня рождения.
    Используйте LocalDate и метод getDayOfWeek(). Выведите результат на консоль.
     */
    public static void task6(){
        LocalDate birthday = LocalDate.of(2024, 6, 29);
        String dayOfWeek = birthday.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        LOGGER.info("Day of week: {}", dayOfWeek);
    }

    /*
    Задача 7
    Создайте объект ZonedDateTime для текущей даты и времени в вашем локальном часовом поясе.
    Затем измените этот объект, чтобы он отражал время в часовом поясе Токио.
    Выведите оба значения времени на консоль.
     */
    public static void task7(){
        ZonedDateTime time = ZonedDateTime.now();
        String formatLocal = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.ENGLISH).format(time);
        LOGGER.info("Local time: {}", formatLocal);
        ZonedDateTime tokyoTime = time.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        String formatTokyo = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.ENGLISH).format(tokyoTime);
        LOGGER.info("Tokyo time: {}", formatTokyo);
    }

    public static void line(){
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_\n");
    }
}