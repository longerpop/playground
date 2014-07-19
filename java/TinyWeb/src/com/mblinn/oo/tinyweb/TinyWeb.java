package com.mblinn.oo.tinyweb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TinyWeb
{

    private final List<Filter> filters;
    private final Map<String, Controller> controllers;

    public TinyWeb(final Map<String, Controller> controllers, final List<Filter> filters)
    {
        this.controllers = new HashMap<>(controllers);
        this.filters = new ArrayList<>(filters);
    }

    public HttpResponse handleRequest(final HttpRequest request)
    {
        HttpRequest currentRequest = request;
        for (final Filter filter : filters) {
            currentRequest = filter.doFilter(currentRequest);
        }

        final Controller controller = controllers.get(currentRequest.getPath());
        if (controller == null) return null;

        return controller.handleRequest(currentRequest);
    }

}
