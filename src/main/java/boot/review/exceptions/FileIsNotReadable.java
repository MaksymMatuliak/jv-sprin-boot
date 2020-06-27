package boot.review.exceptions;

public class FileIsNotReadable extends RuntimeException {
    public FileIsNotReadable(String message) {
        super(message);
    }
}
