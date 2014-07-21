package com.mblinn.oo.tinyweb;

public class ControllerException extends Exception
{

    private static final long serialVersionUID = 1L;

    private final Integer statusCode;

    public ControllerException(final Integer statusCode)
    {
        super();
        this.statusCode = statusCode;
    }

    public ControllerException(final Integer statusCode, final String message)
    {
        super(message);
        this.statusCode = statusCode;
    }

    public ControllerException(final Integer statusCode, final Throwable cause)
    {
        super(cause);
        this.statusCode = statusCode;
    }

    public ControllerException(final Integer statusCode, final String message, final Throwable cause)
    {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode()
    {
        return statusCode;
    }

}
