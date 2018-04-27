package shoppingcart.sk.fmph.uniba.security;

public class SCException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SCException() { super(); }

    public SCException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SCException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SCException(String message)
    {
        super(message);
    }

    public SCException(Throwable cause)
    {
        super(cause);
    }
}
