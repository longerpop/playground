package com.mblinn.oo.tinyweb.example;

import com.mblinn.oo.tinyweb.HttpRequest;
import com.mblinn.oo.tinyweb.TemplateController;
import com.mblinn.oo.tinyweb.View;

import java.util.*;

public class GreetingController extends TemplateController
{

    private final Random random;

    public GreetingController(final View view)
    {
        super(view);
        random = new Random();
    }

    @Override
    public Map<String, List<String>> doRequest(final HttpRequest request)
    {
        final Map<String, List<String>> helloModel = new HashMap<>();
        helloModel.put("greetings", generateGreetings(request.getBody()));
        return helloModel;
    }

    private List<String> generateGreetings(final String namesCommaSeparated)
    {
        final List<String> greetings = new ArrayList<>();
        for (final String name : namesCommaSeparated.split(",")) {
            greetings.add(makeGreeting(name));
        }
        return greetings;
    }

    private String makeGreeting(final String name)
    {
        final String[] greetings = { "Hello", "Gerrings", "Salutations", "Hola" };
        return String.format("%s, %s", greetings[random.nextInt(4)], name);
    }

}
