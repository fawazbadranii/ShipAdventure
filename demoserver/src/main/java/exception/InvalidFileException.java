package exception;

public class InvalidFileException extends Exception{
    public InvalidFileException() {
    }

    public InvalidFileException(String message) {
        super(message);

    }
}
