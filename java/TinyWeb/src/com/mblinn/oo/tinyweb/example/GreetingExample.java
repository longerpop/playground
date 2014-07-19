package com.mblinn.oo.tinyweb.example;

import com.mblinn.oo.tinyweb.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GreetingExample
{

    private static final List<Filter> filters = new LinkedList<>();
    private static final Map<String, Controller> controllers = new HashMap<>();

    public static void main(final String[] args)
    {
        filters.add(new LoggingFilter());
        controllers.put("/greeting", new GreetingController(
            new StrategyView(new GreetingRenderingStrategy())
        ));

        final TinyWeb greetingWeb = new TinyWeb(controllers, filters);
        final HttpRequest request = HttpRequest.Builder.newBuilder()
                .path("/greeting")
                .body("Mike,Joe,John,Steve")
                .build();

        final HttpResponse response = greetingWeb.handleRequest(request);
        System.out.println("responseCode: " + response.getResponseCode());
        System.out.println("responseBody: " + response.getBody());
    }

}
