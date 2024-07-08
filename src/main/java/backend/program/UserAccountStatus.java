package backend.program;

public enum UserAccountStatus {

    ACTIVE ("Account is OK"),
    SUSPENDED("We suspend your account. Contact support please"),
    DELETED("Your account was deleted");

    String description;

    UserAccountStatus(String description) {
        this.description = description;
    }
}

