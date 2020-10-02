package com.indepthkenya.connectionpooling.exception;

public class JedisConnectionNotFound extends Exception{
    public JedisConnectionNotFound() {
        super();
    }

    public JedisConnectionNotFound(String message) {
        super(message);
    }

    public JedisConnectionNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public JedisConnectionNotFound(Throwable cause) {
        super(cause);
    }

    protected JedisConnectionNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
