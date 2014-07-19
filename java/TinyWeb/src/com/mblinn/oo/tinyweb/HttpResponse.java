package com.mblinn.oo.tinyweb;

public class HttpResponse
{

    private final String body;
    private final Integer responseCode;

    private HttpResponse(final Builder builder)
    {
        body = builder.body;
        responseCode = builder.responseCode;
    }

    public String getBody()
    {
        return body;
    }

    public Integer getResponseCode()
    {
        return responseCode;
    }

    public static class Builder
    {
        private String body;
        private Integer responseCode;

        public static Builder newBuilder()
        {
            return new Builder();
        }

        public HttpResponse build()
        {
            return new HttpResponse(this);
        }

        public Builder body(final String body)
        {
            this.body = body;
            return this;
        }

        public Builder responseCode(final Integer responseCode)
        {
            this.responseCode = responseCode;
            return this;
        }

    }

}
