package com.indepthkenya.connectionpooling.exception;

public class JedisPoolNotFoundException extends Exception {
    public JedisPoolNotFoundException() {
        super();
    }

    public JedisPoolNotFoundException(String message) {
        super(message);
    }

    public JedisPoolNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public JedisPoolNotFoundException(Throwable cause) {
        super(cause);
    }

    protected JedisPoolNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
