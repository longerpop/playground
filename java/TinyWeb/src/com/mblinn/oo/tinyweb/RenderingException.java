package com.mblinn.oo.tinyweb;

public class RenderingException extends Exception
{

    private static final long serialVersionUID = 1L;

    public RenderingException()
    {
        super();
    }

    public RenderingException(final String message)
    {
        super(message);
    }

    public RenderingException(final Throwable cause)
    {
        super(cause);
    }

    public RenderingException(final String message, final Throwable cause)
    {
        super(message, cause);
    }

}
