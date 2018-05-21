package overdiary.exception;

import java.util.function.Supplier;

public class UserException extends Exception implements Supplier<String> {

    public UserException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String get() {
        return null;
    }
}
