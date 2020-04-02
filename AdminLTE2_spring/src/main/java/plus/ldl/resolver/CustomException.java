package plus.ldl.resolver;

/**
 * @author ldl.plus
 * @date 2020年03月19日  16:40
 */
public class CustomException extends RuntimeException {

    private String message;

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public CustomException(String message) {
        this.message = message;
    }


}
