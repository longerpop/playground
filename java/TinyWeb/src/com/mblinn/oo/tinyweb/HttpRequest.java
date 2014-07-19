package com.mblinn.oo.tinyweb;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest
{

    private final String path;
    private final String body;
    private final Map<String, String> headers = new HashMap<>();

    private HttpRequest(final Builder builder)
    {
        path = builder.path;
        body = builder.body;
        headers.putAll(builder.headers);
    }

    public String getPath()
    {
        return path;
    }

    public String getBody()
    {
        return body;
    }

    public String getHeader(final String header)
    {
        return headers.getOrDefault(header, "");
    }

    public Map<String, String> getHeaders()
    {
        return new HashMap<>(headers);
    }

    public static class Builder
    {

        private String path;
        private String body;
        private final Map<String, String> headers = new HashMap<>();

        public static Builder newBuilder()
        {
            return new Builder();
        }

        public static Builder builderFrom(final HttpRequest request)
        {
            Builder builder = new Builder();

            builder.path(request.getPath());
            builder.body(request.getBody());
            builder.headers.putAll(request.getHeaders());

            return builder;
        }

        public HttpRequest build()
        {
            return new HttpRequest(this);
        }

        public Builder path(final String path)
        {
            this.path = path;
            return this;
        }

        public Builder body(final String body)
        {
            this.body = body;
            return this;
        }

        public Builder header(final String header, final String content)
        {
            this.headers.put(header, content);
            return this;
        }

    }

}
