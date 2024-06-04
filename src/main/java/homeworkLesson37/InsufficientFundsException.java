package homeworkLesson37;

public class InsufficientFundsException extends Exception{

    double shortage;

    public InsufficientFundsException(double shortage) {
        super("Insufficient funds. Please top up your account by: " + shortage);
        this.shortage = shortage;
    }

}