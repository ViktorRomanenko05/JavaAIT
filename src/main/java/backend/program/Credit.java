package backend.program;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static java.lang.Long.parseLong;

// Класс рассчитан на следующую реализацию:
// В соответствующем окне приложения ставим галочку о необходимости кредита
// DatePicker-ом выбираем необходимый период и вводим сумму.
// Процентная ставка зависит от срока кредита

public class Credit implements Serializable {
    private double creditAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User user;
    private long id;
    private CreditStatus status;

    // Процентную ставку делаем зависимой от срока кредита и не вводим вручную
    public Credit(double creditAmount, LocalDateTime startDate, LocalDateTime endDate, User user) {
        this.creditAmount = creditAmount;
        this.startDate = startDate.withDayOfMonth(1);
        this.endDate = endDate.withDayOfMonth(1);
        this.user = user;
        this.id = parseLong(generateId());
        this.status = CreditStatus.ACTIVE;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public User getUser() {
        return user;
    }

    public long getId() {
        return id;
    }

    public CreditStatus getStatus() {
        return status;
    }

    public void setStatus(CreditStatus status) {
        this.status = status;
    }

    // Метод для генерации ID
    private String generateId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return LocalDateTime.now().format(formatter);
    }

    // метод для расчета периода кредита в месяцах
    public int calculateLoanTermInMonths() {
        return (int) ChronoUnit.MONTHS.between(startDate, endDate);
    }

    // Метод для определения процентной ставки на основе срока кредита
    public double getAnnualInterestRate(int loanTermInMonths) {
        if (loanTermInMonths <= 12) {
            return 3.0; // Процентная ставка для сроков до 1 года
        } else if (loanTermInMonths <= 24) {
            return 3.5; // Процентная ставка для сроков от 1 до 2 лет
        } else if (loanTermInMonths <= 36) {
            return 4.0; // Процентная ставка для сроков от 2 до 3 лет
        } else if (loanTermInMonths <= 48) {
            return 4.5; // Процентная ставка для сроков от 3 до 4 лет
        } else if (loanTermInMonths <= 60) {
            return 5.0; // Процентная ставка для сроков от 4 до 5 лет
        } else {
            return 5.5; // Процентная ставка для сроков более 5 лет
        }
    }

    // Метод для расчета месячного платежа согласно формуле:
    // monthlyPayment = (LoanAmount * MonthlyInterestRate * (1 + MonthlyInterestRate) ^ LoanTermInMonths) / ((1 + MonthlyInterestRate) ^ LoanTermInMonths - 1)
    //
    // creditAmount - сумма кредита
    // annualInterestRate - годовая процентная ставка (в процентах)
    // loanTermInMonths - срок кредита в месяцах
    // monthlyInterestRate - месячная процентная ставка, вычисляется как AnnualInterestRate / 12 / 100
    // monthlyPayment - ежемесячный платеж

    public double calculateMonthlyPayment() {
        int loanTermInMonths = calculateLoanTermInMonths(); // Вычисление срока кредита в месяцах
        double annualInterestRate = getAnnualInterestRate(loanTermInMonths); // Определение процентной ставки
        double monthlyInterestRate = annualInterestRate / 12 / 100; // Перевод годовой ставки в месячную

        double factor = 1.0; // часть формулы (1 + MonthlyInterestRate) ^ LoanTermInMonths
        for (int i = 0; i < loanTermInMonths; i++) {
            factor *= (1 + monthlyInterestRate);
        }

        // Формула расчета ежемесячного платежа
        return (creditAmount * monthlyInterestRate * factor) / (factor - 1);
    }

    // Метод для расчета общей конечной суммы кредита
    public double calculateTotalPayment() {
        int loanTermInMonths = calculateLoanTermInMonths();
        double monthlyPayment = calculateMonthlyPayment();
        return monthlyPayment * loanTermInMonths; // Общая сумма всех платежей
    }

    // Метод для расчета оставшегося времени до конца кредита в месяцах
    public int calculateRemainingTermInMonths() {
        LocalDateTime now = LocalDateTime.now().withDayOfMonth(1);

        if (now.isBefore(startDate)) {
            // Если текущая дата до начала кредита, вернуть общее количество месяцев по кредиту
            return calculateLoanTermInMonths();
        } else {
            // Если текущая дата в пределах кредита или после начала, вернуть оставшиеся месяцы до конца кредита
            return (int) ChronoUnit.MONTHS.between(now, endDate);
        }
    }

    // Метод для расчета оставшейся общей суммы платежей
    public double calculateRemainingTotalPayment() {
        int remainingTermInMonths = calculateRemainingTermInMonths();
        double monthlyPayment = calculateMonthlyPayment();
        return monthlyPayment * remainingTermInMonths;
    }

    // Метод для расчета количества оставшихся платежей
    public int calculateRemainingPaymentsCount() {
        return calculateRemainingTermInMonths();
    }

    // Метод для логирования информации о кредите
    public void logCreditDetails() {
        double monthlyPayment = calculateMonthlyPayment();
        double totalPayment = calculateTotalPayment();
        int loanTermInMonths = calculateLoanTermInMonths();
        int remainingTerm = calculateRemainingTermInMonths();
        double remainingAmount = calculateRemainingTotalPayment();
        double annualInterestRate = getAnnualInterestRate(loanTermInMonths);

        // Форматируем дату в dd.MM.yyyy
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        System.out.println("User: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Loan Amount: " + creditAmount);
        System.out.println("Annual Interest Rate: " + annualInterestRate + "%");
        System.out.println("Loan Start Date: " + startDate.format(dateFormatter));
        System.out.println("Loan End Date: " + endDate.format(dateFormatter));
        System.out.println("Loan Term: " + loanTermInMonths + " months");
        System.out.println("Monthly Payment: " + monthlyPayment);
        System.out.println("Total Payment: " + totalPayment);
        System.out.println("Remaining Total Payment: " + remainingAmount);
        System.out.println("Remaining Payments Count: " + remainingTerm);
    }
}