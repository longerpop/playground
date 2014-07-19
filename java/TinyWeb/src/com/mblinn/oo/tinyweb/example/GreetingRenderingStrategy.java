package com.mblinn.oo.tinyweb.example;

import com.mblinn.oo.tinyweb.RenderingStrategy;

import java.util.List;
import java.util.Map;

public class GreetingRenderingStrategy implements RenderingStrategy
{

    @Override
    public String renderView(final Map<String, List<String>> model)
    {
        final StringBuilder responseBody = new StringBuilder("<h1>Friendly Greetings:</h1>\n");
        for (final String greeting : model.get("greetings")) {
            responseBody.append(String.format("<h2>%s</h2>\n", greeting));
        }
        return responseBody.toString();
    }

}
